package com.lovemylunch.api.service.impl;


import com.lovemylunch.api.service.BaseService;
import com.lovemylunch.api.service.SummaryService;
import com.lovemylunch.common.beans.order.SumItem;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class SummaryServiceImpl extends BaseService implements SummaryService{

    @Override
    public List<SumItem> getSummary(String startDate, String endDate) {
        return null;
    }
}
