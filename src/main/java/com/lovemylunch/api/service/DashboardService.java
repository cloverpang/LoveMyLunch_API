package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.dashboard.ChartData;
import com.lovemylunch.common.beans.dashboard.CreateCount;
import com.lovemylunch.common.beans.dashboard.Dashboard;
import com.lovemylunch.common.beans.dashboard.Quantity;
import com.lovemylunch.common.beans.order.SumItem;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    Dashboard getDashoard() throws Exception;

    ChartData getOrderData(String startDate, String endDate) throws Exception;

    ChartData getCustomerData(String startDate, String endDate) throws Exception;
}
