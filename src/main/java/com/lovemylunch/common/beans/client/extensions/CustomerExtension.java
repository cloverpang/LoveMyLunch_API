package com.lovemylunch.common.beans.client.extensions;

import com.lovemylunch.common.beans.client.Customer;

/**
 * Created by Administrator on 2017/12/25.
 */
public class CustomerExtension extends Customer
{
    private Integer orderQuantity; // �ۼƶ�������
    private Integer totalCost;// �ۼ����ѽ��

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }
}
