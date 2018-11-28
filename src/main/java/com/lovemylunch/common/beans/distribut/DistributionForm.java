package com.lovemylunch.common.beans.distribut;

import com.lovemylunch.common.beans.order.LunchOrder;

import java.util.Date;
import java.util.List;

//配送单
public class DistributionForm {
    private String distributionFormId;

    private String formNumber;//{{companyCode}}_{{date}} AsiaInspection_2018-01-13

    private String companyId;
    private String companyName;
    private String companyAddress;
    private Date lastArriveTime;

    private String distributerId;
    private String distributerName;

    private String orderIds;//订单 id 集合

    List<LunchOrder> orders;//订单详细

    private Integer status;// 0 未送达 1 已送达

    private String operationCenterCode;//运营中心代码
    private Date createTime;

    public String getDistributionFormId() {
        return distributionFormId;
    }

    public void setDistributionFormId(String distributionFormId) {
        this.distributionFormId = distributionFormId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Date getLastArriveTime() {
        return lastArriveTime;
    }

    public void setLastArriveTime(Date lastArriveTime) {
        this.lastArriveTime = lastArriveTime;
    }

    public String getDistributerId() {
        return distributerId;
    }

    public void setDistributerId(String distributerId) {
        this.distributerId = distributerId;
    }

    public String getDistributerName() {
        return distributerName;
    }

    public void setDistributerName(String distributerName) {
        this.distributerName = distributerName;
    }

    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds;
    }

    public List<LunchOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<LunchOrder> orders) {
        this.orders = orders;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getOperationCenterCode() {
        return operationCenterCode;
    }

    public void setOperationCenterCode(String operationCenterCode) {
        this.operationCenterCode = operationCenterCode;
    }
}
