package com.tianen.chen;

import com.tianen.chen.base.conf.Config;
import com.tianen.chen.base.entity.Dict;
import com.tianen.chen.base.entity.JsonPkg;
import com.tianen.chen.base.entity.JsonType;
import com.tianen.chen.base.pojo.InvestorControlGroup;
import com.tianen.chen.base.pojo.Trade;
import com.tianen.chen.base.pojo.TradeWarning;
import com.tianen.chen.base.util.JsonUtil;
import com.tianen.chen.base.warn.MessageWarning;
import com.tianen.chen.common.AssignerWithPeriodicWatermarksImpl;
import com.tianen.chen.sink.MongoSink;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.streaming.api.functions.co.CoProcessFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.tianen.chen.common.BroadcastDescriptor.groupDescriptor;


/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/3/25 14:35
 * @description :${description}
 */
public class TradeWarn {
    private static final Logger log;
    private static final JsonUtil jsonUtil;
    private static final Long CLEANGAP = Time.days(1).toMilliseconds();
    static {
        log = LoggerFactory.getLogger(TradeWarn.class);
        jsonUtil = new JsonUtil();
    }

    private static void tradeWarnBootStrap(String[] args){
        final ParameterTool params = ParameterTool.fromArgs(args);
        final Properties properties = new Properties();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.getConfig().setGlobalJobParameters(params);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

//        env.enableCheckpointing(10000);
//        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
//        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
//        env.getCheckpointConfig().setCheckpointTimeout(60000);
//        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
//        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
//        env.setStateBackend(new FsStateBackend("file:///home/flink/backend"));
//        env.setStateBackend(new MemoryStateBackend());
        properties.put("bootstrap.servers",params.get("bootstrap.servers", Config.KAFKA));
        properties.put("group.id",params.get("groupid","group2"));

        List<String> topics= Arrays.asList(params.get("topic","te-all").split(","));

        log.info("start execute job : TradeWarn , bootstrap.servers ：{} , group.id : {} , topic : {}",
                properties.get("bootstrap.servers"),
                properties.get("group.id"),
                topics);
        executeJob(properties,topics,env);
    }


    private static void executeJob(Properties properties, List<String> topics, StreamExecutionEnvironment env){

        //预警规则数据源
        DataStream<TradeWarning> ruleStream = env.addSource(new FlinkKafkaConsumer<>(topics, new SimpleStringSchema(), properties))
                .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarksImpl<>())
                .flatMap(new FilterTradeWarning());
        //实控账户组数据源
        BroadcastStream<InvestorControlGroup> controlGroupDataStream = env.addSource(new FlinkKafkaConsumer<>(topics, new SimpleStringSchema(), properties))
                .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarksImpl<>())
                .flatMap(new FilterAndCleanControlGroup())
                .broadcast(groupDescriptor);
        //成交流数据源以及核心预警计算
        DataStream<String> tradeStream = env.addSource(new FlinkKafkaConsumer<>(topics, new SimpleStringSchema(), properties))
                .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarksImpl<>())
                .flatMap(new FilterAndCleanTrade())
                .keyBy(0,1)
                .process(new FilterSelfTrade())
//                .setParallelism(4)
                .connect(controlGroupDataStream)
                .process(new CombineControlGroup())
//                .setParallelism(4)
                .connect(ruleStream)
                .process(new matchRule());

        tradeStream.addSink(new FlinkKafkaProducer<>("te-warn",new SimpleStringSchema(),properties));
        tradeStream.addSink(new MongoSink("warn"));

        tradeStream.print();

        try {
            env.execute("TradeWarn");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // saving operator state
    private static class matchRule extends CoProcessFunction<Tuple4<String,String,String,Integer>, TradeWarning,String> implements CheckpointedFunction {
        // Map<investorID,List<TradeWarning>>
        private Map<String,List<TradeWarning>> tradeWarningState;
        // Map<controlGroup [null or groupName] , self trade counter>
        private Map<String,Integer> investorGroupCounterState;
        // investorID exchangeID groupName counter
        private transient ListState<Map<String,List<TradeWarning>>> tradeWarningOperatorState;
        private transient ListState<Map<String,Integer>> investorControlGroupOperatorState;
        @Override
        public void processElement1(Tuple4<String,String,String,Integer> value, Context ctx, Collector<String> out){
            String investorID = value.f0;
            String groupName = value.f2;
            List<TradeWarning> tradeWarnings =  tradeWarningState.get(investorID);
            String exchangeID = value.f1;
            int counter = value.f3;

            //将每个用户的自成交数合并到组添加到Map中
            if (Objects.nonNull(groupName)) {
                investorGroupCounterState.put(groupName, investorGroupCounterState.getOrDefault(groupName, 0) + counter);
            }
            //将每个用户的自成交数添加到Map中
            investorGroupCounterState.put(investorID,investorGroupCounterState.getOrDefault(investorID, 0)+counter);

            try {
                if (Objects.nonNull(tradeWarnings))
                    tradeWarnings.forEach(x->{
                        if (x.getExchangeID().equals(exchangeID)){
                            int currentSelfTradeCount = x.getSelfTradeConsolidate().equals(Dict.Consolidate.isConsolidate)&&Objects.nonNull(groupName)?investorGroupCounterState.get(groupName):counter;
                            if (x.getScale3()<=currentSelfTradeCount)
                                out.collect(jsonUtil.any2JsonPkgStr(jsonUtil.messageWarning2Json(new MessageWarning(JsonType.TradeWarning.getType(),"3","[TradeWarn]investorID :"+ investorID+"\texchangeID :"+ exchangeID)),JsonType.Warn));
                            else if (x.getScale2()<=currentSelfTradeCount)
                                out.collect(jsonUtil.any2JsonPkgStr(jsonUtil.messageWarning2Json(new MessageWarning(JsonType.TradeWarning.getType(),"2","[TradeWarn]investorID :"+ investorID+"\texchangeID :"+ exchangeID)),JsonType.Warn));
                            else if (x.getScale1()<=currentSelfTradeCount)
                                out.collect(jsonUtil.any2JsonPkgStr(jsonUtil.messageWarning2Json(new MessageWarning(JsonType.TradeWarning.getType(),"1","[TradeWarn]investorID :"+ investorID+"\texchangeID :"+ exchangeID)),JsonType.Warn));
                        }
                    });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void processElement2(TradeWarning value, Context ctx, Collector<String> out) {
            if (tradeWarningState.containsKey(value.getInvestorID())){
                List<TradeWarning> tradeWarnings = tradeWarningState.get(value.getInvestorID());
                int i = 0;
                for (;i<tradeWarnings.size();++i){
                    if (tradeWarnings.get(i).getInvestorID().equals(value.getInvestorID())&&tradeWarnings.get(i).getExchangeID().equals(value.getExchangeID())){
                        tradeWarnings.set(i,value);
                        break;
                    }
                }
                if (i==tradeWarnings.size()) {
                    tradeWarnings.add(value);
                }
            }
            else {
                List<TradeWarning> tradeWarnings = new ArrayList<>();
                tradeWarnings.add(value);
                tradeWarningState.put(value.getInvestorID(),tradeWarnings);
            }
//            investorGroupCounterState.remove(value.getInvestorID());
        }
        @Override
        public void open(Configuration parameters){
            tradeWarningState=new HashMap<>();
            investorGroupCounterState=new HashMap<>();
        }

        @Override
        public void snapshotState(FunctionSnapshotContext context) throws Exception{
            tradeWarningOperatorState.clear();
            investorControlGroupOperatorState.clear();
            tradeWarningOperatorState.add(tradeWarningState);
            investorControlGroupOperatorState.add(investorGroupCounterState);
        }

        @Override
        public void initializeState(FunctionInitializationContext context) throws Exception{
            ListStateDescriptor<Map<String,List<TradeWarning>>> tradeWarningDescriptor =
                    new ListStateDescriptor<>(
                            "tradeWarning",
                            TypeInformation.of(new TypeHint<Map<String,List<TradeWarning>>>() {}));
            ListStateDescriptor<Map<String,Integer>> investorControlGroupDescriptor =
                    new ListStateDescriptor<>(
                            "investorControlGroup",
                            TypeInformation.of(new TypeHint<Map<String,Integer>>() {}));
            tradeWarningOperatorState = context.getOperatorStateStore().getListState(tradeWarningDescriptor);
            investorControlGroupOperatorState = context.getOperatorStateStore().getListState(investorControlGroupDescriptor);
            if (context.isRestored()){
                if (tradeWarningOperatorState.get().iterator().hasNext())
                    tradeWarningState=tradeWarningOperatorState.get().iterator().next();
                if (investorControlGroupOperatorState.get().iterator().hasNext())
                    investorGroupCounterState = investorControlGroupOperatorState.get().iterator().next();
            }
        }
    }

    private static class FilterTradeWarning implements FlatMapFunction<String,TradeWarning> {
        @Override
        public void flatMap(String value, Collector<TradeWarning> out){
            JsonPkg jsonPkg= jsonUtil.str2JsonPkg(value);
            if (jsonPkg.getType()==JsonType.TradeWarning){
                TradeWarning tradeWarning= jsonUtil.json2TradeWarning(jsonPkg.getJson());
                out.collect(tradeWarning);
            }
        }
    }

    /**
     * @description: 实控账户组流广播到成交流
     * @date 2019/3/28 18:40
     */
    private static class CombineControlGroup extends BroadcastProcessFunction<Tuple3<String,String,Integer>, InvestorControlGroup, Tuple4<String,String,String,Integer>> {
        @Override
        public void processElement(Tuple3<String,String,Integer> value, ReadOnlyContext ctx, Collector<Tuple4<String, String,String, Integer>> out) throws Exception{
            out.collect(Tuple4.of(value.f0,value.f1,ctx.getBroadcastState(groupDescriptor).get(value.f0),value.f2));         //investorID exchangeID groupName counter
        }
        @Override
        public void processBroadcastElement(InvestorControlGroup value, Context ctx, Collector<Tuple4<String, String,String, Integer>> out) throws Exception {
            ctx.getBroadcastState(groupDescriptor).put(value.getInvestorID(),value.getGroupName());
        }
    }

    private static class FilterSelfTrade extends KeyedProcessFunction<Tuple,Tuple3<String,String, Trade>,Tuple3<String,String,Integer>> {
        private transient ListState<Trade> state;
        private transient ValueState<Integer> counter;
        private transient ValueState<Long> lastModified;
        @Override
        public void open(Configuration parameters) {
            ListStateDescriptor<Trade> listStateDescriptor= new ListStateDescriptor<>("selfTrade",TypeInformation.of(Trade.class));
            ValueStateDescriptor<Integer> valueStateDescriptor = new ValueStateDescriptor<>("selfTradeCounter",BasicTypeInfo.INT_TYPE_INFO,0);
            ValueStateDescriptor<Long> timeModifiedDescriptor = new ValueStateDescriptor<>("lastModified",BasicTypeInfo.LONG_TYPE_INFO,0L);
            state = getRuntimeContext().getListState(listStateDescriptor);
            counter = getRuntimeContext().getState(valueStateDescriptor);
            lastModified = getRuntimeContext().getState(timeModifiedDescriptor);
        }

        @Override
        public void processElement(Tuple3<String, String,Trade> value, Context ctx, Collector<Tuple3<String,String,Integer>> out) throws Exception {
            for (Trade trade : state.get()) {
                if (isSelfTrade(trade, value.f2)) {
                    log.debug(trade.getInstrumentID()+"      --------------------     "+value.f2.getInstrumentID());
                    counter.update(counter.value() + 1);
                    out.collect(Tuple3.of(value.f0,value.f1,counter.value()));
                    break;
                }
            }
            state.add(value.f2);
            lastModified.update(ctx.timestamp());
            ctx.timerService().registerEventTimeTimer(lastModified.value() + CLEANGAP);
        }
        private boolean isSelfTrade(Trade trade1,Trade trade2){
            return  trade1.getTradeVolume() == trade2.getTradeVolume()&&!trade1.getDirection().equals(trade2.getDirection()) &&
                    trade1.getTradeNo().equals(trade2.getTradeNo()) &&trade1.getInstrumentID().equals(trade2.getInstrumentID()) &&
                    trade1.getHedgeFlag().equals(trade2.getHedgeFlag()) &&trade1.getTradeAmount() == trade2.getTradeAmount() &&
                    trade1.getTradeTime().equals(trade2.getTradeTime()) &&trade1.getTradePrice() == trade2.getTradePrice();
        }
        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<Tuple3<String, String, Integer>> out) throws Exception {
            if (timestamp == lastModified.value() + CLEANGAP) {
                state.clear();
                counter.clear();
            }
        }
    }

    public static class FilterAndCleanTrade implements FlatMapFunction<String, Tuple3<String,String, Trade>>{
        @Override
        public void flatMap(String value, Collector<Tuple3<String,String,Trade>> out){
            JsonPkg jsonPkg= jsonUtil.str2JsonPkg(value);
            if (jsonPkg.getType()== JsonType.Trade){
                Trade trade = jsonUtil.json2Trade(jsonPkg.getJson());
                out.collect(Tuple3.of(trade.getInvestorID(),trade.getExchangeID(),trade));
            }
        }
    }

    private static class FilterAndCleanControlGroup implements FlatMapFunction<String,InvestorControlGroup>{
        @Override
        public void flatMap(String value, Collector<InvestorControlGroup> out){
            JsonPkg jsonPkg = jsonUtil.str2JsonPkg(value);
            if (jsonPkg.getType()==JsonType.InvestorControlGroup){
                InvestorControlGroup investorControlGroup= jsonUtil.json2InvestorControlGroup(jsonPkg.getJson());
                out.collect(investorControlGroup);
            }
        }
    }
    public static void main(String[] args){
        tradeWarnBootStrap(args);
    }
}
