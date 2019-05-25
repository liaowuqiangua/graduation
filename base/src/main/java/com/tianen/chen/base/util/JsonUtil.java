package com.tianen.chen.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianen.chen.base.conf.JacksonConfig;
import com.tianen.chen.base.entity.JsonPkg;
import com.tianen.chen.base.entity.JsonType;
import com.tianen.chen.base.pojo.*;
import com.tianen.chen.base.warn.MessageWarning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/19 14:41
 * @description :${description}
 */
public class JsonUtil {
    private final ObjectMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public JsonUtil(){
        mapper = new JacksonConfig().initObjectMapper();
    }

    public String Instrument2Json(Instrument instrument) throws JsonProcessingException{
        return mapper.writeValueAsString(instrument);
    }
    public String Investor2Json(Investor investor) throws JsonProcessingException{
        return mapper.writeValueAsString(investor);
    }
    public String MarketData2Json(MarketData marketData) throws JsonProcessingException{
        return mapper.writeValueAsString(marketData);
    }
    public String TimeScaleWarning2Json(TimeScaleWarning timeScaleWarning) throws JsonProcessingException{
        return mapper.writeValueAsString(timeScaleWarning);
    }
    public String Trade2Json(Trade trade)  throws JsonProcessingException{
        return mapper.writeValueAsString(trade);
    }
    public String TradeWarning2Json(TradeWarning tradeWarning) throws JsonProcessingException{
        return mapper.writeValueAsString(tradeWarning);
    }
    public String InvestorControlGroup2Json(InvestorControlGroup investorControlGroup) throws JsonProcessingException{
        return mapper.writeValueAsString(investorControlGroup);
    }
    public String access2Json(Access access) throws JsonProcessingException{
        return mapper.writeValueAsString(access);
    }
    public Instrument json2Instrument(String json){
        try {
            return mapper.readValue(json,Instrument.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("json2Instrument error , json :{}",json);
            return null;
        }
    }
    public Investor json2Investor(String json){
        try {
            return mapper.readValue(json,Investor.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("json2Investor error , json :{}",json);
            return null;
        }
    }
    public MarketData json2MarketData(String json){
        try {
            return mapper.readValue(json,MarketData.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("json2MarketData error , json :{}",json);
            return null;
        }
    }
    public TimeScaleWarning json2TimeScaleWarning(String json){
        try {
            return mapper.readValue(json,TimeScaleWarning.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("json2TimeScaleWarning error , json :{}",json);
            return null;
        }
    }
    public Trade json2Trade(String json){
        try {
            return mapper.readValue(json,Trade.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("json2Trade error , json :{}",json);
            return null;
        }
    }
    public TradeWarning json2TradeWarning(String json){
        try {
            return mapper.readValue(json,TradeWarning.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("json2TradeWarning error , json :{}",json);
            return null;
        }
    }
    public InvestorControlGroup json2InvestorControlGroup(String json){
        try {
            return mapper.readValue(json,InvestorControlGroup.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("json2InvestorControlGroup error , json :{}",json);
            return null;
        }
    }
    public JsonPkg str2JsonPkg(String str){
        try {
            return mapper.readValue(str, JsonPkg.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("str2JsonPkg error , json :{}",str);
            return null;
        }
    }
    public String instrument2JsonPkgStr(Instrument instrument){
        try {
            return mapper.writeValueAsString(new JsonPkg(JsonType.Instrument,Instrument2Json(instrument)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("instrument2JsonPkg error , {}",instrument);
            return null;
        }
    }
    public String investor2JsonPkgStr(Investor investor){
        try {
            return mapper.writeValueAsString(new JsonPkg(JsonType.Investor,Investor2Json(investor)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("investor2JsonPkgStr error , {}",investor);
            return null;
        }
    }
    public String marketData2JsonPkgStr(MarketData marketData){
        try {
            return mapper.writeValueAsString(new JsonPkg(JsonType.MarketData,MarketData2Json(marketData)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("marketData2JsonPkgStr error , {}",marketData);
            return null;
        }
    }
    public String timeScaleWarning2JsonPkgStr(TimeScaleWarning timeScaleWarning){
        try {
            return mapper.writeValueAsString(new JsonPkg(JsonType.TimeScaleWarning,TimeScaleWarning2Json(timeScaleWarning)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("timeScaleWarning2JsonPkgStr error , {}",timeScaleWarning);
            return null;
        }
    }
    public String trade2JsonPkgStr(Trade trade){
        try {
            return mapper.writeValueAsString(new JsonPkg(JsonType.Trade,Trade2Json(trade)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("trade2JsonPkgStr error , {}",trade);
            return null;
        }
    }
    public String tradeWarning2JsonPkgStr(TradeWarning tradeWarning){
        try {
            return mapper.writeValueAsString(new JsonPkg(JsonType.TradeWarning,TradeWarning2Json(tradeWarning)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("tradeWarning2JsonPkgStr error , {}",tradeWarning);
            return null;
        }
    }
    public String investorControlGroup2JsonPkgStr(InvestorControlGroup investorControlGroup){
        try {
            return mapper.writeValueAsString(new JsonPkg(JsonType.InvestorControlGroup,InvestorControlGroup2Json(investorControlGroup)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("investorControlGroup2JsonPkgStr error , {}",investorControlGroup);
            return null;
        }
    }

    public String messageWarning2Json(MessageWarning messageWarning){
        try {
            return mapper.writeValueAsString(messageWarning);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("messageWarning2Json error , {}",messageWarning);
            return null;
        }
    }

    public String any2JsonPkgStr(String any,JsonType type){
        JsonPkg jsonPkg = new JsonPkg(type,any);
        try {
            return mapper.writeValueAsString(jsonPkg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("any2JsonPkgStr error , any : {} jsonType : {}",any,type);
            return null;
        }
    }

    public String buildErrorJsonPkgStr(String msg){
        if (Objects.isNull(msg)){
            msg = "";
        }
        JsonPkg jsonPkg = new JsonPkg(JsonType.Error,msg);
        try {
            return mapper.writeValueAsString(jsonPkg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String access2JsonPkgStr(Access access){
        try {
            return mapper.writeValueAsString(new JsonPkg(JsonType.Access,access2Json(access)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("access2JsonPkgStr error , {}",access);
            return null;
        }
    }

}
