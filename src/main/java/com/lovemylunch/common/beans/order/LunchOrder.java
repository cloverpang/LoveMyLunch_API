package com.lovemylunch.common.beans.order;
import com.lovemylunch.common.beans.food.Dish;
import com.lovemylunch.common.beans.food.MixFood;
import com.lovemylunch.common.beans.food.Soup;
import com.lovemylunch.common.beans.food.StapleFood;

import java.util.Date;
import java.util.List;

public class LunchOrder {
    public LunchOrder() {
    }

    private String orderId;//订单主键
    private String orderNumber;//订单号 R-AsiaInspection-1700001
    private String distributNumber; //配送单号 DL-AsiaInspection-171211

    private Date bookTime;//下单时间
    private String customerName;//下单人姓名
    private String customerMobile;//下单人电话

    private String customerId;
    private String companyId;

    private String remark;//下单备注

    private Double originPrice;//原始价格
    private Double discoutPrice;//折扣
    private Double realPrice;//订单价格
    private Integer star;//评分
    private String appraise;//评价详细

    private Integer orderStatus;//订单状态 0 待确认 1 已确认 2 取消 9 配送中 10 已完成
    private Integer paymentStatus;//支付状态 0 未支付 1 已支付 2 部分支付

    private Date createTime;

    private List<Dish> dishs;//菜品集合 包括 汤 饭 等

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDistributNumber() {
        return distributNumber;
    }

    public void setDistributNumber(String distributNumber) {
        this.distributNumber = distributNumber;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Double originPrice) {
        this.originPrice = originPrice;
    }

    public Double getDiscoutPrice() {
        return discoutPrice;
    }

    public void setDiscoutPrice(Double discoutPrice) {
        this.discoutPrice = discoutPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
