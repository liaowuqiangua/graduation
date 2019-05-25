package com.tianen.chen.base.pojo;

import java.util.Objects;

/**
*  分时涨跌幅度预警
*/
public class TimeScaleWarning{

    
    /**
    *  预警ID  -  key=[yes]
    */
    private String warningID = "-";
    
    
    /**
    *  交易类型  -  key=[no]
    *   All-全部-0
    *   Futuer-期货-1
    *   Option-期权-2
    */
    private String dealType = "-";
    
    
    /**
    *  合约范围  -  key=[no]
    *   Market-交易所-0
    *   Varicode-品种-1
    *   Code-合约-2
    *   OptionSeries-期权系列-3
    *   OptionCode-期权合约-4
    */
    private String contractScope = "-";
    
    
    /**
    *  合约，品种，交易所  -  key=[no]
    */
    private String instrumentID = "-";
    
    
    /**
    *  涨跌幅比例一  -  key=[no]
    */
    private double scale1 = 0;
    
    /**
    *  涨跌幅比例二  -  key=[no]
    */
    private double scale2 = 0;
    
    /**
    *  涨跌幅比例三  -  key=[no]
    */
    private double scale3 = 0;
    
    /**
    *  分时时间一  -  key=[no]
    */
    private double scaleTime1 = 0;
    
    /**
    *  分时时间二  -  key=[no]
    */
    private double scaleTime2 = 0;
    
    /**
    *  分时时间三  -  key=[no]
    */
    private double scaleTime3 = 0;
    
    /**
    *  是否启动  -  key=[no]
    *   Start-启动-0
    *   NotStart-不启动-1
    */
    private String isStart = "-";
    
    
    /**
    *  操作员如果为空表示标准预警信息  -  key=[no]
    */
    private String operator = "-";
    
    
    /**
    *  设置人  -  key=[no]
    */
    private String operatorCode = "-";
    
    
    /**
    *  设置时间  -  key=[-]
    */
    private String operatDate = "-";

    public String getWarningID() {
        return warningID;
    }

    public void setWarningID(String warningID) {
        this.warningID = warningID;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getContractScope() {
        return contractScope;
    }

    public void setContractScope(String contractScope) {
        this.contractScope = contractScope;
    }

    public String getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(String instrumentID) {
        this.instrumentID = instrumentID;
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

    public double getScaleTime1() {
        return scaleTime1;
    }

    public void setScaleTime1(double scaleTime1) {
        this.scaleTime1 = scaleTime1;
    }

    public double getScaleTime2() {
        return scaleTime2;
    }

    public void setScaleTime2(double scaleTime2) {
        this.scaleTime2 = scaleTime2;
    }

    public double getScaleTime3() {
        return scaleTime3;
    }

    public void setScaleTime3(double scaleTime3) {
        this.scaleTime3 = scaleTime3;
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
        TimeScaleWarning that = (TimeScaleWarning) o;
        return Double.compare(that.scale1, scale1) == 0 &&
                Double.compare(that.scale2, scale2) == 0 &&
                Double.compare(that.scale3, scale3) == 0 &&
                Double.compare(that.scaleTime1, scaleTime1) == 0 &&
                Double.compare(that.scaleTime2, scaleTime2) == 0 &&
                Double.compare(that.scaleTime3, scaleTime3) == 0 &&
                Objects.equals(warningID, that.warningID) &&
                Objects.equals(dealType, that.dealType) &&
                Objects.equals(contractScope, that.contractScope) &&
                Objects.equals(instrumentID, that.instrumentID) &&
                Objects.equals(isStart, that.isStart) &&
                Objects.equals(operator, that.operator) &&
                Objects.equals(operatorCode, that.operatorCode) &&
                Objects.equals(operatDate, that.operatDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warningID, dealType, contractScope, instrumentID, scale1, scale2, scale3, scaleTime1, scaleTime2, scaleTime3, isStart, operator, operatorCode, operatDate);
    }

    @Override
    public String toString() {
        return "TimeScaleWarning{" +
                "warningID='" + warningID + '\'' +
                ", dealType='" + dealType + '\'' +
                ", contractScope='" + contractScope + '\'' +
                ", instrumentID='" + instrumentID + '\'' +
                ", scale1=" + scale1 +
                ", scale2=" + scale2 +
                ", scale3=" + scale3 +
                ", scaleTime1=" + scaleTime1 +
                ", scaleTime2=" + scaleTime2 +
                ", scaleTime3=" + scaleTime3 +
                ", isStart='" + isStart + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", operatDate='" + operatDate + '\'' +
                '}';
    }
}
