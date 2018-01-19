package com.lovemylunch.api.controller.distribut;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.api.service.DistributerService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.annotation.PermssionSecured;
import com.lovemylunch.common.beans.annotation.TokenSecured;
import com.lovemylunch.common.beans.distribut.Distributer;
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
@RequestMapping(value={"/{center}"})
@Api(tags = {"distributer"}, description = "Distributer APIs")
public class DistributerController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(DistributerController.class);

    @Autowired
    private DistributerService distributerService;

    @TokenSecured
    @PermssionSecured(value="distributer_get_one")
    @ApiOperation(value="get Distributer ", notes="",response = Distributer.class)
    @RequestMapping(value={"/distributer/{id}"}, method= RequestMethod.GET)
    public ResponseEntity<ApiCallResult> get(@PathVariable String center,@PathVariable("id") String id){
        logger.info("invoke: " + "/distributer/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Distributer distributer = distributerService.get(id);
            if (null != distributer) {
                result.setContent(distributer);
            } else {
                result.setMessage("can not found out this distributer ");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get distributer by id failed : " + e.getStackTrace());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="distributer_get_list")
    @RequestMapping(value={"/distributers"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search distributer API", response = Distributer.class,responseContainer =
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
        logger.info("invoke: " + "/distributers");
        ApiCallResult result = new ApiCallResult();
        try{
            conditionsStr = conditionsStr + "$operationCenterCode::=::" + center;
            PageBean<Distributer> distributerPageBean = distributerService.page(conditionsStr, pageSize, pageNumber, sortColumn, sortType);
            result.setContent(distributerPageBean);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("search distributer failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="distributer_add")
    @RequestMapping(value = "/distributer", method = RequestMethod.POST)
    @ApiOperation(value = "Create distributer API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> createDistributer(@PathVariable String center,
            @RequestBody Distributer distributer) {
        logger.info("invoke: " + "/distributer/");
        ApiCallResult result = new ApiCallResult();
        try{
            distributer.setOperationCenterCode(center);
            Boolean excute = distributerService.insert(distributer);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("create distributer failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="distributer_update")
    @RequestMapping(value = "/distributer", method = RequestMethod.PUT)
    @ApiOperation(value = "Save distributer API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> saveDistributer(@PathVariable String center,
            @RequestBody Distributer distributer) {
        logger.info("invoke: " + "/distributer/");
        ApiCallResult result = new ApiCallResult();
        try{
            distributer.setOperationCenterCode(center);
            Boolean excute = distributerService.update(distributer);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("save distributer failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="distributer_delete")
    @RequestMapping(value = "distributer/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete distributer API", response = boolean.class)
    public ResponseEntity<ApiCallResult> deleteDistributer(@PathVariable String center,
            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/distributer/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = distributerService.delete(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("delete distributer failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
