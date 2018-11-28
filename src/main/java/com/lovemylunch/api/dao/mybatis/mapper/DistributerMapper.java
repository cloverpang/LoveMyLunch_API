package com.lovemylunch.api.dao.mybatis.mapper;

import com.lovemylunch.common.beans.distribut.Distributer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface DistributerMapper {
    public Distributer get(String id);

    public void insert(Distributer distributer);

    public void update(Distributer distributer);
    
    public void delete(String id);

    public List<Distributer> search(Map<String, Object> param);

    public int count(Map<String, Object> param);
}
