package com.tianen.chen.base.pojo;

import java.util.Objects;

/**
*  成交
*/
public class Trade {

    
    /**
    *  成交序号  -  key=[-]
    */
    private String tradeNo = "-";
    
    
    /**
    *  机构代码  -  key=[yes]
    */
    private String institutionID = "-";
    
    
    /**
    *  投资者代码  -  key=[yes]
    */
    private String investorID = "-";
    
    
    /**
    *  交易日  -  key=[-]
    */
    private String tradingDay = "-";
    
    
    /**
    *  委托序号  -  key=[-]
    */
    private String entrustNo = "-";
    
    
    /**
    *  交易所代码  -  key=[yes]
    */
    private String exchangeID = "-";
    
    
    /**
    *  交易编码  -  key=[no]
    */
    private String tradingCode = "-";
    
    
    /**
    *  某个交易所分配的某个席位的内部编号  -  key=[yes]
    */
    private String seatID = "-";
    
    
    /**
    *  合约编号  -  key=[-]
    */
    private String instrumentID = "-";
    
    
    /**
    *  报单编号  -  key=[-]
    */
    private String orderSysID = "-";
    
    
    /**
    *  本地报单编号  -  key=[-]
    */
    private String orderLocalID = "-";
    
    
    /**
    *  系统号  -  key=[-]
    */
    private String sysID = "-";
    
    
    /**
    *  新系统号  -  key=[-]
    */
    private String newSysID = "-";
    
    
    /**
    *  成交编号  -  key=[-]
    */
    private String tradeID = "-";
    
    
    /**
    *  买卖方向  -  key=[-]
    *   Buy-买-48
    *   Sell-卖-49
    */
    private String direction = "-";
    
    
    /**
    *  开平标志  -  key=[-]
    *   Open-开仓-48
    *   Close-平仓-49
    *   ForceClose-强平-50
    *   CloseToday-平今-51
    *   CloseYesterday-平昨-52
    *   ForceOff-强减-53
    *   LocalForceClose-本地强平-54
    */
    private String offsetFlag = "-";
    
    
    /**
    *  投机套保标志  -  key=[-]
    *   Speculation-投机-49
    *   Arbitrage-套利-50
    *   Hedge-套保-51
    */
    private String hedgeFlag = "-";
    
    
    /**
    *  成交价  -  key=[-]
    */
    private double tradePrice = 0;
    
    /**
    *  成交数量  -  key=[-]
    */
    private int tradeVolume = 0;
    
    /**
    *  成交时间  -  key=[-]
    */
    private String tradeTime = "-";
    
    
    /**
    *  成交金额  -  key=[-]
    */
    private double tradeAmount = 0;
    
    /**
    *  流中唯一的序列号  -  key=[-]
    */
    private int uniqSequenceNo = 0;
    
    /**
    *  报单引用  -  key=[-]
    */
    private String orderRef = "-";
    
    
    /**
    *  用户代码  -  key=[-]
    */
    private String userID = "-";
    
    
    /**
    *  会员代码  -  key=[-]
    */
    private String participantID = "-";
    
    
    /**
    *  客户代码  -  key=[-]
    */
    private String clientID = "-";
    
    
    /**
    *  成交时期  -  key=[-]
    */
    private String tradeDate = "-";
    
    
    /**
    *  成交类型  -  key=[-]
    *   Common-普通成交-0
    *   OptionsExecution-期权执行-1
    *   OTC-OTC成交-2
    *   EFPDerived-期转现衍生成交-3
    *   CombinationDerived-组合衍生成交-4
    */
    private String tradeType = "-";
    
    
    /**
    *  交易所交易员代码  -  key=[-]
    */
    private String traderID = "-";
    
    
    /**
    *  保证金  -  key=[-]
    */
    private double margin = 0;
    
    /**
    *  交易所保证金  -  key=[-]
    */
    private double exchangeMargin = 0;
    
    /**
    *  费用  -  key=[-]
    */
    private double fee = 0;
    
    /**
    *  权利金  -  key=[-]
    */
    private double optPremium = 0;
    
    /**
    *  序号  -  key=[-]
    */
    private int sequenceNo = 0;
    
    /**
    *  经纪公司报单编号  -  key=[-]
    */
    private int brokerOrderSeq = 0;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(String institutionID) {
        this.institutionID = institutionID;
    }

    public String getInvestorID() {
        return investorID;
    }

    public void setInvestorID(String investorID) {
        this.investorID = investorID;
    }

    public String getTradingDay() {
        return tradingDay;
    }

    public void setTradingDay(String tradingDay) {
        this.tradingDay = tradingDay;
    }

    public String getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(String entrustNo) {
        this.entrustNo = entrustNo;
    }

    public String getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(String exchangeID) {
        this.exchangeID = exchangeID;
    }

    public String getTradingCode() {
        return tradingCode;
    }

    public void setTradingCode(String tradingCode) {
        this.tradingCode = tradingCode;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public String getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(String instrumentID) {
        this.instrumentID = instrumentID;
    }

    public String getOrderSysID() {
        return orderSysID;
    }

    public void setOrderSysID(String orderSysID) {
        this.orderSysID = orderSysID;
    }

    public String getOrderLocalID() {
        return orderLocalID;
    }

    public void setOrderLocalID(String orderLocalID) {
        this.orderLocalID = orderLocalID;
    }

    public String getSysID() {
        return sysID;
    }

    public void setSysID(String sysID) {
        this.sysID = sysID;
    }

    public String getNewSysID() {
        return newSysID;
    }

    public void setNewSysID(String newSysID) {
        this.newSysID = newSysID;
    }

    public String getTradeID() {
        return tradeID;
    }

    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getOffsetFlag() {
        return offsetFlag;
    }

    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }

    public String getHedgeFlag() {
        return hedgeFlag;
    }

    public void setHedgeFlag(String hedgeFlag) {
        this.hedgeFlag = hedgeFlag;
    }

    public double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public int getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(int tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public double getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(double tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public int getUniqSequenceNo() {
        return uniqSequenceNo;
    }

    public void setUniqSequenceNo(int uniqSequenceNo) {
        this.uniqSequenceNo = uniqSequenceNo;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getParticipantID() {
        return participantID;
    }

    public void setParticipantID(String participantID) {
        this.participantID = participantID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTraderID() {
        return traderID;
    }

    public void setTraderID(String traderID) {
        this.traderID = traderID;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getExchangeMargin() {
        return exchangeMargin;
    }

    public void setExchangeMargin(double exchangeMargin) {
        this.exchangeMargin = exchangeMargin;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getOptPremium() {
        return optPremium;
    }

    public void setOptPremium(double optPremium) {
        this.optPremium = optPremium;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public int getBrokerOrderSeq() {
        return brokerOrderSeq;
    }

    public void setBrokerOrderSeq(int brokerOrderSeq) {
        this.brokerOrderSeq = brokerOrderSeq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Double.compare(trade.tradePrice, tradePrice) == 0 &&
                tradeVolume == trade.tradeVolume &&
                Double.compare(trade.tradeAmount, tradeAmount) == 0 &&
                uniqSequenceNo == trade.uniqSequenceNo &&
                Double.compare(trade.margin, margin) == 0 &&
                Double.compare(trade.exchangeMargin, exchangeMargin) == 0 &&
                Double.compare(trade.fee, fee) == 0 &&
                Double.compare(trade.optPremium, optPremium) == 0 &&
                sequenceNo == trade.sequenceNo &&
                brokerOrderSeq == trade.brokerOrderSeq &&
                Objects.equals(tradeNo, trade.tradeNo) &&
                Objects.equals(institutionID, trade.institutionID) &&
                Objects.equals(investorID, trade.investorID) &&
                Objects.equals(tradingDay, trade.tradingDay) &&
                Objects.equals(entrustNo, trade.entrustNo) &&
                Objects.equals(exchangeID, trade.exchangeID) &&
                Objects.equals(tradingCode, trade.tradingCode) &&
                Objects.equals(seatID, trade.seatID) &&
                Objects.equals(instrumentID, trade.instrumentID) &&
                Objects.equals(orderSysID, trade.orderSysID) &&
                Objects.equals(orderLocalID, trade.orderLocalID) &&
                Objects.equals(sysID, trade.sysID) &&
                Objects.equals(newSysID, trade.newSysID) &&
                Objects.equals(tradeID, trade.tradeID) &&
                Objects.equals(direction, trade.direction) &&
                Objects.equals(offsetFlag, trade.offsetFlag) &&
                Objects.equals(hedgeFlag, trade.hedgeFlag) &&
                Objects.equals(tradeTime, trade.tradeTime) &&
                Objects.equals(orderRef, trade.orderRef) &&
                Objects.equals(userID, trade.userID) &&
                Objects.equals(participantID, trade.participantID) &&
                Objects.equals(clientID, trade.clientID) &&
                Objects.equals(tradeDate, trade.tradeDate) &&
                Objects.equals(tradeType, trade.tradeType) &&
                Objects.equals(traderID, trade.traderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeNo, institutionID, investorID, tradingDay, entrustNo, exchangeID, tradingCode, seatID, instrumentID, orderSysID, orderLocalID, sysID, newSysID, tradeID, direction, offsetFlag, hedgeFlag, tradePrice, tradeVolume, tradeTime, tradeAmount, uniqSequenceNo, orderRef, userID, participantID, clientID, tradeDate, tradeType, traderID, margin, exchangeMargin, fee, optPremium, sequenceNo, brokerOrderSeq);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeNo='" + tradeNo + '\'' +
                ", institutionID='" + institutionID + '\'' +
                ", investorID='" + investorID + '\'' +
                ", tradingDay='" + tradingDay + '\'' +
                ", entrustNo='" + entrustNo + '\'' +
                ", exchangeID='" + exchangeID + '\'' +
                ", tradingCode='" + tradingCode + '\'' +
                ", seatID='" + seatID + '\'' +
                ", instrumentID='" + instrumentID + '\'' +
                ", orderSysID='" + orderSysID + '\'' +
                ", orderLocalID='" + orderLocalID + '\'' +
                ", sysID='" + sysID + '\'' +
                ", newSysID='" + newSysID + '\'' +
                ", tradeID='" + tradeID + '\'' +
                ", direction='" + direction + '\'' +
                ", offsetFlag='" + offsetFlag + '\'' +
                ", hedgeFlag='" + hedgeFlag + '\'' +
                ", tradePrice=" + tradePrice +
                ", tradeVolume=" + tradeVolume +
                ", tradeTime='" + tradeTime + '\'' +
                ", tradeAmount=" + tradeAmount +
                ", uniqSequenceNo=" + uniqSequenceNo +
                ", orderRef='" + orderRef + '\'' +
                ", userID='" + userID + '\'' +
                ", participantID='" + participantID + '\'' +
                ", clientID='" + clientID + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", traderID='" + traderID + '\'' +
                ", margin=" + margin +
                ", exchangeMargin=" + exchangeMargin +
                ", fee=" + fee +
                ", optPremium=" + optPremium +
                ", sequenceNo=" + sequenceNo +
                ", brokerOrderSeq=" + brokerOrderSeq +
                '}';
    }
}
