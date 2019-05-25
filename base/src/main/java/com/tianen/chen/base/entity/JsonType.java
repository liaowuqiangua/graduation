package com.tianen.chen.base.entity;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/21 12:52
 * @description :${description}
 */
public enum JsonType {

    Instrument("Instrument"),
    Investor("Investor"),
    MarketData("MarketData"),
    TimeScaleWarning("TimeScaleWarning"),
    Trade("Trade"),
    InitTrade("InitTrade"),
    TradeWarning("TradeWarning"),
    InvestorControlGroup("InvestorControlGroup"),
    InitQuotes("InitQuotes"),
    Scatter("Scatter"),
    Access("Access"),
    Log("Log"),
    Warn("Warn"),
    Error("Error");

    private final String type;
    JsonType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
