package com.lovemylunch.api.service.impl;

import com.lovemylunch.api.dao.mybatis.mapper.CustomerMapper;
import com.lovemylunch.api.service.BaseService;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.client.Customer;
import com.lovemylunch.common.util.CriteriaMapUtils;
import com.lovemylunch.common.util.DateUtils;
import com.lovemylunch.common.util.IDUtils;
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
public class CustomerServiceImpl extends BaseService implements com.lovemylunch.api.service.CustomerService {
    protected Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer get(String id) throws Exception {
        try{
            return customerMapper.get(id);
        }catch (Exception e){
            logger.error("failed on get Customer info : " + e.getMessage());
            throw new Exception("failed on get Customer : ",e);
        }
    }

    @Override
    public Customer getByLogin(String login) throws Exception {
        try{
            return customerMapper.getByLogin(login);
        }catch (Exception e){
            logger.error("failed on get Customer info : " + e.getMessage());
            throw new Exception("failed on get Customer : ",e);
        }
    }

    @Override
    public Boolean insert(Customer customer) throws Exception {
        try{
            if(null == customer.getCustomerLogin()){
                throw new Exception("customer login is null! ");
            }

            Customer originCustomer = getByLogin(customer.getCustomerLogin());
            if(null != originCustomer){
                throw new Exception("This customer login is existing, please change an new login! ");
            }

            //re-set the CustomerId
            customer.setCustomerId(IDUtils.generateID());

            customer.setCreateTime(DateUtils.now());

            customerMapper.insert(customer);
            return true;
        }catch (Exception e){
            logger.error("failed on insert Customer : " + e.getMessage());
            throw new Exception("failed on insert Customer : ",e);
        }
    }

    @Override
    public Boolean update(Customer customer) throws Exception {
        try{
            customerMapper.update(customer);
            return true;
        }catch (Exception e){
            logger.error("failed on update Customer : " + e.getMessage());
            throw new Exception("failed on update Customer : ",e);
        }
    }

    @Override
    public Boolean delete(String id) throws Exception {
        try{
            Customer originCustomer = get(id);
            customerMapper.delete(originCustomer.getCustomerId());
            return true;
        }catch (Exception e){
            logger.error("failed on delete Customer : " + e.getMessage());
            throw new Exception("failed on delete Customer : ",e);
        }
    }

    @Override
    public PageBean<Customer> page(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("","",conditionsStr,
                    pageSize,pageNo,sortColumn,sortType);

            PageBean<Customer> CustomerPageBean = new PageBean<Customer>();
            int totalRecords = customerMapper.count(criteriaMap);

            CustomerPageBean.setTotalSize(totalRecords);
            CustomerPageBean.setPageNo(pageNo);
            int numOfPages = Double.valueOf(Math.ceil((1.0 * totalRecords) / pageSize)).intValue();
            CustomerPageBean.setTotalPageNum(numOfPages);

            List<Customer> fundBasics = customerMapper.search(criteriaMap);
            if(CollectionUtils.isEmpty(fundBasics)){
                fundBasics = Collections.emptyList();
            }

            CustomerPageBean.setItem(fundBasics);
            return CustomerPageBean;
        }catch (Exception e){
            throw new Exception("Search Customer exception : " + e.getMessage());
        }
    }

    @Override
    public List<Customer> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("", "", conditionsStr,
                    pageSize, pageNo, sortColumn, sortType);

            return customerMapper.search(criteriaMap);
        }catch (Exception e){
            throw new Exception("Search Customer exception : " + e.getMessage());
        }
    }
}
