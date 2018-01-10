package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.order.SumItem;

import java.util.List;

public interface SummaryService {
    List<SumItem> getSummary(String startDate,String endDate);
}
