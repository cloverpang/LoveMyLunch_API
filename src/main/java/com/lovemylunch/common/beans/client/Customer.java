package com.lovemylunch.common.beans.client;

public class Customer {
    private String customerId;//客户用户主键ID
    private String customerName;//客户姓名
    private String companyId;//公司主键ID
    private String companyName;//公司名称

    private String mobileNumber;//电话号码

    private Integer customerScore;//用户积分
    private Boolean active;//是否是活动状态，为否表示暂时不可用
}
