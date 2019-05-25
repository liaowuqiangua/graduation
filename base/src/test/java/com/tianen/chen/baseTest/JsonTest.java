package com.tianen.chen.baseTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tianen.chen.base.entity.JsonPkg;
import com.tianen.chen.base.entity.JsonType;
import com.tianen.chen.base.pojo.*;
import com.tianen.chen.base.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/19 16:02
 * @description :${description}
 */
public class JsonTest {
    private static final JsonUtil jsonUtil = new JsonUtil();
    public static void main(String[] args) throws JsonProcessingException {
        String jsonPkgStr = jsonUtil.instrument2JsonPkgStr(new Instrument());
        System.out.println(jsonPkgStr);
        JsonPkg jsonPkg = jsonUtil.str2JsonPkg(jsonPkgStr);
        if (jsonPkg.getType().equals(JsonType.Instrument)){
            Instrument instrument = jsonUtil.json2Instrument(jsonPkg.getJson());
            System.out.println(instrument);
        }
        else {
            System.out.println(" bad ");
        }


        List<String> list = new ArrayList<>();
        list.add(jsonUtil.Instrument2Json(new Instrument()));
        list.add(jsonUtil.Investor2Json(new Investor()));
        list.add(jsonUtil.MarketData2Json(new MarketData()));
        list.add(jsonUtil.TimeScaleWarning2Json(new TimeScaleWarning()));
        list.add(jsonUtil.Trade2Json(new Trade()));
        list.add(jsonUtil.TradeWarning2Json(new TradeWarning()));
        list.forEach(System.out::print);
    }
}
