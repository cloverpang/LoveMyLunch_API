package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.system.OperationLog;

import java.util.List;

public interface OperationLogService {
    OperationLog get(String id) throws Exception;

    Boolean insert(OperationLog operationLog) throws Exception;

    Boolean delete(String id) throws Exception;

    Boolean batchDelete(String ids) throws Exception;

    PageBean<OperationLog> page(String conditionsStr, int pageSize, int pageNo,
                           String sortColumn, String sortType) throws Exception;

    List<OperationLog> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception;
}
