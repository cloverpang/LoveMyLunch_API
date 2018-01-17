package com.lovemylunch.api.controller.summary;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.api.service.SummaryService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.annotation.PermssionSecured;
import com.lovemylunch.common.beans.annotation.TokenSecured;
import com.lovemylunch.common.beans.order.SumItem;
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

import java.util.List;

@RestController
@SpringBootApplication
//@RequestMapping("/summary")
@Api(tags = {"summary"}, description = "Summary APIs")
public class SummaryController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(SummaryController.class);

    @Autowired
    private SummaryService summaryService;

    @TokenSecured
    @PermssionSecured(value="summary")
    @RequestMapping(value={"/summary"}, method= RequestMethod.GET)
    @ApiOperation(value = "Get order summary", response = SumItem.class,responseContainer =
            "List")
    public ResponseEntity<ApiCallResult> summary(@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                                @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate){
        logger.info("invoke: " + "/summary");
        ApiCallResult result = new ApiCallResult();
        try{
            List<SumItem> sumItems = summaryService.getSummary(startDate,endDate);
            result.setContent(sumItems);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get order summary failed : " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
