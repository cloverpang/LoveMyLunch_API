package com.lovemylunch.api.controller.distribut;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.api.service.DistributionFormService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.distribut.DistributionForm;
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

import java.util.Map;

@RestController
@SpringBootApplication
//@RequestMapping("/distributionForm")
@Api(tags = {"distributionForm"}, description = "DistributionForm APIs")
public class DistributionFormController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(DistributionFormController.class);

    @Autowired
    private DistributionFormService distributionFormService;

    @ApiOperation(value="get DistributionForm ", notes="",response = DistributionForm.class)
    @RequestMapping(value={"/distributionForm/{id}"}, method= RequestMethod.GET)
    public ResponseEntity<ApiCallResult> get(@PathVariable("id") String id){
        logger.info("invoke: " + "/distributionForm/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            DistributionForm distributionForm = distributionFormService.get(id);
            if (null != distributionForm) {
                result.setContent(distributionForm);
            } else {
                result.setMessage("can not found out this distributionForm ");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get distributionForm by id failed : " + e.getStackTrace());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value={"/distributionForms"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search distributionForm API", response = DistributionForm.class,responseContainer =
            "List")
    public ResponseEntity<ApiCallResult> search(@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                                                @ApiParam(value = "column name")
                                                @RequestParam(value = "sortColumn", required = false, defaultValue = "createTime") String sortColumn,
                                                @ApiParam(value = "desc or asc,default value is desc")
                                                @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                                @ApiParam(value = "like this - name::like::clover$type::=::AI")
                                                @RequestParam(value = "conditionsStr", required = false, defaultValue = "") String conditionsStr){
        logger.info("invoke: " + "/distributionForms");
        ApiCallResult result = new ApiCallResult();
        try{
            PageBean<DistributionForm> distributionFormPageBean = distributionFormService.page(conditionsStr, pageSize, pageNumber, sortColumn, sortType);
            result.setContent(distributionFormPageBean);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("search distributionForm failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/distributionForm", method = RequestMethod.POST)
    @ApiOperation(value = "Create distributionForm API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> createDistributionForm(
            @RequestBody DistributionForm distributionForm) {
        logger.info("invoke: " + "/distributionForm/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = distributionFormService.insert(distributionForm);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("create distributionForm failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/distributionForm", method = RequestMethod.PUT)
    @ApiOperation(value = "Save distributionForm API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> saveDistributionForm(@RequestBody DistributionForm distributionForm) {
        logger.info("invoke: " + "/distributionForm/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = distributionFormService.update(distributionForm);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("save distributionForm failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "distributionForm/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete distributionForm API", response = boolean.class)
    public ResponseEntity<ApiCallResult> deleteDistributionForm(@ApiParam(value = "id", required = true) @PathVariable("id") String id) {
        logger.info("invoke: " + "/distributionForm/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = distributionFormService.delete(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("delete distributionForm failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "distributionForm/generate/{date}", method = RequestMethod.PUT)
    @ApiOperation(value = "Generate distributionForm API", response = boolean.class)
    public ResponseEntity<ApiCallResult> generateDistributionForm(
            @ApiParam(value = "date", required = true)
            @PathVariable("date") String date) {
        logger.info("invoke: " + "/distributionForm/generate/" + date);
        ApiCallResult result = new ApiCallResult();
        try{
            int excute = distributionFormService.generateDistributionForm(date);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("Generate distributionForm failed : " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "distributionForm/selectDistributer/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "DistributionForm selectDistributer API", response = boolean.class)
    public ResponseEntity<ApiCallResult> selectDistributer(@ApiParam(value = "id", required = true)
                                                           @PathVariable("id") String id,
                                                           @RequestBody(required = true) Map map) {
        logger.info("invoke: " + "/distributionForm/selectDistributer/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            String distributerId = (String) map.get("distributerId");
            String distributerName = (String) map.get("distributerName");
            Boolean excute = distributionFormService.selectDistributer(id, distributerId, distributerName);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("DistributionForm selectDistributer failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "distributionForm/markArrived/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "MarkArrived distributionForm API", response = boolean.class)
    public ResponseEntity<ApiCallResult> markArrived(
            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/distributionForm/markArrived/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = distributionFormService.markArrived(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("MarkArrived distributionForm failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "distributionForm/markAllArrived", method = RequestMethod.PUT)
    @ApiOperation(value = "MarkArrived all distributionForm API", response = boolean.class)
    public ResponseEntity<ApiCallResult> markAllArrived() {
        logger.info("invoke: " + "/distributionForm/markArrived" );
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = distributionFormService.makeAllArrived();
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("MarkArrived all distributionForm failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
