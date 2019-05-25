package com.tianen.chen.base.pojo;

import java.util.Objects;

/**
*  投资者
*/
public class Investor{

    
    /**
    *  机构代码  -  key=[yes]
    */
    private String institutionID = "-";
    
    
    /**
    *  组织架构  -  key=[yes]
    */
    private String departmentID = "-";
    
    
    /**
    *  投资者代码  -  key=[yes]
    */
    private String investorID = "-";
    
    
    /**
    *  投资者名称  -  key=[-]
    */
    private String investorName = "-";
    
    
    /**
    *  表示该客户是否活跃  -  key=[-]
    */
    private int isActive = 0;
    
    /**
    *  控制组ID  -  key=[yes]
    */
    private String groupID = "-";
    
    
    /**
    *  联系电话  -  key=[-]
    */
    private String telephone = "-";
    
    
    /**
    *  通讯地址  -  key=[-]
    */
    private String address = "-";
    
    
    /**
    *  手机  -  key=[-]
    */
    private String mobile = "-";
    
    
    /**
    *  传真  -  key=[-]
    */
    private String fax = "-";
    
    
    /**
    *  电子邮件  -  key=[-]
    */
    private String eMail = "-";
    
    
    /**
    *  开户日期  -  key=[-]
    */
    private String openDate = "-";
    
    
    /**
    *  开户模式  -  key=[-]
    *   CLM_Domestic-境内期货公司直接代理境外投资者模式-1
    *   CLM_ForeignAgent-境外特殊经纪参与者模式-2
    *   CLM_ForeignIB-境外中介机构中间介绍模式-3
    *   CLM_ForeignSecondAgent-境外中介机构委托代理模式-4
    *   CLM_ForeignPerson-境外特殊非经纪参与者模式-5
    *   CLM_DomesticInvestor-境内投资者模式-6
    */
    private String clientMode = "-";
    
    
    /**
    *  指令下单人姓名  -  key=[-]
    */
    private String orderInvestorName = "-";
    
    
    /**
    *  指令下单人联系电话  -  key=[-]
    */
    private String orderTelephone = "-";
    
    
    /**
    *  指定下单人手机  -  key=[-]
    */
    private String orderMobile = "-";
    
    
    /**
    *  资金调拨人姓名  -  key=[-]
    */
    private String fundName = "-";
    
    
    /**
    *  资金调拨人联系电话  -  key=[-]
    */
    private String fundTelephone = "-";
    
    
    /**
    *  资金调拨人手机  -  key=[-]
    */
    private String fundMobile = "-";
    
    
    /**
    *  节点编号  -  key=[-]
    */
    private int nodeID = 0;

    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(String institutionID) {
        this.institutionID = institutionID;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getInvestorID() {
        return investorID;
    }

    public void setInvestorID(String investorID) {
        this.investorID = investorID;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getClientMode() {
        return clientMode;
    }

    public void setClientMode(String clientMode) {
        this.clientMode = clientMode;
    }

    public String getOrderInvestorName() {
        return orderInvestorName;
    }

    public void setOrderInvestorName(String orderInvestorName) {
        this.orderInvestorName = orderInvestorName;
    }

    public String getOrderTelephone() {
        return orderTelephone;
    }

    public void setOrderTelephone(String orderTelephone) {
        this.orderTelephone = orderTelephone;
    }

    public String getOrderMobile() {
        return orderMobile;
    }

    public void setOrderMobile(String orderMobile) {
        this.orderMobile = orderMobile;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundTelephone() {
        return fundTelephone;
    }

    public void setFundTelephone(String fundTelephone) {
        this.fundTelephone = fundTelephone;
    }

    public String getFundMobile() {
        return fundMobile;
    }

    public void setFundMobile(String fundMobile) {
        this.fundMobile = fundMobile;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Investor investor = (Investor) o;
        return isActive == investor.isActive &&
                nodeID == investor.nodeID &&
                Objects.equals(institutionID, investor.institutionID) &&
                Objects.equals(departmentID, investor.departmentID) &&
                Objects.equals(investorID, investor.investorID) &&
                Objects.equals(investorName, investor.investorName) &&
                Objects.equals(groupID, investor.groupID) &&
                Objects.equals(telephone, investor.telephone) &&
                Objects.equals(address, investor.address) &&
                Objects.equals(mobile, investor.mobile) &&
                Objects.equals(fax, investor.fax) &&
                Objects.equals(eMail, investor.eMail) &&
                Objects.equals(openDate, investor.openDate) &&
                Objects.equals(clientMode, investor.clientMode) &&
                Objects.equals(orderInvestorName, investor.orderInvestorName) &&
                Objects.equals(orderTelephone, investor.orderTelephone) &&
                Objects.equals(orderMobile, investor.orderMobile) &&
                Objects.equals(fundName, investor.fundName) &&
                Objects.equals(fundTelephone, investor.fundTelephone) &&
                Objects.equals(fundMobile, investor.fundMobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionID, departmentID, investorID, investorName, isActive, groupID, telephone, address, mobile, fax, eMail, openDate, clientMode, orderInvestorName, orderTelephone, orderMobile, fundName, fundTelephone, fundMobile, nodeID);
    }

    @Override
    public String toString() {
        return "Investor{" +
                "institutionID='" + institutionID + '\'' +
                ", departmentID='" + departmentID + '\'' +
                ", investorID='" + investorID + '\'' +
                ", investorName='" + investorName + '\'' +
                ", isActive=" + isActive +
                ", groupID='" + groupID + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", fax='" + fax + '\'' +
                ", eMail='" + eMail + '\'' +
                ", openDate='" + openDate + '\'' +
                ", clientMode='" + clientMode + '\'' +
                ", orderInvestorName='" + orderInvestorName + '\'' +
                ", orderTelephone='" + orderTelephone + '\'' +
                ", orderMobile='" + orderMobile + '\'' +
                ", fundName='" + fundName + '\'' +
                ", fundTelephone='" + fundTelephone + '\'' +
                ", fundMobile='" + fundMobile + '\'' +
                ", nodeID=" + nodeID +
                '}';
    }
}
