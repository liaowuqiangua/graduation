package com.tianen.chen.base.pojo;

import java.util.Objects;

/**
*  投资者成交预警
*/
public class TradeWarning {


    /**
     * 预警ID  -  key=[yes]
     */
    private String warningID = "-";


    /**
     * 为空为所有交易所  -  key=[no]
     */
    private String exchangeID = "-";



    /**
     * 投资者属性  -  key=[no]
     * Investor-投资者-1
     * Property-父级属性-2
     */
    private String property = "-";


    /**
     * 投资者代码 为'-'表示所有投资者  -  key=[no]
     */
    private String investorID = "-";


    /**
     * 自成交笔数一  -  key=[no]
     */
    private double scale1 = 0;

    /**
     * 自成交笔数二  -  key=[no]
     */
    private double scale2 = 0;

    /**
     * 自成交笔数三  -  key=[no]
     */
    private double scale3 = 0;

    /**
     * 自成交是否合并计算  -  key=[no]
     * isConsolidate-合并计算-0
     * notConsolidate-不合并计算-1
     */
    private String selfTradeConsolidate = "-";


    /**
     * 是否启动  -  key=[no]
     * Start-启动-0
     * NotStart-不启动-1
     */
    private String isStart = "-";


    /**
     * 操作员如果为空表示标准预警信息  -  key=[no]
     */
    private String operator = "-";


    /**
     * 设置人  -  key=[no]
     */
    private String operatorCode = "-";


    /**
     * 设置时间  -  key=[-]
     */
    private String operatDate = "-";

    public String getWarningID() {
        return warningID;
    }

    public void setWarningID(String warningID) {
        this.warningID = warningID;
    }

    public String getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(String exchangeID) {
        this.exchangeID = exchangeID;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getInvestorID() {
        return investorID;
    }

    public void setInvestorID(String investorID) {
        this.investorID = investorID;
    }

    public double getScale1() {
        return scale1;
    }

    public void setScale1(double scale1) {
        this.scale1 = scale1;
    }

    public double getScale2() {
        return scale2;
    }

    public void setScale2(double scale2) {
        this.scale2 = scale2;
    }

    public double getScale3() {
        return scale3;
    }

    public void setScale3(double scale3) {
        this.scale3 = scale3;
    }

    public String getSelfTradeConsolidate() {
        return selfTradeConsolidate;
    }

    public void setSelfTradeConsolidate(String selfTradeConsolidate) {
        this.selfTradeConsolidate = selfTradeConsolidate;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperatDate() {
        return operatDate;
    }

    public void setOperatDate(String operatDate) {
        this.operatDate = operatDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeWarning that = (TradeWarning) o;
        return Double.compare(that.scale1, scale1) == 0 &&
                Double.compare(that.scale2, scale2) == 0 &&
                Double.compare(that.scale3, scale3) == 0 &&
                Objects.equals(warningID, that.warningID) &&
                Objects.equals(exchangeID, that.exchangeID) &&
                Objects.equals(property, that.property) &&
                Objects.equals(investorID, that.investorID) &&
                Objects.equals(selfTradeConsolidate, that.selfTradeConsolidate) &&
                Objects.equals(isStart, that.isStart) &&
                Objects.equals(operator, that.operator) &&
                Objects.equals(operatorCode, that.operatorCode) &&
                Objects.equals(operatDate, that.operatDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warningID, exchangeID, property, investorID, scale1, scale2, scale3, selfTradeConsolidate, isStart, operator, operatorCode, operatDate);
    }

    @Override
    public String toString() {
        return "TradeWarning{" +
                "warningID='" + warningID + '\'' +
                ", exchangeID='" + exchangeID + '\'' +
                ", property='" + property + '\'' +
                ", investorID='" + investorID + '\'' +
                ", scale1=" + scale1 +
                ", scale2=" + scale2 +
                ", scale3=" + scale3 +
                ", selfTradeConsolidate='" + selfTradeConsolidate + '\'' +
                ", isStart='" + isStart + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", operatDate='" + operatDate + '\'' +
                '}';
    }
}