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

    private String orderId;//��������
    private String orderNumber;//������ R-AsiaInspection-1700001
    private String distributNumber; //���͵��� DL-AsiaInspection-171211

    private Date bookTime;//�µ�ʱ��
    private String customerName;//�µ�������
    private String customerMobile;//�µ��˵绰

    private String customerId;
    private String companyId;

    private String content;// �������� ����Ʒ���Ƽ��ϣ�
    private String dishIds;// ��ƷID����
    private String remark;//�µ���ע

    private Double originPrice;//ԭʼ�۸�
    private Double discoutPrice;//�ۿ�
    private Double realPrice;//�����۸�
    private Integer star;//����
    private String appraise;//������ϸ

    private Integer orderStatus;//����״̬ 0 ��ȷ�� 1 ��ȷ�� 2 ȡ�� 9 ������ 10 �����
    private Integer paymentStatus;//֧��״̬ 0 δ֧�� 1 ��֧�� 2 ����֧��

    private Date createTime;

    private List<Dish> dishs;//��Ʒ���� ���� �� �� ��

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Dish> getDishs() {
        return dishs;
    }

    public void setDishs(List<Dish> dishs) {
        this.dishs = dishs;
    }

    public String getDishIds() {
        return dishIds;
    }

    public void setDishIds(String dishIds) {
        this.dishIds = dishIds;
    }
}
