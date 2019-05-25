package com.tianen.chen.push.service.source;

import com.tianen.chen.push.service.config.Config;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Consumer extends Thread{
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    private final ChannelGroup channelGroup;
    public Consumer(ChannelGroup channelGroup){
        this.channelGroup = channelGroup;
    }
    @Override
    public void run(){
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(Config.getProp())) {
            consumer.subscribe(Config.TOPIC);
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    log.debug("send msg :{}",record.value());
                    channelGroup.forEach(x->x.writeAndFlush(new TextWebSocketFrame(record.value())));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
