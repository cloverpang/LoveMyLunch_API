package com.lovemylunch.api.service.impl;


import com.lovemylunch.api.service.BaseService;
import com.lovemylunch.api.service.DishService;
import com.lovemylunch.api.service.LunchOrderService;
import com.lovemylunch.api.service.SummaryService;
import com.lovemylunch.common.beans.food.Dish;
import com.lovemylunch.common.beans.order.LunchOrder;
import com.lovemylunch.common.beans.order.SumItem;
import com.lovemylunch.common.consts.Consts;
import com.lovemylunch.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class SummaryServiceImpl extends BaseService implements SummaryService{
    protected Logger logger = LoggerFactory.getLogger(SummaryServiceImpl.class);

    @Autowired
    private LunchOrderService lunchOrderService;

    @Autowired
    private DishService dishService;

    @Override
    public List<SumItem> getSummary(String center,String startDate, String endDate) throws Exception {
        String startTime = startDate +  " 00:00:00";
        String endTime = endDate + " 23:59:59";

        List<SumItem> sumItems = new ArrayList<SumItem>();
        String conditionStr = "lunchTime::between::" + startTime + "," + endTime + "$operationCenterCode::=::" + center;
        conditionStr = conditionStr + "$orderStatus::noteq::2";
        List<LunchOrder> orders = lunchOrderService.search(conditionStr, 10000, 1, "", "");

        //start to group
        Map<String,Integer> dishIdMap = new HashMap<String,Integer>();

        List<String> dishIdList = new ArrayList<String>();
        StringBuilder dishIds = new StringBuilder();
        for(LunchOrder order : orders){
            dishIds.append(order.getDishIds() + ",");
        }

        if(null == dishIds || dishIds.length() == 0){
            return sumItems;
        }

        String[] dishIdArr = dishIds.toString().split(Consts.COMMA);

        for(String dishId : dishIdArr){
            if(StringUtils.isNotEmpty(dishId)){
                if(dishIdMap.containsKey(dishId)){
                    int i = dishIdMap.get(dishId) + 1;
                    dishIdMap.put(dishId,i);
                }else{
                    dishIdMap.put(dishId,1);
                }
            }
        }

        for (String key : dishIdMap.keySet()) {
            dishIdList.add(key);
        }

        String dishIdListStr = String.join(",", dishIdList);


        List<Dish> dishs = dishService.search("dishId::=" + dishIdListStr, 1000, 1, "", "");

        Map<String,Dish> dishMap = new HashMap<String,Dish>();
        for(Dish d : dishs){
            dishMap.put(d.getDishId(),d);
        }

        for(String key : dishMap.keySet()){
            SumItem sumItem = new SumItem();
            Dish d = dishMap.get(key);
            sumItem.setDishId(key);
            sumItem.setDishImageSmall(d.getDishImageSmall());
            sumItem.setDishName(d.getDishName());
            sumItem.setDishPrice(d.getDishPrice());
            sumItem.setDishQuantity(dishIdMap.get(key));
            sumItem.setDishTotalPrice(dishIdMap.get(key) * d.getDishPrice());

            sumItems.add(sumItem);
        }

        return sumItems;
    }


}
