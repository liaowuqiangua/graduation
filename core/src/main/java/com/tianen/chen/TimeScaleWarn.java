package com.tianen.chen;

import com.tianen.chen.base.conf.Config;
import com.tianen.chen.base.entity.JsonPkg;
import com.tianen.chen.base.entity.JsonType;
import com.tianen.chen.base.pojo.MarketData;
import com.tianen.chen.base.pojo.TimeScaleWarning;
import com.tianen.chen.base.util.JsonUtil;
import com.tianen.chen.base.warn.MessageWarning;
import com.tianen.chen.common.AssignerWithPeriodicWatermarksImpl;
import com.tianen.chen.sink.MongoSink;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static com.tianen.chen.common.BroadcastDescriptor.mapStateDescriptor;

public class TimeScaleWarn {
    private static final Logger log = LoggerFactory.getLogger(TimeScaleWarn.class);
    private static final JsonUtil jsonUtil = new JsonUtil();

    private static void timeScaleWarnBootStrap(String[] args){
        final ParameterTool params = ParameterTool.fromArgs(args);
        final Properties properties = new Properties();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        env.getConfig().setGlobalJobParameters(params);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        properties.put("bootstrap.servers",params.get("bootstrap.servers", Config.KAFKA));
        properties.put("group.id",params.get("groupid","group2"));

        String[] windowSizes = params.has("windowSizes")&&params.get("windowSizes").split(",").length==3?(params.get("windowSizes").split(",")):new String[]{"1","5","30"};
//        List<String> topics= params.has("topic") ? Arrays.asList(params.get("topic").split(",")):Collections.singletonList("tianentest");
        List<String> topics= Arrays.asList(params.get("topic","te-all").split(","));

        log.info("start execute job : TimeScaleWarn , bootstrap.servers ：{} , group.id : {} , topic : {}",
                properties.get("bootstrap.servers"),
                properties.get("group.id"),
                topics);
        executeJob(properties,topics,windowSizes,env);
    }
    private static void executeJob(Properties properties, List<String> topics, String[] windowSizes, StreamExecutionEnvironment env){
        DataStream<Tuple3<String,Double,Double>> stream = env
                .addSource(new FlinkKafkaConsumer<>(topics, new SimpleStringSchema(), properties))
                .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarksImpl<>())
                .flatMap(new FilterAndCleanMarketData());

        BroadcastStream<String> ruleStream = env
                .addSource(new FlinkKafkaConsumer<>(topics, new SimpleStringSchema(), properties))
                .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarksImpl<>())
                .flatMap(new FlatMapFunction<String, String>() {
                    @Override
                    public void flatMap(String value, Collector<String> out) throws Exception {
                        JsonPkg jsonPkg = jsonUtil.str2JsonPkg(value);
                        if (jsonPkg.getType()==JsonType.TimeScaleWarning){
                            out.collect(jsonPkg.getJson());
                        }
                    }
                })
                .broadcast(mapStateDescriptor);

        //daily time scale warn
        DataStream<Tuple4<String,String,Double,Double>> windowedStreamDaily = stream
                .keyBy(x->x.f0)
                .timeWindow(Time.days(1), Time.milliseconds(1000))
                .reduce(new getMinAndMaxScope(),new WindowCollection("daily"))
                .connect(ruleStream)
                .process(new MatchRules());

        //time scale warn for level1
        DataStream<Tuple4<String,String,Double,Double>> windowedStreamLevel1 = stream
                .keyBy(x->x.f0)
                .timeWindow(Time.minutes(Integer.valueOf(windowSizes[0])), Time.milliseconds(1000))
                .reduce(new getMinAndMaxScope(),new WindowCollection("level1"))
                .connect(ruleStream)
                .process(new MatchRules());
        //time scale warn for level2
        DataStream<Tuple4<String,String,Double,Double>> windowedStreamLevel2 = stream
                .keyBy(x->x.f0)
                .timeWindow(Time.minutes(Integer.valueOf(windowSizes[1])), Time.milliseconds(1000))
                .reduce(new getMinAndMaxScope(),new WindowCollection("level2"))
                .connect(ruleStream)
                .process(new MatchRules());
        //time scale warn for level3
        DataStream<Tuple4<String,String,Double,Double>> windowedStreamLevel3 = stream
                .keyBy(x->x.f0)
                .timeWindow(Time.minutes(Integer.valueOf(windowSizes[2])), Time.milliseconds(1000))
                .reduce(new getMinAndMaxScope(),new WindowCollection("level3"))
                .connect(ruleStream)
                .process(new MatchRules());

        DataStream<Tuple4<String,String,Double,Double>> unionStream = windowedStreamLevel1
                .union(windowedStreamLevel2)
                .union(windowedStreamLevel3)
                .union(windowedStreamDaily);

        DataStream<String> warnMsg = unionStream.map((value -> jsonUtil.any2JsonPkgStr(jsonUtil.messageWarning2Json(new MessageWarning(JsonType.TimeScaleWarning.getType(),value.f1,"[TimeScaleWarn] instrumentID:"+value.f0+", max :"+value.f2+", min :"+value.f3+"超出预警范围 !")),JsonType.Warn)));

        warnMsg.print();
        warnMsg.addSink(new FlinkKafkaProducer<>("te-warn",new SimpleStringSchema(),properties));
        warnMsg.addSink(new MongoSink("warn"));

        try {
            env.execute("TimeScalaWarn");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class FilterAndCleanMarketData implements FlatMapFunction<String, Tuple3<String,Double,Double>>{
        @Override
        public void flatMap(String value, Collector<Tuple3<String, Double, Double>> out) throws Exception {
            JsonPkg jsonPkg = jsonUtil.str2JsonPkg(value);
            if (jsonPkg.getType()== JsonType.MarketData){
                MarketData marketData= jsonUtil.json2MarketData(jsonPkg.getJson());
                out.collect(Tuple3.of(marketData.getInstrumentID(),marketData.getLastPrice(),marketData.getLastPrice()));
            }
        }
    }

    public static class getMinAndMaxScope implements ReduceFunction<Tuple3<String,Double,Double>> {
        @Override
        public Tuple3<String, Double, Double> reduce(Tuple3<String, Double, Double> value1, Tuple3<String, Double, Double> value2){
            return Tuple3.of(value1.f0,Double.max(value1.f1,value2.f1),Double.min(value1.f2,value2.f2));
        }
    }

    public static class WindowCollection extends ProcessWindowFunction<Tuple3<String, Double, Double>, Tuple4<String, String, Double, Double>, String, TimeWindow> {
        private final String type;
        private WindowCollection(String type){
            this.type = type;
        }
        private transient ValueState<Long> state;
        @Override
        public void process(String key, Context context, Iterable<Tuple3<String, Double, Double>> elements, Collector<Tuple4<String, String, Double, Double>> out) throws Exception {
            Tuple3<String, Double, Double> tmp = elements.iterator().next();
            context.currentWatermark();
            if(state.value() + 60000 <= context.currentWatermark()){
                state.update(context.currentWatermark());
                out.collect(Tuple4.of(tmp.f0,type,tmp.f1,tmp.f2));
            }
        }
        @Override
        public void close(){
            state.clear();
        }

        @Override
        public void open(Configuration parameters) {
            ValueStateDescriptor<Long> descriptor = new ValueStateDescriptor<>("timeMark", BasicTypeInfo.LONG_TYPE_INFO,0L);
            state = getRuntimeContext().getState(descriptor);
        }
    }
    public static class MatchRules extends BroadcastProcessFunction<Tuple4<String, String, Double, Double>, String, Tuple4<String,String,Double,Double>> {

        @Override
        public void processElement(Tuple4<String, String, Double, Double> value, ReadOnlyContext ctx, Collector<Tuple4<String, String, Double, Double>> out) throws Exception {
            if (ctx.getBroadcastState(mapStateDescriptor).contains(value.f0)){
                final TimeScaleWarning rule = ctx.getBroadcastState(mapStateDescriptor).get(value.f0);
                System.out.println(rule);
            }
            out.collect(value);
        }

        @Override
        public void processBroadcastElement(String value, Context ctx, Collector<Tuple4<String, String, Double, Double>> out) throws Exception {
            TimeScaleWarning timeScaleWarning = jsonUtil.json2TimeScaleWarning(value);
            ctx.getBroadcastState(mapStateDescriptor).put(timeScaleWarning.getInstrumentID(),timeScaleWarning);
        }
    }

    public static void main(String[] args) {
        timeScaleWarnBootStrap(args);
    }
}
