package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.dashboard.ChartData;
import com.lovemylunch.common.beans.dashboard.CreateCount;
import com.lovemylunch.common.beans.dashboard.Dashboard;
import com.lovemylunch.common.beans.dashboard.Quantity;
import com.lovemylunch.common.beans.order.SumItem;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    Dashboard getDashoard(String center,String force) throws Exception;

    ChartData getOrderData(String center,String startDate, String endDate,String force) throws Exception;

    ChartData getCustomerData(String center,String startDate, String endDate,String force) throws Exception;
}
