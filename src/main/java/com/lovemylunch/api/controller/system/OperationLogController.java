package com.lovemylunch.api.controller.system;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.api.service.OperationLogService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.system.OperationLog;
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
//@RequestMapping("/operationLog")
@Api(tags = {"operationLog"}, description = "OperationLog APIs")
public class OperationLogController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(OperationLogController.class);

    @Autowired
    private OperationLogService operationLogService;

    @ApiOperation(value="get OperationLog ", notes="",response = OperationLog.class)
    @RequestMapping(value={"/operationLog/{id}"}, method= RequestMethod.GET)
    public ResponseEntity<ApiCallResult> get(@PathVariable("id") String id){
        logger.info("invoke: " + "/operationLog/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            OperationLog operationLog = operationLogService.get(id);
            if (null != operationLog) {
                result.setContent(operationLog);
            } else {
                result.setMessage("can not found out this operationLog ");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get operationLog by id failed : " + e.getStackTrace());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value={"/operationLogs"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search operationLog API", response = OperationLog.class,responseContainer =
            "List")
    public ResponseEntity<ApiCallResult> search(@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                                                @ApiParam(value = "column name")
                                                @RequestParam(value = "sortColumn", required = false, defaultValue = "createTime") String sortColumn,
                                                @ApiParam(value = "desc or asc,default value is desc")
                                                @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                                @ApiParam(value = "like this - name::like::clover$type::=::AI")
                                                @RequestParam(value = "conditionsStr", required = false, defaultValue = "") String conditionsStr){
        logger.info("invoke: " + "/operationLogs");
        ApiCallResult result = new ApiCallResult();
        try{
            PageBean<OperationLog> operationLogPageBean = operationLogService.page(conditionsStr, pageSize, pageNumber, sortColumn, sortType);
            result.setContent(operationLogPageBean);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("search operationLog failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/operationLog", method = RequestMethod.POST)
    @ApiOperation(value = "Create operationLog API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> createOperationLog(
            @RequestBody OperationLog operationLog) {
        logger.info("invoke: " + "/operationLog/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = operationLogService.insert(operationLog);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("create operationLog failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "operationLog/{id}", method = RequestMethod.DELETE)
         @ApiOperation(value = "Delete operationLog API", response = boolean.class)
         public ResponseEntity<ApiCallResult> deleteOperationLog(
            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/operationLog/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = operationLogService.delete(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("delete operationLog failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "operationLogs/{ids}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete operationLogs API", response = boolean.class)
    public ResponseEntity<ApiCallResult> batchDeleteOperationLog(
            @ApiParam(value = "ids", required = true)
            @PathVariable("ids") String ids) {
        logger.info("invoke: " + "/operationLogs/" + ids);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = operationLogService.batchDelete(ids);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("batch delete operationLogs failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
