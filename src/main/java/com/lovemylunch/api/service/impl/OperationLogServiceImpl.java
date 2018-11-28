package com.lovemylunch.api.service.impl;

import com.lovemylunch.api.dao.mybatis.mapper.OperationLogMapper;
import com.lovemylunch.api.service.BaseService;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.system.OperationLog;
import com.lovemylunch.common.consts.Consts;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Service
@EnableTransactionManagement// 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven/>
public class OperationLogServiceImpl extends BaseService implements com.lovemylunch.api.service.OperationLogService {
    protected Logger logger = LoggerFactory.getLogger(OperationLogServiceImpl.class);

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public OperationLog get(String id) throws Exception {
        try{
            return operationLogMapper.get(id);
        }catch (Exception e){
            logger.error("failed on get operationLog info : " + e.getMessage());
            throw new Exception("failed on get operationLog : ",e);
        }
    }

    @Override
    public Boolean insert(OperationLog operationLog) throws Exception {
        try{
            //re-set the operationLogId
            operationLog.setOperationId(IDUtils.generateID());

            if(null == operationLog.getCreateTime()){
                operationLog.setCreateTime(DateUtils.now());
            }

            operationLog.setCreateTime(DateUtils.now());

            operationLogMapper.insert(operationLog);
            return true;
        }catch (Exception e){
            logger.error("failed on insert operationLog : " + e.getMessage());
            throw new Exception("failed on insert operationLog : ",e);
        }
    }

    @Override
    public Boolean delete(String id) throws Exception {
        try{
            OperationLog originOperationLog = get(id);
            operationLogMapper.delete(originOperationLog.getOperationId());
            return true;
        }catch (Exception e){
            logger.error("failed on delete operationLog : " + e.getMessage());
            throw new Exception("failed on delete operationLog : ",e);
        }
    }

    @Transactional
    @Override
    public Boolean batchDelete(String ids) throws Exception {
        try{
            if(StringUtils.isEmpty(ids)){
                throw new Exception("Ids can not empty!");
            }
            String[] idArr = ids.split(Consts.COMMA);
            for(String id : idArr){
                if(StringUtils.isNotEmpty(id)){
                    OperationLog originOperationLog = get(id);
                    operationLogMapper.delete(originOperationLog.getOperationId());
                }
            }
            return true;
        }catch (Exception e){
            logger.error("failed on delete operationLog : " + e.getMessage());
            throw new Exception("failed on delete operationLog : ",e);
        }
    }

    @Override
    public PageBean<OperationLog> page(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("","",conditionsStr,
                    pageSize,pageNo,sortColumn,sortType);

            PageBean<OperationLog> operationLogPageBean = new PageBean<OperationLog>();
            int totalRecords = operationLogMapper.count(criteriaMap);

            operationLogPageBean.setTotalSize(totalRecords);
            operationLogPageBean.setPageNo(pageNo);
            int numOfPages = Double.valueOf(Math.ceil((1.0 * totalRecords) / pageSize)).intValue();
            operationLogPageBean.setTotalPageNum(numOfPages);

            List<OperationLog> fundBasics = operationLogMapper.search(criteriaMap);
            if(CollectionUtils.isEmpty(fundBasics)){
                fundBasics = Collections.emptyList();
            }

            operationLogPageBean.setItem(fundBasics);
            return operationLogPageBean;
        }catch (Exception e){
            throw new Exception("Search operationLog exception : " + e.getMessage());
        }
    }



    @Override
    public List<OperationLog> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("", "", conditionsStr,
                    pageSize, pageNo, sortColumn, sortType);

            return operationLogMapper.search(criteriaMap);
        }catch (Exception e){
            throw new Exception("Search operationLog exception : " + e.getMessage());
        }
    }
}
