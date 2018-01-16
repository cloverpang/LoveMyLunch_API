package com.lovemylunch.api.controller.order;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.api.service.LunchOrderService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.annotation.TokenSecured;
import com.lovemylunch.common.beans.order.LunchOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
//@RequestMapping("/lunchOrder")
@Api(tags = {"lunchOrder"}, description = "LunchOrder APIs")
public class LunchOrderController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(LunchOrderController.class);

    @Autowired
    private LunchOrderService lunchOrderService;

    @TokenSecured
    @ApiOperation(value="get LunchOrder ", notes="",response = LunchOrder.class)
    @RequestMapping(value={"/lunchOrder/{id}"}, method= RequestMethod.GET)
    public ResponseEntity<ApiCallResult> get(@PathVariable("id") String id){
        logger.info("invoke: " + "/lunchOrder/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            LunchOrder lunchOrder = lunchOrderService.get(id);
            if (null != lunchOrder) {
                result.setContent(lunchOrder);
            } else {
                result.setMessage("can not found out this lunchOrder ");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get lunchOrder by id failed : " + e.getStackTrace());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @RequestMapping(value={"/lunchOrders"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search lunchOrder API", response = LunchOrder.class,responseContainer =
            "List")
    public ResponseEntity<ApiCallResult> search(@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                                                @ApiParam(value = "column name")
                                                @RequestParam(value = "sortColumn", required = false, defaultValue = "createTime") String sortColumn,
                                                @ApiParam(value = "desc or asc,default value is desc")
                                                @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                                @ApiParam(value = "like this - name::like::clover$type::=::AI")
                                                @RequestParam(value = "conditionsStr", required = false, defaultValue = "") String conditionsStr){
        logger.info("invoke: " + "/lunchOrders");
        ApiCallResult result = new ApiCallResult();
        try{
            PageBean<LunchOrder> lunchOrderPageBean = lunchOrderService.page(conditionsStr, pageSize, pageNumber, sortColumn, sortType);
            result.setContent(lunchOrderPageBean);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("search lunchOrder failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @RequestMapping(value = "/lunchOrder", method = RequestMethod.POST)
    @ApiOperation(value = "Create lunchOrder API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> createLunchOrder(
            @RequestBody LunchOrder lunchOrder) {
        logger.info("invoke: " + "/lunchOrder/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = lunchOrderService.insert(lunchOrder);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("create lunchOrder failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @RequestMapping(value = "/lunchOrder", method = RequestMethod.PUT)
    @ApiOperation(value = "Save lunchOrder API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> saveLunchOrder(
            @RequestBody LunchOrder lunchOrder) {
        logger.info("invoke: " + "/lunchOrder/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = lunchOrderService.update(lunchOrder);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("save lunchOrder failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @RequestMapping(value = "lunchOrder/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete lunchOrder API", response = boolean.class)
    public ResponseEntity<ApiCallResult> deleteLunchOrder(
            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/lunchOrder/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = lunchOrderService.delete(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("delete lunchOrder failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @RequestMapping(value = "lunchOrder/cancal/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "MarkNotUse lunchOrder API", response = boolean.class)
    public ResponseEntity<ApiCallResult> cancal(
            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/lunchOrder/cancal/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = lunchOrderService.cancal(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("Cancal lunchOrder failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
