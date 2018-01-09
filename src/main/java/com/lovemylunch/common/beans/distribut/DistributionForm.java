package com.lovemylunch.common.beans.distribut;

import com.lovemylunch.common.beans.order.LunchOrder;

import java.util.Date;
import java.util.List;

//配送单
public class DistributionForm {
    private String distributionFormId;

    private String companyId;
    private String companyName;
    private String companyAddress;
    private String lastArriveTime;

    private String distributerId;
    private String distributerName;

    private String orderIds;//订单 id 集合

    List<LunchOrder> orders;//订单详细

    private Integer status;// 0 未送达 1 已送达

    private Date createTime;
}
