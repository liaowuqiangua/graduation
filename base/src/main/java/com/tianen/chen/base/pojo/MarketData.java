package com.tianen.chen.base.pojo;

import java.util.Objects;

/**
*  说明合约基本的行情信息
*/
public class MarketData{

    
    /**
    *  交易日  -  key=[yes]
    */
    private String tradingDay = "-";
    
    
    /**
    *  一个交易所的编号  -  key=[yes]
    */
    private String exchangeID = "-";
    
    
    /**
    *  合约在系统中的编号  -  key=[yes]
    */
    private String instrumentID = "-";
    
    
    /**
    *  当日该合约交易期间的最新成交价格  -  key=[-]
    */
    private double lastPrice = 0;
    
    /**
    *  上一日的结算价  -  key=[-]
    */
    private double preSettlementPrice = 0;
    
    /**
    *  上一日收盘价  -  key=[-]
    */
    private double preClosePrice = 0;
    
    /**
    *  前最后持仓量，双向计算  -  key=[-]
    */
    private double preOpenInterest = 0;
    
    /**
    *  该期货合约开市前五分钟内经集合竞价产生的成交价格  -  key=[-]
    */
    private double openPrice = 0;
    
    /**
    *  指一定时间内该合约成交价中的最高成交价格  -  key=[-]
    */
    private double highestPrice = 0;
    
    /**
    *  指一定时间内该合约成交价中的最低成交价格  -  key=[-]
    */
    private double lowestPrice = 0;
    
    /**
    *  该合约在当日交易期价所有成交合约的双边数量  -  key=[-]
    */
    private int volume = 0;
    
    /**
    *  该合约完成交易的市值  -  key=[-]
    */
    private double turnover = 0;
    
    /**
    *  最后持仓量，双向计算  -  key=[-]
    */
    private double openInterest = 0;
    
    /**
    *  该合约当日交易的最后一笔成交价格  -  key=[-]
    */
    private double closePrice = 0;
    
    /**
    *  该合约当日成交价格按成交量的加权平均价，当日无成交的，按上一日结算价  -  key=[-]
    */
    private double settlementPrice = 0;
    
    /**
    *  涨停板价  -  key=[-]
    */
    private double upperLimitPrice = 0;
    
    /**
    *  跌停板价  -  key=[-]
    */
    private double lowerLimitPrice = 0;
    
    /**
    *  期权用的昨日虚实度  -  key=[-]
    */
    private double preDelta = 0;
    
    /**
    *  期权用的今日虚实度  -  key=[-]
    */
    private double currDelta = 0;
    
    /**
    *  最后修改时间  -  key=[-]
    */
    private String updateTime = "-";
    
    
    /**
    *  最后修改毫秒  -  key=[-]
    */
    private int updateMillisec = 0;

    public String getTradingDay() {
        return tradingDay;
    }

    public void setTradingDay(String tradingDay) {
        this.tradingDay = tradingDay;
    }

    public String getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(String exchangeID) {
        this.exchangeID = exchangeID;
    }

    public String getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(String instrumentID) {
        this.instrumentID = instrumentID;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getPreSettlementPrice() {
        return preSettlementPrice;
    }

    public void setPreSettlementPrice(double preSettlementPrice) {
        this.preSettlementPrice = preSettlementPrice;
    }

    public double getPreClosePrice() {
        return preClosePrice;
    }

    public void setPreClosePrice(double preClosePrice) {
        this.preClosePrice = preClosePrice;
    }

    public double getPreOpenInterest() {
        return preOpenInterest;
    }

    public void setPreOpenInterest(double preOpenInterest) {
        this.preOpenInterest = preOpenInterest;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getOpenInterest() {
        return openInterest;
    }

    public void setOpenInterest(double openInterest) {
        this.openInterest = openInterest;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(double settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public double getUpperLimitPrice() {
        return upperLimitPrice;
    }

    public void setUpperLimitPrice(double upperLimitPrice) {
        this.upperLimitPrice = upperLimitPrice;
    }

    public double getLowerLimitPrice() {
        return lowerLimitPrice;
    }

    public void setLowerLimitPrice(double lowerLimitPrice) {
        this.lowerLimitPrice = lowerLimitPrice;
    }

    public double getPreDelta() {
        return preDelta;
    }

    public void setPreDelta(double preDelta) {
        this.preDelta = preDelta;
    }

    public double getCurrDelta() {
        return currDelta;
    }

    public void setCurrDelta(double currDelta) {
        this.currDelta = currDelta;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateMillisec() {
        return updateMillisec;
    }

    public void setUpdateMillisec(int updateMillisec) {
        this.updateMillisec = updateMillisec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketData that = (MarketData) o;
        return Double.compare(that.lastPrice, lastPrice) == 0 &&
                Double.compare(that.preSettlementPrice, preSettlementPrice) == 0 &&
                Double.compare(that.preClosePrice, preClosePrice) == 0 &&
                Double.compare(that.preOpenInterest, preOpenInterest) == 0 &&
                Double.compare(that.openPrice, openPrice) == 0 &&
                Double.compare(that.highestPrice, highestPrice) == 0 &&
                Double.compare(that.lowestPrice, lowestPrice) == 0 &&
                volume == that.volume &&
                Double.compare(that.turnover, turnover) == 0 &&
                Double.compare(that.openInterest, openInterest) == 0 &&
                Double.compare(that.closePrice, closePrice) == 0 &&
                Double.compare(that.settlementPrice, settlementPrice) == 0 &&
                Double.compare(that.upperLimitPrice, upperLimitPrice) == 0 &&
                Double.compare(that.lowerLimitPrice, lowerLimitPrice) == 0 &&
                Double.compare(that.preDelta, preDelta) == 0 &&
                Double.compare(that.currDelta, currDelta) == 0 &&
                updateMillisec == that.updateMillisec &&
                Objects.equals(tradingDay, that.tradingDay) &&
                Objects.equals(exchangeID, that.exchangeID) &&
                Objects.equals(instrumentID, that.instrumentID) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradingDay, exchangeID, instrumentID, lastPrice, preSettlementPrice, preClosePrice, preOpenInterest, openPrice, highestPrice, lowestPrice, volume, turnover, openInterest, closePrice, settlementPrice, upperLimitPrice, lowerLimitPrice, preDelta, currDelta, updateTime, updateMillisec);
    }

    @Override
    public String toString() {
        return "MarketData{" +
                "tradingDay='" + tradingDay + '\'' +
                ", exchangeID='" + exchangeID + '\'' +
                ", instrumentID='" + instrumentID + '\'' +
                ", lastPrice=" + lastPrice +
                ", preSettlementPrice=" + preSettlementPrice +
                ", preClosePrice=" + preClosePrice +
                ", preOpenInterest=" + preOpenInterest +
                ", openPrice=" + openPrice +
                ", highestPrice=" + highestPrice +
                ", lowestPrice=" + lowestPrice +
                ", volume=" + volume +
                ", turnover=" + turnover +
                ", openInterest=" + openInterest +
                ", closePrice=" + closePrice +
                ", settlementPrice=" + settlementPrice +
                ", upperLimitPrice=" + upperLimitPrice +
                ", lowerLimitPrice=" + lowerLimitPrice +
                ", preDelta=" + preDelta +
                ", currDelta=" + currDelta +
                ", updateTime='" + updateTime + '\'' +
                ", updateMillisec=" + updateMillisec +
                '}';
    }
}
