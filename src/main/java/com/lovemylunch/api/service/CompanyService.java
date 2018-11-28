package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.client.Company;
import com.lovemylunch.common.beans.client.extensions.CompanyExtension;
import com.lovemylunch.common.beans.client.extensions.CustomerExtension;

import java.util.List;
import java.util.Map;

public interface CompanyService {
    Company get(String id) throws Exception;

    Company getByCode(String code,String center) throws Exception;

    Boolean insert(Company Company) throws Exception;

    Boolean update(Company Company) throws Exception;

    Boolean delete(String id) throws Exception;

    Boolean batchDelete(String ids) throws Exception;

    PageBean<Company> page(String conditionsStr, int pageSize, int pageNo,
                             String sortColumn, String sortType,String force) throws Exception;

    List<Company> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception;

    PageBean<CompanyExtension> pageExtend(String conditionsStr, int pageSize, int pageNo,
                                          String sortColumn, String sortType) throws Exception;
}
