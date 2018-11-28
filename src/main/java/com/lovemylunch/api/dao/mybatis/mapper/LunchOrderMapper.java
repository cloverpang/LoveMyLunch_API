package com.lovemylunch.api.dao.mybatis.mapper;

import com.lovemylunch.common.beans.order.LunchOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface LunchOrderMapper {
    public LunchOrder get(String id);

    public void insert(LunchOrder lunchOrder);

    public void update(LunchOrder lunchOrder);

    public void delete(String id);

    public void cancal(String id);

    public List<LunchOrder> search(Map<String, Object> param);

    public int count(Map<String, Object> param);
}
