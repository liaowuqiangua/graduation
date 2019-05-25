package com.tianen.chen.common;

import com.tianen.chen.base.pojo.TradeWarning;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/3/25 16:16
 * @description :${description}
 */
public class TradeWarnFakeRuleSource extends RichSourceFunction<TradeWarning> {
    private volatile boolean isRunning = false;
    @Override
    public void run(SourceContext<TradeWarning> ctx) throws Exception {
        while (isRunning){
            Thread.sleep(1000);
            TradeWarning tradeWarning = new TradeWarning();
            tradeWarning.setInvestorID("foo");
            tradeWarning.setScale1(1d);
            tradeWarning.setScale2(10d);
            tradeWarning.setScale3(100d);
//            Document document = new Document();
//            PojoToBson.updateDoc(tradeWarning,document);
            ctx.collectWithTimestamp(tradeWarning,System.currentTimeMillis());
        }
    }

    @Override
    public void open(Configuration parameters){
        isRunning = true;
    }

    @Override
    public void close(){
        isRunning = false;
    }

    @Override
    public void cancel() {
//        isRunning = false;
    }
}
