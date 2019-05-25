package com.tianen.chen.base.pojo;


import java.util.Objects;

/**
*  某一特定种类（即期货、期权或期货期权）的一个衍生品种
*/
public class Instrument{

    
    /**
    *  合约在系统中的编号  -  key=[yes]
    */
    private String instrumentID = "-";
    
    
    /**
    *  合约的名称  -  key=[-]
    */
    private String instrumentName = "-";
    
    
    /**
    *  交易所代码  -  key=[yes]
    */
    private String exchangeID = "-";
    
    
    /**
    *  合约在交易所的代码  -  key=[-]
    */
    private String exchangeInstID = "-";
    
    
    /**
    *  品种  -  key=[-]
    */
    private String variCode = "-";
    
    
    /**
    *  交割期  -  key=[-]
    */
    private String delivery = "-";
    
    
    /**
    *  合约乘数  -  key=[-]
    */
    private int mult = 0;
    
    /**
    *  最大下单量  -  key=[-]
    */
    private int maxOrder = 0;
    
    /**
    *  主力  -  key=[-]
    *   NoMain-非主力-0
    *   Main-主力-1
    */
    private String mainForce = "-";
    
    
    /**
    *  新合约创建时间  -  key=[-]
    */
    private String createDate = "-";
    
    
    /**
    *  上市日  -  key=[-]
    */
    private String openDate = "-";
    
    
    /**
    *  合约到期时间  -  key=[-]
    */
    private String expireDate = "-";
    
    
    /**
    *  期权最低保障系数  -  key=[-]
    */
    private double optMindeposit = 0;
    
    /**
    *  期权行权价  -  key=[-]
    */
    private double optStrikeprice = 0;
    
    /**
    *  标的合约  -  key=[-]
    */
    private String relativeCode = "-";
    
    
    /**
    *  了结到期未平仓合约的开始日期  -  key=[-]
    */
    private String startDelivDate = "-";
    
    
    /**
    *  了结到期未平仓合约的结束日期  -  key=[-]
    */
    private String endDelivDate = "-";
    
    
    /**
    *  分为合约未上市、上市、停牌、到期4个状态  -  key=[-]
    *   NotStart-未上市-0
    *   Started-上市-1
    *   Pause-停牌-2
    *   Expired-到期-3
    */
    private String instLifePhase = "-";
    
    
    /**
    *  当前是否交易  -  key=[-]
    */
    private int isTrading = 0;
    
    /**
    *  交易类型  -  key=[-]
    *   Futuer-期货-0
    *   Option-期权-1
    */
    private String tradeClass = "-";
    
    
    /**
    *  交割年份  -  key=[-]
    */
    private int deliveryYear = 0;
    
    /**
    *  交割月  -  key=[-]
    */
    private int deliveryMonth = 0;
    
    /**
    *  最小变动价位  -  key=[-]
    */
    private double priceTick = 0;
    
    /**
    *  是否参与单向大边  -  key=[-]
    *   NO-参与-0
    *   YES-不参与-1
    */
    private String maxMarginSideAlgorithm = "-";
    
    
    /**
    *  期权类型  -  key=[-]
    *   C-看涨-C
    *   P-看跌-P
    */
    private String optionsType = "-";
    
    
    /**
    *  合约基础商品乘数  -  key=[-]
    */
    private int underlyingMultiple = 0;
    
    /**
    *  组合类型  -  key=[-]
    *   Future-期货组合-0
    *   BUL-垂直价差BUL-1
    *   BER-垂直价差BER-2
    *   STD-跨式组合-3
    *   STG-宽跨式组合-4
    *   PRT-备兑组合-5
    *   CLD-时间价差组合-6
    */
    private String combinationType = "-";

    public String getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(String instrumentID) {
        this.instrumentID = instrumentID;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(String exchangeID) {
        this.exchangeID = exchangeID;
    }

    public String getExchangeInstID() {
        return exchangeInstID;
    }

    public void setExchangeInstID(String exchangeInstID) {
        this.exchangeInstID = exchangeInstID;
    }

    public String getVariCode() {
        return variCode;
    }

    public void setVariCode(String variCode) {
        this.variCode = variCode;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getMult() {
        return mult;
    }

    public void setMult(int mult) {
        this.mult = mult;
    }

    public int getMaxOrder() {
        return maxOrder;
    }

    public void setMaxOrder(int maxOrder) {
        this.maxOrder = maxOrder;
    }

    public String getMainForce() {
        return mainForce;
    }

    public void setMainForce(String mainForce) {
        this.mainForce = mainForce;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public double getOptMindeposit() {
        return optMindeposit;
    }

    public void setOptMindeposit(double optMindeposit) {
        this.optMindeposit = optMindeposit;
    }

    public double getOptStrikeprice() {
        return optStrikeprice;
    }

    public void setOptStrikeprice(double optStrikeprice) {
        this.optStrikeprice = optStrikeprice;
    }

    public String getRelativeCode() {
        return relativeCode;
    }

    public void setRelativeCode(String relativeCode) {
        this.relativeCode = relativeCode;
    }

    public String getStartDelivDate() {
        return startDelivDate;
    }

    public void setStartDelivDate(String startDelivDate) {
        this.startDelivDate = startDelivDate;
    }

    public String getEndDelivDate() {
        return endDelivDate;
    }

    public void setEndDelivDate(String endDelivDate) {
        this.endDelivDate = endDelivDate;
    }

    public String getInstLifePhase() {
        return instLifePhase;
    }

    public void setInstLifePhase(String instLifePhase) {
        this.instLifePhase = instLifePhase;
    }

    public int getIsTrading() {
        return isTrading;
    }

    public void setIsTrading(int isTrading) {
        this.isTrading = isTrading;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public int getDeliveryYear() {
        return deliveryYear;
    }

    public void setDeliveryYear(int deliveryYear) {
        this.deliveryYear = deliveryYear;
    }

    public int getDeliveryMonth() {
        return deliveryMonth;
    }

    public void setDeliveryMonth(int deliveryMonth) {
        this.deliveryMonth = deliveryMonth;
    }

    public double getPriceTick() {
        return priceTick;
    }

    public void setPriceTick(double priceTick) {
        this.priceTick = priceTick;
    }

    public String getMaxMarginSideAlgorithm() {
        return maxMarginSideAlgorithm;
    }

    public void setMaxMarginSideAlgorithm(String maxMarginSideAlgorithm) {
        this.maxMarginSideAlgorithm = maxMarginSideAlgorithm;
    }

    public String getOptionsType() {
        return optionsType;
    }

    public void setOptionsType(String optionsType) {
        this.optionsType = optionsType;
    }

    public int getUnderlyingMultiple() {
        return underlyingMultiple;
    }

    public void setUnderlyingMultiple(int underlyingMultiple) {
        this.underlyingMultiple = underlyingMultiple;
    }

    public String getCombinationType() {
        return combinationType;
    }

    public void setCombinationType(String combinationType) {
        this.combinationType = combinationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return mult == that.mult &&
                maxOrder == that.maxOrder &&
                Double.compare(that.optMindeposit, optMindeposit) == 0 &&
                Double.compare(that.optStrikeprice, optStrikeprice) == 0 &&
                isTrading == that.isTrading &&
                deliveryYear == that.deliveryYear &&
                deliveryMonth == that.deliveryMonth &&
                Double.compare(that.priceTick, priceTick) == 0 &&
                underlyingMultiple == that.underlyingMultiple &&
                Objects.equals(instrumentID, that.instrumentID) &&
                Objects.equals(instrumentName, that.instrumentName) &&
                Objects.equals(exchangeID, that.exchangeID) &&
                Objects.equals(exchangeInstID, that.exchangeInstID) &&
                Objects.equals(variCode, that.variCode) &&
                Objects.equals(delivery, that.delivery) &&
                Objects.equals(mainForce, that.mainForce) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(openDate, that.openDate) &&
                Objects.equals(expireDate, that.expireDate) &&
                Objects.equals(relativeCode, that.relativeCode) &&
                Objects.equals(startDelivDate, that.startDelivDate) &&
                Objects.equals(endDelivDate, that.endDelivDate) &&
                Objects.equals(instLifePhase, that.instLifePhase) &&
                Objects.equals(tradeClass, that.tradeClass) &&
                Objects.equals(maxMarginSideAlgorithm, that.maxMarginSideAlgorithm) &&
                Objects.equals(optionsType, that.optionsType) &&
                Objects.equals(combinationType, that.combinationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instrumentID, instrumentName, exchangeID, exchangeInstID, variCode, delivery, mult, maxOrder, mainForce, createDate, openDate, expireDate, optMindeposit, optStrikeprice, relativeCode, startDelivDate, endDelivDate, instLifePhase, isTrading, tradeClass, deliveryYear, deliveryMonth, priceTick, maxMarginSideAlgorithm, optionsType, underlyingMultiple, combinationType);
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "instrumentID='" + instrumentID + '\'' +
                ", instrumentName='" + instrumentName + '\'' +
                ", exchangeID='" + exchangeID + '\'' +
                ", exchangeInstID='" + exchangeInstID + '\'' +
                ", variCode='" + variCode + '\'' +
                ", delivery='" + delivery + '\'' +
                ", mult=" + mult +
                ", maxOrder=" + maxOrder +
                ", mainForce='" + mainForce + '\'' +
                ", createDate='" + createDate + '\'' +
                ", openDate='" + openDate + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", optMindeposit=" + optMindeposit +
                ", optStrikeprice=" + optStrikeprice +
                ", relativeCode='" + relativeCode + '\'' +
                ", startDelivDate='" + startDelivDate + '\'' +
                ", endDelivDate='" + endDelivDate + '\'' +
                ", instLifePhase='" + instLifePhase + '\'' +
                ", isTrading=" + isTrading +
                ", tradeClass='" + tradeClass + '\'' +
                ", deliveryYear=" + deliveryYear +
                ", deliveryMonth=" + deliveryMonth +
                ", priceTick=" + priceTick +
                ", maxMarginSideAlgorithm='" + maxMarginSideAlgorithm + '\'' +
                ", optionsType='" + optionsType + '\'' +
                ", underlyingMultiple=" + underlyingMultiple +
                ", combinationType='" + combinationType + '\'' +
                '}';
    }
}
