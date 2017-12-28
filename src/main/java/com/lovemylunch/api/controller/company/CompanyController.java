package com.lovemylunch.api.controller.company;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.api.service.CompanyService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.client.Company;
import com.lovemylunch.common.beans.client.extensions.CompanyExtension;
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
//@RequestMapping("/company")
@Api(tags = {"company"}, description = "Company APIs")
public class CompanyController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value="get Company ", notes="",response = Company.class)
    @RequestMapping(value={"/company/{id}"}, method= RequestMethod.GET)
    public ResponseEntity<ApiCallResult> get(@PathVariable("id") String id){
        logger.info("invoke: " + "/company/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Company company = companyService.get(id);
            if (null != company) {
                result.setContent(company);
            } else {
                result.setMessage("can not found out this company ");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get company by id failed : " + e.getStackTrace());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value={"/companies"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search company API", response = Company.class,responseContainer =
            "List")
    public ResponseEntity<ApiCallResult> search(@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                                                @ApiParam(value = "column name")
                                                @RequestParam(value = "sortColumn", required = false, defaultValue = "createTime") String sortColumn,
                                                @ApiParam(value = "desc or asc,default value is desc")
                                                @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                                @ApiParam(value = "like this - name::like::clover$type::=::AI")
                                                @RequestParam(value = "conditionsStr", required = false, defaultValue = "") String conditionsStr){
        logger.info("invoke: " + "/companies");
        ApiCallResult result = new ApiCallResult();
        try{
            PageBean<Company> companyPageBean = companyService.page(conditionsStr, pageSize, pageNumber, sortColumn, sortType);
            result.setContent(companyPageBean);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("search company failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ApiOperation(value = "Create company API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> createCompany(
            @RequestBody Company company) {
        logger.info("invoke: " + "/company/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = companyService.insert(company);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("create company failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    @ApiOperation(value = "Save company API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> saveCompany(
            @RequestBody Company company) {
        logger.info("invoke: " + "/company/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = companyService.update(company);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("save company failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "company/{id}", method = RequestMethod.DELETE)
         @ApiOperation(value = "Delete company API", response = boolean.class)
         public ResponseEntity<ApiCallResult> deleteCompany(
            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/company/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = companyService.delete(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("delete company failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "companies/{ids}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete companies API", response = boolean.class)
    public ResponseEntity<ApiCallResult> batchDeleteCompany(
            @ApiParam(value = "ids", required = true)
            @PathVariable("ids") String ids) {
        logger.info("invoke: " + "/companies/" + ids);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = companyService.batchDelete(ids);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("batch delete companies failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value={"/companyExtends"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search company API", response = Company.class,responseContainer =
            "List")
    public ResponseEntity<ApiCallResult> searchFull(@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                                                @ApiParam(value = "column name")
                                                @RequestParam(value = "sortColumn", required = false, defaultValue = "createTime") String sortColumn,
                                                @ApiParam(value = "desc or asc,default value is desc")
                                                @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                                @ApiParam(value = "like this - name::like::clover$type::=::AI")
                                                @RequestParam(value = "conditionsStr", required = false, defaultValue = "") String conditionsStr){
        logger.info("invoke: " + "/companyExtends");
        ApiCallResult result = new ApiCallResult();
        try{
            PageBean<CompanyExtension> companyPageBean = companyService.pageExtend(conditionsStr, pageSize, pageNumber, sortColumn, sortType);
            result.setContent(companyPageBean);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("search company failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
