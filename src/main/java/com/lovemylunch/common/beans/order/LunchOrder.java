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
    private String orderId;//��������
    private String orderNumber;//������ R-AsiaInspection-1700001
    private String distributNumber; //���͵��� DL-AsiaInspection-171211

    private Date bookTime;//�µ�ʱ��
    private String customerName;//�µ�������
    private String customerMobile;//�µ��˵绰
    private String remark;//�µ���ע

    private Double price;//�����۸�
    private Boolean payFor;//�Ƿ���֧��
    private Integer star;//����
    private String appraise;//������ϸ

    private List<Dish> dishs;//��Ʒ����
    private StapleFood stapleFood;//��ʳ
    private Soup soup;//��
    private MixFood mixFood;//�����ʳ�������ȣ�


}
