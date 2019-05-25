package com.tianen.chen.push.service.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Config {
    private static final Properties props = getProp();
    public static final List<String> TOPIC = Arrays.asList(props.getProperty("topic","te-all,te-warn,te-quotes").split(","));
    public static Properties getProp(){
        Properties prop = new Properties();
        try {
            prop.load(Config.class.getResourceAsStream("/kafka.properties"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
