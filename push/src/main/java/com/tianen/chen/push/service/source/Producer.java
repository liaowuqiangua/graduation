package com.tianen.chen.push.service.source;

import com.tianen.chen.base.pojo.MarketData;
import com.tianen.chen.base.pojo.TimeScaleWarning;
import com.tianen.chen.base.pojo.Trade;
import com.tianen.chen.base.pojo.TradeWarning;
import com.tianen.chen.base.util.JsonUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Producer extends Thread{
    private static final JsonUtil jsonUtil = new JsonUtil();
    private List<String> msgs;
    List<String> instrumentID = Arrays.asList("cu1910","al1907","zn1909","au1912");
    private String topic;
    private Producer(List<String> msgs,String topic){
        this.msgs = msgs;
        this.topic = topic;
    }

    @Override
    public void run() {
        KafkaProducer producer = Producers.getProducer();
        MarketData marketData = new MarketData();
        marketData.setInstrumentID("cu1910");
        marketData.setLastPrice(700);
        String msg0 = jsonUtil.marketData2JsonPkgStr(marketData);
        TimeScaleWarning timeScaleWarning = new TimeScaleWarning();
        timeScaleWarning.setInstrumentID(instrumentID.get(new Random().nextInt(4)));
        timeScaleWarning.setInstrumentID("cu1910");
        String msg1 = jsonUtil.timeScaleWarning2JsonPkgStr(timeScaleWarning);

        Trade trade = new Trade();
        trade.setDirection("2");
        trade.setInvestorID("foo");
        trade.setInstrumentID("cu1910");
        trade.setExchangeID("SHFE");

        String msg2 = jsonUtil.trade2JsonPkgStr(trade);

        TradeWarning tradeWarning = new TradeWarning();
        tradeWarning.setInvestorID("foo");
        tradeWarning.setScale1(1);
        tradeWarning.setScale2(2);
        tradeWarning.setScale3(3);
        tradeWarning.setSelfTradeConsolidate("1");
        tradeWarning.setExchangeID("SHFE");
        String msg3 = jsonUtil.tradeWarning2JsonPkgStr(tradeWarning);

//        msgs.add(msg0);
//        msgs.add(msg1);
        msgs.add(msg2);
        msgs.add(msg3);
        for (String msg:msgs) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, null, msg);
            try {
                producer.send(record);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        List<String> msgs = new ArrayList<>();
//        msgs.add(msg);
        new Producer(msgs,"te-warn").start();
    }
}
