package com.lovemylunch.common.beans.distribut;

import com.lovemylunch.common.beans.order.LunchOrder;

import java.util.Date;
import java.util.List;

//���͵�
public class DistributionForm {
    private String distributionFormId;

    private String companyId;
    private String companyName;
    private String companyAddress;
    private String lastArriveTime;

    private String distributerId;
    private String distributerName;

    private String orderIds;//���� id ����

    List<LunchOrder> orders;//������ϸ

    private Integer status;// 0 δ�ʹ� 1 ���ʹ�

    private Date createTime;
}
