package com.lovemylunch.common.beans.distribut;

import com.lovemylunch.common.beans.order.LunchOrder;

import java.util.Date;
import java.util.List;

//ÅäËÍµ¥
public class DistributionForm {
    private String distributionFormId;

    private String companyId;
    private String companyName;
    private String companyAddress;
    private String lastArriveTime;

    private String distributerId;
    private String distributerName;

    private String orderIds;//¶©µ¥ id ¼¯ºÏ

    List<LunchOrder> orders;//¶©µ¥ÏêÏ¸

    private Integer status;// 0 Î´ËÍ´ï 1 ÒÑËÍ´ï

    private Date createTime;
}
