package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.order.LunchOrder;

import java.util.List;

public interface LunchOrderService {
    LunchOrder get(String id) throws Exception;

    Boolean insert(LunchOrder lunchOrder) throws Exception;

    Boolean update(LunchOrder lunchOrder) throws Exception;

    Boolean delete(String id) throws Exception;

    Boolean cancal(String id) throws Exception;

    PageBean<LunchOrder> page(String conditionsStr, int pageSize, int pageNo,
                        String sortColumn, String sortType) throws Exception;

    List<LunchOrder> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception;
}
