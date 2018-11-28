package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.distribut.Distributer;

import java.util.List;

public interface DistributerService {
    Distributer get(String id) throws Exception;

    Boolean insert(Distributer distributer) throws Exception;

    Boolean update(Distributer distributer) throws Exception;

    Boolean delete(String id) throws Exception;

    PageBean<Distributer> page(String conditionsStr, int pageSize, int pageNo,
                        String sortColumn, String sortType) throws Exception;

    List<Distributer> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception;
}
