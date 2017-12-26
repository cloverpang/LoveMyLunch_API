package com.lovemylunch.api.service.impl;

import com.lovemylunch.api.dao.mybatis.mapper.CompanyMapper;
import com.lovemylunch.api.service.BaseService;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.client.Company;
import com.lovemylunch.common.beans.client.extensions.CompanyExtension;
import com.lovemylunch.common.beans.client.extensions.CustomerExtension;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Service
public class CompanyServiceImpl extends BaseService implements com.lovemylunch.api.service.CompanyService {
    protected Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company get(String id) throws Exception {
        try{
            return companyMapper.get(id);
        }catch (Exception e){
            logger.error("failed on get company info : " + e.getMessage());
            throw new Exception("failed on get company : ",e);
        }
    }

    @Override
    public Boolean insert(Company company) throws Exception {
        try{
            //re-set the companyId
            company.setCompanyId(IDUtils.generateID());

            if(null == company.getJoinTime()){
                company.setJoinTime(DateUtils.now());
            }

            company.setCreateTime(DateUtils.now());

            companyMapper.insert(company);
            return true;
        }catch (Exception e){
            logger.error("failed on insert company : " + e.getMessage());
            throw new Exception("failed on insert company : ",e);
        }
    }

    @Override
    public Boolean update(Company company) throws Exception {
        try{
            companyMapper.update(company);
            return true;
        }catch (Exception e){
            logger.error("failed on update company : " + e.getMessage());
            throw new Exception("failed on update company : ",e);
        }
    }

    @Override
    public Boolean delete(String id) throws Exception {
        try{
            Company originCompany = get(id);
            companyMapper.delete(originCompany.getCompanyId());
            return true;
        }catch (Exception e){
            logger.error("failed on delete company : " + e.getMessage());
            throw new Exception("failed on delete company : ",e);
        }
    }

    @Override
    public PageBean<Company> page(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("","",conditionsStr,
                    pageSize,pageNo,sortColumn,sortType);

            PageBean<Company> companyPageBean = new PageBean<Company>();
            int totalRecords = companyMapper.count(criteriaMap);

            companyPageBean.setTotalSize(totalRecords);
            companyPageBean.setPageNo(pageNo);
            int numOfPages = Double.valueOf(Math.ceil((1.0 * totalRecords) / pageSize)).intValue();
            companyPageBean.setTotalPageNum(numOfPages);

            List<Company> fundBasics = companyMapper.search(criteriaMap);
            if(CollectionUtils.isEmpty(fundBasics)){
                fundBasics = Collections.emptyList();
            }

            companyPageBean.setItem(fundBasics);
            return companyPageBean;
        }catch (Exception e){
            throw new Exception("Search company exception : " + e.getMessage());
        }
    }



    @Override
    public List<Company> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("", "", conditionsStr,
                    pageSize, pageNo, sortColumn, sortType);

            return companyMapper.search(criteriaMap);
        }catch (Exception e){
            throw new Exception("Search company exception : " + e.getMessage());
        }
    }

    @Override
    public PageBean<CompanyExtension> pageExtend(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("","",conditionsStr,
                    pageSize,pageNo,sortColumn,sortType);

            PageBean<CompanyExtension> companyPageBean = new PageBean<CompanyExtension>();
            int totalRecords = companyMapper.count(criteriaMap);

            companyPageBean.setTotalSize(totalRecords);
            companyPageBean.setPageNo(pageNo);
            int numOfPages = Double.valueOf(Math.ceil((1.0 * totalRecords) / pageSize)).intValue();
            companyPageBean.setTotalPageNum(numOfPages);

            List<CompanyExtension> fundBasics = companyMapper.searchFull(criteriaMap);
            if(CollectionUtils.isEmpty(fundBasics)){
                fundBasics = Collections.emptyList();
            }

            companyPageBean.setItem(fundBasics);
            return companyPageBean;
        }catch (Exception e){
            throw new Exception("Search company exception : " + e.getMessage());
        }
    }
}
