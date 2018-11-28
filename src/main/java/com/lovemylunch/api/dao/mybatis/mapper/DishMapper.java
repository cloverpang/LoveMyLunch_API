package com.lovemylunch.api.dao.mybatis.mapper;

import com.lovemylunch.common.beans.client.Customer;
import com.lovemylunch.common.beans.food.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface DishMapper {
    public Dish get(String id);

    public void insert(Dish dish);

    public void update(Dish dish);

    public void delete(String id);

    public void markeNotOpen(String id);

    public List<Dish> search(Map<String, Object> param);

    public int count(Map<String, Object> param);
}
