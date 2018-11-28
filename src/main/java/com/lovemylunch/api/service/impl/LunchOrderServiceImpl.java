package com.lovemylunch.api.service.impl;

import com.lovemylunch.api.dao.mybatis.mapper.LunchOrderMapper;
import com.lovemylunch.api.service.BaseService;
import com.lovemylunch.api.service.DishService;
import com.lovemylunch.api.service.LunchOrderService;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.food.Dish;
import com.lovemylunch.common.beans.order.LunchOrder;
import com.lovemylunch.common.util.CriteriaMapUtils;
import com.lovemylunch.common.util.DateUtils;
import com.lovemylunch.common.util.IDUtils;
import com.lovemylunch.common.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Service
@EnableTransactionManagement// 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven/>
public class LunchOrderServiceImpl extends BaseService implements LunchOrderService {
    protected Logger logger = LoggerFactory.getLogger(LunchOrderServiceImpl.class);

    @Autowired
    private LunchOrderMapper lunchOrderMapper;

    @Autowired
    private DishService dishService;


    @Override
    public LunchOrder get(String id) throws Exception {
        try{
            LunchOrder lunchOrder = lunchOrderMapper.get(id);
            if(StringUtils.isNotEmpty(lunchOrder.getDishIds())){
                String conditionStr = "dishId::=::" + lunchOrder.getDishIds();
                List<Dish> dishs = dishService.search(conditionStr,1000,1,"dishType","desc");
                if(CollectionUtils.isNotEmpty(dishs)){
                    lunchOrder.setDishs(dishs);
                }
            }
            return lunchOrder;
        }catch (Exception e){
            logger.error("failed on get LunchOrder info : " + e.getMessage());
            throw new Exception("failed on get LunchOrder : ",e);
        }
    }

    @Override
    public Boolean insert(LunchOrder lunchOrder) throws Exception {
        try{
            if(null == lunchOrder.getDishIds()){
                throw new Exception("lunchOrder must have dish! ");
            }

            //re-set the orderId
            lunchOrder.setOrderId(IDUtils.generateID());

            lunchOrder.setBookTime(DateUtils.now());
            lunchOrder.setCreateTime(DateUtils.now());

            lunchOrderMapper.insert(lunchOrder);
            return true;
        }catch (Exception e){
            logger.error("failed on insert LunchOrder : " + e.getMessage());
            throw new Exception("failed on insert LunchOrder : ",e);
        }
    }

    @Override
    public Boolean update(LunchOrder lunchOrder) throws Exception {
        try{
            lunchOrderMapper.update(lunchOrder);
            return true;
        }catch (Exception e){
            logger.error("failed on update LunchOrder : " + e.getMessage());
            throw new Exception("failed on update LunchOrder : ",e);
        }
    }

    @Override
    public Boolean delete(String id) throws Exception {
        LunchOrder originLunchOrder = get(id);
        if(null == originLunchOrder){
            throw new Exception("this lunch order not existing ");
        }

        try{
            lunchOrderMapper.delete(originLunchOrder.getOrderId());
            return true;
        }catch (Exception e){
            logger.error("failed on delete LunchOrder : " + e.getMessage());
            throw new Exception("failed on delete LunchOrder : ",e);
        }
    }

    @Override
    public Boolean cancal(String id) throws Exception {
        LunchOrder originLunchOrder = get(id);
        if(null == originLunchOrder){
            throw new Exception("this lunch order not existing ");
        }

        try{
            lunchOrderMapper.cancal(originLunchOrder.getOrderId());
            return true;
        }catch (Exception e){
            logger.error("failed on delete LunchOrder : " + e.getMessage());
            throw new Exception("failed on delete LunchOrder : ",e);
        }
    }

    @Override
    public PageBean<LunchOrder> page(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("","",conditionsStr,
                    pageSize,pageNo,sortColumn,sortType);

            PageBean<LunchOrder> LunchOrderPageBean = new PageBean<LunchOrder>();
            int totalRecords = lunchOrderMapper.count(criteriaMap);

            LunchOrderPageBean.setTotalSize(totalRecords);
            LunchOrderPageBean.setPageNo(pageNo);
            int numOfPages = Double.valueOf(Math.ceil((1.0 * totalRecords) / pageSize)).intValue();
            LunchOrderPageBean.setTotalPageNum(numOfPages);

            List<LunchOrder> fundBasics = lunchOrderMapper.search(criteriaMap);
            if(CollectionUtils.isEmpty(fundBasics)){
                fundBasics = Collections.emptyList();
            }

            LunchOrderPageBean.setItem(fundBasics);
            return LunchOrderPageBean;
        }catch (Exception e){
            throw new Exception("Search LunchOrder exception : " + e.getMessage());
        }
    }

    @Override
    public List<LunchOrder> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("", "", conditionsStr,
                    pageSize, pageNo, sortColumn, sortType);

            return lunchOrderMapper.search(criteriaMap);
        }catch (Exception e){
            throw new Exception("Search LunchOrder exception : " + e.getMessage());
        }
    }
}
