package com.lovemylunch.api.dao.mybatis.mapper;

import com.lovemylunch.common.beans.system.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface OperationLogMapper {
    public OperationLog get(String id);

    public void insert(OperationLog OperationLog);
    
    public void delete(String id);

    public List<OperationLog> search(Map<String, Object> param);

    public int count(Map<String, Object> param);
}
