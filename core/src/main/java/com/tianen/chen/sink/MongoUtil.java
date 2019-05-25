package com.tianen.chen.sink;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.tianen.chen.base.conf.Config;

class MongoUtil {
    private static final ConnectionString connectionString = new ConnectionString(Config.CONNECT);
    private static MongoClient mongoClient = null;
    static MongoClient getMongoClients() {
        if (mongoClient == null) {
            synchronized (MongoClient.class) {
                if (mongoClient == null) {
                    mongoClient = MongoClients.create(connectionString);
                }
            }
        }
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase(){
        return getMongoClients().getDatabase(Config.DATABASE);
    }

    static void closeMongoClient(MongoClient mongoClient) {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
