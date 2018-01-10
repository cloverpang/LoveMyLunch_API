package com.lovemylunch.api.service.impl;

import com.lovemylunch.api.dao.mybatis.mapper.DistributionFormMapper;
import com.lovemylunch.api.service.BaseService;
import com.lovemylunch.api.service.CompanyService;
import com.lovemylunch.api.service.DistributionFormService;
import com.lovemylunch.api.service.LunchOrderService;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.client.Company;
import com.lovemylunch.common.beans.distribut.DistributionForm;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Service
@EnableTransactionManagement// 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven/>
public class DistributionFormServiceImpl extends BaseService implements DistributionFormService {
    protected Logger logger = LoggerFactory.getLogger(DistributionFormServiceImpl.class);

    @Autowired
    private DistributionFormMapper distributionFormMapper;

    @Autowired
    private LunchOrderService lunchOrderService;

    @Autowired
    private CompanyService companyService;

    @Override
    public DistributionForm get(String id) throws Exception {
        try{
            DistributionForm distributionForm =  distributionFormMapper.get(id);
            if(StringUtils.isNotEmpty(distributionForm.getOrderIds())){
                String conditionStr = "orderId::=::" + distributionForm.getOrderIds();
                List<LunchOrder> orders = lunchOrderService.search(conditionStr,1000,1,"","");
                if(CollectionUtils.isNotEmpty(orders)){
                    distributionForm.setOrders(orders);
                }
            }

            return distributionForm;
        }catch (Exception e){
            logger.error("failed on get DistributionForm info : " + e.getMessage());
            throw new Exception("failed on get DistributionForm : ",e);
        }
    }

    @Override
    public DistributionForm getByNumber(String number) throws Exception {
        try{
            return distributionFormMapper.getByNumber(number);
        }catch (Exception e){
            logger.error("failed on get DistributionForm info : " + e.getMessage());
            throw new Exception("failed on get DistributionForm : ",e);
        }
    }

    @Override
    public Boolean insert(DistributionForm distributionForm) throws Exception {
        try{
            if(StringUtils.isEmpty(distributionForm.getFormNumber())){
                throw new Exception("distributionForm form number can not empty! ");
            }

            DistributionForm originDistributionForm = getByNumber(distributionForm.getFormNumber());
            if(null != originDistributionForm){
                throw new Exception("This Distribution form number is existing, cannot insert again! ");
            }

            //re-set the DistributionFormId
            distributionForm.setDistributionFormId(IDUtils.generateID());

            distributionForm.setCreateTime(DateUtils.now());

            distributionFormMapper.insert(distributionForm);
            return true;
        }catch (Exception e){
            logger.error("failed on insert DistributionForm : " + e.getMessage());
            throw new Exception("failed on insert DistributionForm : ",e);
        }
    }

    @Override
    public Boolean update(DistributionForm distributionForm) throws Exception {
        try{
            distributionFormMapper.update(distributionForm);
            return true;
        }catch (Exception e){
            logger.error("failed on update DistributionForm : " + e.getMessage());
            throw new Exception("failed on update DistributionForm : ",e);
        }
    }

    @Override
    public Boolean selectDistributer(String id, String distributerId, String distributerName) throws Exception {
        try{
            DistributionForm distributionForm = distributionFormMapper.get(id);
            distributionForm.setDistributerId(distributerId);
            distributionForm.setDistributerName(distributerName);
            distributionFormMapper.update(distributionForm);
            return true;
        }catch (Exception e){
            logger.error("failed on update DistributionForm : " + e.getMessage());
            throw new Exception("failed on update DistributionForm : ",e);
        }
    }

    @Override
    public Boolean markArrived(String id) throws Exception {
        try{
            distributionFormMapper.makeArrived(id);
            return true;
        }catch (Exception e){
            logger.error("Mark DistributionForm arrived failed: " + e.getMessage());
            throw new Exception("Mark DistributionForm arrived failed : ",e);
        }
    }

    @Override
    public Boolean makeAllArrived() throws Exception {
        try{
            distributionFormMapper.makeAllArrived();
            return true;
        }catch (Exception e){
            logger.error("Mark All DistributionForm arrived failed: " + e.getMessage());
            throw new Exception("Mark All DistributionForm arrived failed : ",e);
        }
    }

    @Override
    public Boolean delete(String id) throws Exception {
        try{
            DistributionForm originDistributionForm = distributionFormMapper.get(id);
            distributionFormMapper.delete(originDistributionForm.getDistributionFormId());
            return true;
        }catch (Exception e){
            logger.error("failed on delete DistributionForm : " + e.getMessage());
            throw new Exception("failed on delete DistributionForm : ",e);
        }
    }

    @Override
    public PageBean<DistributionForm> page(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("","",conditionsStr,
                    pageSize,pageNo,sortColumn,sortType);

            PageBean<DistributionForm> DistributionFormPageBean = new PageBean<DistributionForm>();
            int totalRecords = distributionFormMapper.count(criteriaMap);

            DistributionFormPageBean.setTotalSize(totalRecords);
            DistributionFormPageBean.setPageNo(pageNo);
            int numOfPages = Double.valueOf(Math.ceil((1.0 * totalRecords) / pageSize)).intValue();
            DistributionFormPageBean.setTotalPageNum(numOfPages);

            List<DistributionForm> fundBasics = distributionFormMapper.search(criteriaMap);
            if(CollectionUtils.isEmpty(fundBasics)){
                fundBasics = Collections.emptyList();
            }

            DistributionFormPageBean.setItem(fundBasics);
            return DistributionFormPageBean;
        }catch (Exception e){
            throw new Exception("Search DistributionForm exception : " + e.getMessage());
        }
    }

    @Override
    public List<DistributionForm> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("", "", conditionsStr,
                    pageSize, pageNo, sortColumn, sortType);

            return distributionFormMapper.search(criteriaMap);
        }catch (Exception e){
            throw new Exception("Search DistributionForm exception : " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public int generateDistributionForm(String orderDate) throws Exception {
        int generateQuantity = 0;
        String startTime = orderDate +  " 00:00:00";
        String endTime = orderDate + " 23:59:59";

        String conditionStr = "bookTime::between::" + startTime + "," + endTime;
        List<LunchOrder> orders = lunchOrderService.search(conditionStr, 10000, 1, "", "");

        if(CollectionUtils.isEmpty(orders)){
            return generateQuantity;
        }

        Map<String, List<String>> groupOrders = groupLunchOrder(orders);

        List<String> companyIdList = new ArrayList<String>();
        Map<String, Company> companyMap = new HashMap<String, Company>();

        for (String key : groupOrders.keySet()) {
            companyIdList.add(key);
        }

        String companyIds = String.join(",", companyIdList);
        List<Company> companies = companyService.search("companyId::=" + companyIds, 10000, 1, "", "");
        if(CollectionUtils.isNotEmpty(companies)){
            for(Company c : companies){
                companyMap.put(c.getCompanyId(),c);
            }
        }

        for (String key : groupOrders.keySet()) {
            try{
                List<String> groupOrder =  groupOrders.get(key);
                String groupOrderIds = String.join(",", groupOrder);
                Company company = companyMap.get(key);

                DistributionForm distributionForm = new DistributionForm();
                distributionForm.setCompanyId(key);
                distributionForm.setOrderIds(groupOrderIds);

                if(null != company){
                    distributionForm.setCompanyName(company.getCompanyName());
                    distributionForm.setCompanyAddress(company.getCompanyAddress());
                    distributionForm.setFormNumber(company.getCompanyCode() + "_" + orderDate);
                    distributionForm.setStatus(0);
                }

                //insert real form
                boolean excute = insert(distributionForm);
                if(excute){
                    generateQuantity = generateQuantity + 1;
                }
            }catch (Exception e){

            }
        }

        return generateQuantity;
    }

    /**
     * 分组
     */
    private Map<String, List<String>> groupLunchOrder(List<LunchOrder> lunchOrderList) throws Exception{
        Map<String, List<String>> resultMap = new HashMap<String, List<String>>();

        try{
            for(LunchOrder order : lunchOrderList){

                if(resultMap.containsKey(order.getCompanyId())){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                    resultMap.get(order.getCompanyId()).add(order.getOrderId());
                }else{//map中不存在，新建key，用来存放数据
                    List<String> tmpList = new ArrayList<String>();
                    tmpList.add(order.getOrderId());
                    resultMap.put(order.getCompanyId(), tmpList);
                }
            }

        }catch(Exception e){
            throw new Exception("按照异常批次号对已开单数据进行分组时出现异常", e);
        }
        return resultMap;
    }
}
