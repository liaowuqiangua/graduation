package com.tianen.chen.sink;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tianen.chen.base.conf.Config;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.bson.Document;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/21 16:01
 * @description :${description}
 */
public class MongoSink extends RichSinkFunction<String> {
    private MongoDatabase database = null;
    private MongoCollection<Document> collection = null ;
    private final String collectionName;
    public MongoSink(String collectionName){
        this.collectionName = collectionName;
    }
    @Override
    public void invoke(String value, Context context){
        collection.insertOne(Document.parse(value));
    }

    @Override
    public void close(){
//        MongoUtil.closeMongoClient(mongoClient);
    }

    @Override
    public void open(Configuration parameters){
        database = new MongoUtil().getMongoDatabase();
        collection = database.getCollection(collectionName);
    }
}
