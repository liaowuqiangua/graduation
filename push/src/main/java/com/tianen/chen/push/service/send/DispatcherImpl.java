package com.tianen.chen.push.service.send;

import com.tianen.chen.base.entity.JsonPkg;
import com.tianen.chen.base.entity.JsonType;
import com.tianen.chen.base.util.JsonUtil;
import com.tianen.chen.push.service.dao.MongoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/25 9:56
 * @description :${description}
 */
public class DispatcherImpl implements Dispatcher<String> {
    private JsonUtil jsonUtil = new JsonUtil();
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public String dispatch(String s) {
        JsonPkg jsonPkg = jsonUtil.str2JsonPkg(s);
        if (Objects.isNull(jsonPkg)){
            log.error("Illegal Json string :{}",s);
            return jsonUtil.buildErrorJsonPkgStr("Illegal Json string "+s);
        }
        //MongoRequest 还能优化
        switch (jsonPkg.getType()){
            case InitTrade:
                MongoRequest mongoRequest1 = new MongoRequest("trade");
                String any1 = mongoRequest1.getTradeJson(jsonPkg.getJson());
                return jsonUtil.any2JsonPkgStr(any1, JsonType.InitTrade);
            case InitQuotes:
                MongoRequest mongoRequest2 = new MongoRequest("quotes");
                String any2 = mongoRequest2.getMarketDataJson(jsonPkg.getJson(),100);
                return jsonUtil.any2JsonPkgStr(any2, JsonType.InitQuotes);
            case Scatter:
                MongoRequest mongoRequest3 = new MongoRequest("trade");
                String any3 = mongoRequest3.getTradeJson(jsonPkg.getJson());
                return jsonUtil.any2JsonPkgStr(any3, JsonType.Scatter);
            case Access:
                MongoRequest mongoRequest4 = new MongoRequest("access");
                String any4 = mongoRequest4.getAccessJson();
                return jsonUtil.any2JsonPkgStr(any4, JsonType.Access);
            default:
                log.error("Illegal Json string :{}",s);
                return jsonUtil.buildErrorJsonPkgStr("Illegal json type");
        }
    }
}
