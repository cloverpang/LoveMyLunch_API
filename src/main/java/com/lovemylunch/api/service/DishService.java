package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.food.Dish;

import java.util.List;

public interface DishService {
    Dish get(String id) throws Exception;

    Boolean insert(Dish dish) throws Exception;

    Boolean update(Dish dish) throws Exception;

    Boolean delete(String id) throws Exception;

    Boolean markeNotOpen(String id) throws Exception;

    PageBean<Dish> page(String conditionsStr, int pageSize, int pageNo,
                            String sortColumn, String sortType) throws Exception;

    List<Dish> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception;
}
