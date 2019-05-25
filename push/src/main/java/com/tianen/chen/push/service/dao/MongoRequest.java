package com.tianen.chen.push.service.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import com.tianen.chen.base.conf.Config;
import com.tianen.chen.base.pojo.Access;
import com.tianen.chen.base.pojo.MarketData;
import com.tianen.chen.base.pojo.Trade;
import com.tianen.chen.base.util.JsonUtil;
import io.netty.util.internal.StringUtil;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.*;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/24 21:51
 * @description :${description}
 */
public class MongoRequest {
    private MongoClient mongoClient = null;
    private MongoCollection<Document> collection = null ;
    private final JsonUtil jsonUtil = new JsonUtil();

    public MongoRequest(String collectionName){
        this.mongoClient = MongoUtil.getMongoClients();
        this.collection = mongoClient.getDatabase(Config.DATABASE).getCollection(collectionName);
    }
    public List<MarketData> getMarketDataEntity(String instrumentID,int limit){

        FindIterable<Document> documents= collection
                .find(eq("instrumentID",instrumentID))
                .sort(Sorts.descending("updateTime"))
                .limit(limit);
        return MongoUtil.transDocumentToEntity(documents,MarketData.class);
    }
    public String getMarketDataJson(String instrumentID,int limit){
        if (StringUtil.isNullOrEmpty(instrumentID)){
            return null;
        }
        FindIterable<Document> documents= collection
                .find(eq("instrumentID",instrumentID))
                .sort(Sorts.descending("updateTime"))
                .limit(limit);
        return MongoUtil.transDocumentToJson(documents,MarketData.class);
    }
    public long getTradeCount(String instrumentID){
        if (StringUtil.isNullOrEmpty(instrumentID)){
            return 0;
        }
        return collection.countDocuments(eq("instrumentID",instrumentID));
    }

    public String getTradeJson(String instrumentID){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        String LT = formatter.format(calendar.getTime());
        calendar.add(Calendar.MONTH,-1);
        String GTE = formatter.format(calendar.getTime());
        FindIterable<Document> documents= collection
                .find(and(
                        eq("instrumentID",instrumentID)
                        ,gte("tradeDate", GTE)
                        ,lte("tradeDate", LT)))
                .sort(Sorts.ascending("tradeDate"));
        return MongoUtil.transDocumentToJson(documents,Trade.class);
    }

    public void saveAccessInfo(Access access){
        try {
            collection.insertOne(Document.parse(jsonUtil.access2Json(access)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String getAccessJson(){
        long GTE = System.currentTimeMillis()-24*3600*1000;
        FindIterable<Document> documents= collection
                .find(gte("timestamp", GTE));
        return MongoUtil.transDocumentToJson(documents,Access.class);
    }

    public void close(){
        if (Objects.nonNull(mongoClient))
            MongoUtil.closeMongoClient(mongoClient);
    }
}
