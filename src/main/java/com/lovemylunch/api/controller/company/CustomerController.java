package com.lovemylunch.api.controller.customer;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.api.service.CustomerService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.annotation.PermssionSecured;
import com.lovemylunch.common.beans.annotation.TokenSecured;
import com.lovemylunch.common.beans.client.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
@RequestMapping(value={"/{center}"})
@Api(tags = {"customer"}, description = "Customer APIs")
public class CustomerController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @TokenSecured
    @PermssionSecured(value="customer_get_one")
    @ApiOperation(value="get Customer ", notes="",response = Customer.class)
    @RequestMapping(value={"/customer/{id}"}, method= RequestMethod.GET)
    public ResponseEntity<ApiCallResult> get(@PathVariable String center,@PathVariable("id") String id){
        logger.info("invoke: " + "/customer/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Customer customer = customerService.get(id);
            if (null != customer) {
                result.setContent(customer);
            } else {
                result.setMessage("can not found out this customer ");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get customer by id failed : " + e.getStackTrace());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="customer_get_list")
    @RequestMapping(value={"/customers"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search customer API", response = Customer.class,responseContainer =
            "List")
    public ResponseEntity<ApiCallResult> search(@PathVariable String center,
                                                @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                                                @ApiParam(value = "column name")
                                                @RequestParam(value = "sortColumn", required = false, defaultValue = "createTime") String sortColumn,
                                                @ApiParam(value = "desc or asc,default value is desc")
                                                @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                                @ApiParam(value = "like this - name::like::clover$type::=::AI")
                                                @RequestParam(value = "conditionsStr", required = false, defaultValue = "") String conditionsStr){
        logger.info("invoke: " + "/customers");
        ApiCallResult result = new ApiCallResult();
        try{
            conditionsStr = conditionsStr + "$operationCenterCode::=::" + center;
            PageBean<Customer> customerPageBean = customerService.page(conditionsStr, pageSize, pageNumber, sortColumn, sortType);
            result.setContent(customerPageBean);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("search customer failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="customer_add")
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ApiOperation(value = "Create customer API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> createCustomer(@PathVariable String center,
            @RequestBody Customer customer) {
        logger.info("invoke: " + "/customer/");
        ApiCallResult result = new ApiCallResult();
        try{
            customer.setOperationCenterCode(center);
            Boolean excute = customerService.insert(customer);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("create customer failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="customer_update")
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ApiOperation(value = "Save customer API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> saveCustomer(@PathVariable String center,
            @RequestBody Customer customer) {
        logger.info("invoke: " + "/customer/");
        ApiCallResult result = new ApiCallResult();
        try{
            customer.setOperationCenterCode(center);
            Boolean excute = customerService.update(customer);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("save customer failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="customer_delete")
    @RequestMapping(value = "customer/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete customer API", response = boolean.class)
    public ResponseEntity<ApiCallResult> deleteCustomer(
            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/customer/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = customerService.delete(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("delete customer failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
