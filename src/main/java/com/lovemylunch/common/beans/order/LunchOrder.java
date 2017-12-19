package com.lovemylunch.common.beans.order;
import com.lovemylunch.common.beans.food.Dish;
import com.lovemylunch.common.beans.food.MixFood;
import com.lovemylunch.common.beans.food.Soup;
import com.lovemylunch.common.beans.food.StapleFood;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */
public class LunchOrder {
    private String orderId;//订单主键
    private String orderNumber;//订单号 R-AsiaInspection-1700001
    private String distributNumber; //配送单号 DL-AsiaInspection-171211

    private Date bookTime;//下单时间
    private String customerName;//下单人姓名
    private String customerMobile;//下单人电话
    private String remark;//下单备注

    private Double price;//订单价格
    private Boolean payFor;//是否已支付
    private Integer star;//评分
    private String appraise;//评价详细

    private List<Dish> dishs;//菜品集合
    private StapleFood stapleFood;//主食
    private Soup soup;//汤
    private MixFood mixFood;//混合主食（炒饭等）


}
