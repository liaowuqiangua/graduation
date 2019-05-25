package com.tianen.chen.base.conf;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = getProp();
//    public static final String CONNECT=properties.getProperty("connect");
    public static final String CONNECT="mongodb://127.0.0.1";
    public static final String DATABASE="test";
    public static final String KAFKA = "127.0.0.1:29092";

    public static Properties getProp(){
        Properties prop = new Properties();
        try {
            prop.load(Config.class.getResourceAsStream("/kafka.properties"));
//            prop.load(new FileInputStream("kafka.properties"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
