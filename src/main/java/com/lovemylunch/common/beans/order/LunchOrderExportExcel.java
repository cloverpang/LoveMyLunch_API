package com.lovemylunch.common.beans.order;

public class LunchOrderExportExcel {
    private int number;//���
    private String orderNumber;//������ R-AsiaInspection-1700001

    private String lunchTime;//����ʹ��ʱ��

    private String customerName;//�µ�������
    private String customerMobile;//�µ��˵绰

    private String content;// �������� ����Ʒ���Ƽ��ϣ�
    private String remark;//�µ���ע

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getLunchTime() {
        return lunchTime;
    }

    public void setLunchTime(String lunchTime) {
        this.lunchTime = lunchTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
