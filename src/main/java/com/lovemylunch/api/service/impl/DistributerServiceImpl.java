package com.lovemylunch.api.service.impl;

import com.lovemylunch.api.dao.mybatis.mapper.DistributerMapper;
import com.lovemylunch.api.service.BaseService;
import com.lovemylunch.api.service.DistributerService;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.distribut.Distributer;
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
public class DistributerServiceImpl extends BaseService implements DistributerService {
    protected Logger logger = LoggerFactory.getLogger(DistributerServiceImpl.class);

    @Autowired
    private DistributerMapper distributerMapper;

    @Override
    public Distributer get(String id) throws Exception {
        try{
            return distributerMapper.get(id);
        }catch (Exception e){
            logger.error("failed on get Distributer info : " + e.getMessage());
            throw new Exception("failed on get Distributer : ",e);
        }
    }

    @Override
    public Boolean insert(Distributer distributer) throws Exception {
        try{
            //re-set the DistributerId
            distributer.setDistributerId(IDUtils.generateID());

            distributer.setCreateTime(DateUtils.now());

            distributerMapper.insert(distributer);
            return true;
        }catch (Exception e){
            logger.error("failed on insert Distributer : " + e.getMessage());
            throw new Exception("failed on insert Distributer : ",e);
        }
    }

    @Override
    public Boolean update(Distributer distributer) throws Exception {
        try{
            distributerMapper.update(distributer);
            return true;
        }catch (Exception e){
            logger.error("failed on update Distributer : " + e.getMessage());
            throw new Exception("failed on update Distributer : ",e);
        }
    }

    @Override
    public Boolean delete(String id) throws Exception {
        try{
            Distributer originDistributer = get(id);
            distributerMapper.delete(originDistributer.getDistributerId());
            return true;
        }catch (Exception e){
            logger.error("failed on delete Distributer : " + e.getMessage());
            throw new Exception("failed on delete Distributer : ",e);
        }
    }

    @Override
    public PageBean<Distributer> page(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("","",conditionsStr,
                    pageSize,pageNo,sortColumn,sortType);

            PageBean<Distributer> DistributerPageBean = new PageBean<Distributer>();
            int totalRecords = distributerMapper.count(criteriaMap);

            DistributerPageBean.setTotalSize(totalRecords);
            DistributerPageBean.setPageNo(pageNo);
            int numOfPages = Double.valueOf(Math.ceil((1.0 * totalRecords) / pageSize)).intValue();
            DistributerPageBean.setTotalPageNum(numOfPages);

            List<Distributer> fundBasics = distributerMapper.search(criteriaMap);
            if(CollectionUtils.isEmpty(fundBasics)){
                fundBasics = Collections.emptyList();
            }

            DistributerPageBean.setItem(fundBasics);
            return DistributerPageBean;
        }catch (Exception e){
            throw new Exception("Search Distributer exception : " + e.getMessage());
        }
    }

    @Override
    public List<Distributer> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("", "", conditionsStr,
                    pageSize, pageNo, sortColumn, sortType);

            return distributerMapper.search(criteriaMap);
        }catch (Exception e){
            throw new Exception("Search Distributer exception : " + e.getMessage());
        }
    }
}
