package com.lovemylunch.api.service.impl;

import com.lovemylunch.api.dao.mybatis.mapper.DishMapper;
import com.lovemylunch.api.service.BaseService;
import com.lovemylunch.api.service.DishService;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.food.Dish;
import com.lovemylunch.common.util.CriteriaMapUtils;
import com.lovemylunch.common.util.DateUtils;
import com.lovemylunch.common.util.IDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Service
public class DishServiceImpl extends BaseService implements DishService {
    protected Logger logger = LoggerFactory.getLogger(DishServiceImpl.class);

    @Autowired
    private DishMapper dishMapper;

    @Override
    public Dish get(String id) throws Exception {
        try{
            return dishMapper.get(id);
        }catch (Exception e){
            logger.error("failed on get Dish info : " + e.getMessage());
            throw new Exception("failed on get Dish : ",e);
        }
    }

    @Override
    public Boolean insert(Dish dish) throws Exception {
        try{
            if(null == dish.getDishName()){
                throw new Exception("dish login is null! ");
            }

            //re-set the DishId
            dish.setDishId(IDUtils.generateID());

            dish.setCreateTime(DateUtils.now());

            dishMapper.insert(dish);
            return true;
        }catch (Exception e){
            logger.error("failed on insert Dish : " + e.getMessage());
            throw new Exception("failed on insert Dish : ",e);
        }
    }

    @Override
    public Boolean update(Dish dish) throws Exception {
        try{
            dishMapper.update(dish);
            return true;
        }catch (Exception e){
            logger.error("failed on update Dish : " + e.getMessage());
            throw new Exception("failed on update Dish : ",e);
        }
    }

    @Override
    public Boolean delete(String id) throws Exception {
        try{
            Dish originDish = get(id);
            dishMapper.delete(originDish.getDishId());
            return true;
        }catch (Exception e){
            logger.error("failed on delete Dish : " + e.getMessage());
            throw new Exception("failed on delete Dish : ",e);
        }
    }

    @Override
    public Boolean markeNotOpen(String id) throws Exception {
        try{
            Dish originDish = get(id);
            dishMapper.markeNotOpen(originDish.getDishId());
            return true;
        }catch (Exception e){
            logger.error("failed on delete Dish : " + e.getMessage());
            throw new Exception("failed on delete Dish : ",e);
        }
    }

    @Override
    public PageBean<Dish> page(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("","",conditionsStr,
                    pageSize,pageNo,sortColumn,sortType);

            PageBean<Dish> DishPageBean = new PageBean<Dish>();
            int totalRecords = dishMapper.count(criteriaMap);

            DishPageBean.setTotalSize(totalRecords);
            DishPageBean.setPageNo(pageNo);
            int numOfPages = Double.valueOf(Math.ceil((1.0 * totalRecords) / pageSize)).intValue();
            DishPageBean.setTotalPageNum(numOfPages);

            List<Dish> fundBasics = dishMapper.search(criteriaMap);
            if(CollectionUtils.isEmpty(fundBasics)){
                fundBasics = Collections.emptyList();
            }

            DishPageBean.setItem(fundBasics);
            return DishPageBean;
        }catch (Exception e){
            throw new Exception("Search Dish exception : " + e.getMessage());
        }
    }

    @Override
    public List<Dish> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("", "", conditionsStr,
                    pageSize, pageNo, sortColumn, sortType);

            return dishMapper.search(criteriaMap);
        }catch (Exception e){
            throw new Exception("Search Dish exception : " + e.getMessage());
        }
    }
}
