package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.order.SumItem;

import java.util.List;

public interface SummaryService {
    List<SumItem> getSummary(String center,String startDate,String endDate) throws Exception;
}
