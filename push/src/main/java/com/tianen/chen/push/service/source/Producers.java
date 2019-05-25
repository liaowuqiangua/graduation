package com.tianen.chen.push.service.source;

import com.tianen.chen.base.conf.Config;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/3/21 16:12
 * @description :${description}
 */
public class Producers {
    private static KafkaProducer<String,String> producer = null;
    public static KafkaProducer getProducer(){
        if (producer==null){
            synchronized (KafkaProducer.class){
                if(producer==null){
                    producer = new KafkaProducer<>(Config.getProp());
                    return producer;
                }
            }
        }
        return producer;
    }
    public static void aftermath(){
        producer.flush();
        producer.close();
    }
}
