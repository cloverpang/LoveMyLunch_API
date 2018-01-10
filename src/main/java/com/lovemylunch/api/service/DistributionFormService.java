package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.distribut.DistributionForm;

import java.util.List;

public interface DistributionFormService {
    DistributionForm get(String id) throws Exception;

    DistributionForm getByNumber(String number) throws Exception;

    Boolean insert(DistributionForm distributionForm) throws Exception;

    Boolean update(DistributionForm distributionForm) throws Exception;

    Boolean delete(String id) throws Exception;

    PageBean<DistributionForm> page(String conditionsStr, int pageSize, int pageNo,
                        String sortColumn, String sortType) throws Exception;

    List<DistributionForm> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception;

    int generateDistributionForm(String orderDate) throws Exception;
}
