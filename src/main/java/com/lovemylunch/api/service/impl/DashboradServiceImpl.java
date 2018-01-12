package com.lovemylunch.api.service.impl;


import com.lovemylunch.api.dao.mybatis.mapper.DashboardMapper;
import com.lovemylunch.api.service.BaseService;
import com.lovemylunch.api.service.DashboardService;
import com.lovemylunch.common.beans.dashboard.ChartData;
import com.lovemylunch.common.beans.dashboard.CreateCount;
import com.lovemylunch.common.beans.dashboard.Dashboard;
import com.lovemylunch.common.util.DateUtils;
import com.lovemylunch.common.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.*;

@Component
@Service
@EnableTransactionManagement// 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven/>
public class DashboradServiceImpl extends BaseService implements DashboardService{
    protected Logger logger = LoggerFactory.getLogger(DashboradServiceImpl.class);

    @Autowired
    DashboardMapper dashboardMapper;

    @Override
    public Dashboard getDashoard() throws Exception {
        return dashboardMapper.getDashborad();
    }

    @Override
    public ChartData getOrderData(String startDate, String endDate) throws Exception {
        String startTime = startDate +  " 00:00:00";
        String endTime = endDate + " 23:59:59";
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(startDate)){
            criteriaMap.put("startDate", startTime);
        }
        if(StringUtils.isNotEmpty(endDate)){
            criteriaMap.put("endDate", endTime);
        }

        List<CreateCount> orderCount = dashboardMapper.getOrderCreated(criteriaMap);
        String[] dayArr = generateDayListArr(startDate, endDate);
        String[] orderCountArr = generateCountArr(dayArr, orderCount);

        ChartData chartData = new ChartData();
        chartData.setDatas(orderCountArr);
        chartData.setTitles(dayArr);
        return chartData;
    }

    @Override
    public ChartData getCustomerData(String startDate, String endDate) throws Exception {
        String startTime = startDate +  " 00:00:00";
        String endTime = endDate + " 23:59:59";
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(startDate)){
            criteriaMap.put("startDate", startTime);
        }
        if(StringUtils.isNotEmpty(endDate)){
            criteriaMap.put("endDate", endTime);
        }

        List<CreateCount> customerCount =  dashboardMapper.getCustomerCreated(criteriaMap);

        String[] dayArr = generateDayListArr(startDate, endDate);
        String[] customerCountArr = generateCountArr(dayArr, customerCount);

        ChartData chartData = new ChartData();
        chartData.setDatas(customerCountArr);
        chartData.setTitles(dayArr);
        return chartData;
    }

    private String[] generateDayListArr(String startDate, String endDate){
        Date startDay = DateUtils.toDateWithAI(startDate);
        Date endDay = DateUtils.toDateWithAI(endDate);

        int countDays = DateUtils.countDays(startDay,endDay);

        List<String> list = new ArrayList<String>();
        if(countDays == 0) {
            list.add(startDate);
        }else if(countDays == 1){
            list.add(endDate);
            list.add(endDate);
        }else{
            list.add(startDate);
            for(int i = 0;i<countDays;i++){
                Date nextDay = DateUtils.increaseDay(startDay,1+i);
                list.add(DateUtils.toFormatString(nextDay,"yyyy-MM-dd"));
            }
            list.add(endDate);
        }

        return list.toArray(new String[list.size()]);
    }

    private String[] generateCountArr(String[] dayArr, List<CreateCount> createCounts){
        List<String> list = new ArrayList<String>();

        for(String day : dayArr){
            boolean mapping = false;
            if(CollectionUtils.isNotEmpty(createCounts)){
                for(CreateCount c : createCounts){
                    if(day.equals(c.getCreatedDate())){
                        list.add(c.getCreatedCount());
                        mapping = true;
                        break;
                    }
                }
            }

            if(!mapping){
                list.add("0");
            }
        }

        return list.toArray(new String[list.size()]);
    }
}
