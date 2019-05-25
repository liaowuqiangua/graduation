package com.tianen.chen.push.service.dao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.Function;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.tianen.chen.base.conf.Config;
import com.tianen.chen.base.util.JsonUtil;
import com.tianen.chen.push.service.config.JacksonConfig;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class MongoUtil {
    private static MongoClient mongoClient = null;
    private static ObjectMapper om ;
    private static Logger log ;
    private static JsonWriterSettings jsb ;
    private static JsonUtil jsonUtil;
    static {
        jsonUtil = new JsonUtil();
        om = new JacksonConfig().initObjectMapper();
        log = LoggerFactory.getLogger(MongoUtil.class);
        jsb = JsonWriterSettings.builder()
                .outputMode(JsonMode.RELAXED)
                .build();
    }
    static MongoClient getMongoClients() {
        if (mongoClient == null) {
            synchronized (MongoClient.class) {
                if (mongoClient == null) {
                    ConnectionString connectionString = new ConnectionString(Config.CONNECT);
                    mongoClient = MongoClients.create(connectionString);
                }
            }
        }
        return mongoClient;
    }

    public static <T> List<T> transDocumentToEntity(FindIterable<Document> document, Class<T> s) {
        List<T> res = new ArrayList<>();
        document.map(new Function<Document, Void>() {
            @Override
            public Void apply(Document t) {
                try {
                    res.add(om.readValue(t.toJson(jsb), s));
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("transDocumentToJson error ! {} , error msg :{}",t.toString(),e.getMessage());
                }
                return null;
            }
        }).forEach(new Consumer<Void>() {
            @Override
            public void accept(Void t) {
            }
        });
        return res;
    }
    public static <T> String transDocumentToJson(FindIterable<Document> document, Class<T> s) {
        try {
            List<T> list = MongoUtil.transDocumentToEntity(document,s);
            return om.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("transDocumentToJson error ! {} , error msg {}",document.toString(),e.getMessage());
            return null;
        }
    }
    static void closeMongoClient(MongoClient mongoClient) {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
