package com.tianen.chen.push.service.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/25 9:37
 * @description :${description}
 */
public class JacksonConfig {
    public ObjectMapper initObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        om.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);// 大小写脱敏 默认为false 需要改为true
        return om;
    }
}
