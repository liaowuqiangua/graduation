package com.tianen.chen.base.pojo;

import java.util.Objects;

/**
*  实际控制账户组
*/
public class InvestorControlGroup{

    
    /**
    *  控制组ID  -  key=[yes]
    */
    private String groupID = "-";
    
    
    /**
    *  控制组名称  -  key=[yes]
    */
    private String groupName = "-";
    
    
    /**
    *  投资者代码  -  key=[no]
    */
    private String investorID = "-";
    
    
    /**
    *  设置人  -  key=[no]
    */
    private String operatorCode = "-";
    
    
    /**
    *  设置时间  -  key=[-]
    */
    private String operatDate = "-";

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInvestorID() {
        return investorID;
    }

    public void setInvestorID(String investorID) {
        this.investorID = investorID;
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
        InvestorControlGroup that = (InvestorControlGroup) o;
        return Objects.equals(groupID, that.groupID) &&
                Objects.equals(groupName, that.groupName) &&
                Objects.equals(investorID, that.investorID) &&
                Objects.equals(operatorCode, that.operatorCode) &&
                Objects.equals(operatDate, that.operatDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupID, groupName, investorID, operatorCode, operatDate);
    }

    @Override
    public String toString() {
        return "InvestorControlGroup{" +
                "groupID='" + groupID + '\'' +
                ", groupName='" + groupName + '\'' +
                ", investorID='" + investorID + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", operatDate='" + operatDate + '\'' +
                '}';
    }
}
