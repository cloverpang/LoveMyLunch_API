package com.lovemylunch.api.controller.dashboard;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.api.service.DashboardService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.annotation.TokenSecured;
import com.lovemylunch.common.beans.dashboard.ChartData;
import com.lovemylunch.common.beans.dashboard.CreateCount;
import com.lovemylunch.common.beans.dashboard.Dashboard;
import com.lovemylunch.common.beans.order.SumItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
//@RequestMapping("/dashboard")
@Api(tags = {"dashboard"}, description = "Dashboard APIs")
public class DashboardController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private DashboardService dashboardService;

    @TokenSecured
    @RequestMapping(value={"/dashboard/summary"}, method= RequestMethod.GET)
    @ApiOperation(value = "Get  dashboard summary", response = Dashboard.class)
    public ResponseEntity<ApiCallResult> getDashboard(){
        logger.info("invoke: " + "/dashboard/summary");
        ApiCallResult result = new ApiCallResult();
        try{
            Dashboard dashboard = dashboardService.getDashoard();
            result.setContent(dashboard);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get dashboard summary failed : " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @RequestMapping(value={"/dashboard/order/chart"}, method= RequestMethod.GET)
    @ApiOperation(value = "Get order chart data", response = SumItem.class,responseContainer = "List")
    public ResponseEntity<ApiCallResult> getOrderData(@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                                         @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate){
        logger.info("invoke: " + "/dashboard/order");
        ApiCallResult result = new ApiCallResult();
        try{
            ChartData orderChartData = dashboardService.getOrderData(startDate, endDate);
            result.setContent(orderChartData);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get order dashboard failed : " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @RequestMapping(value={"/dashboard/customer/chart"}, method= RequestMethod.GET)
    @ApiOperation(value = "Get customer chart data", response = SumItem.class,responseContainer = "List")
    public ResponseEntity<ApiCallResult> getCustomerData(@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                                @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate){
        logger.info("invoke: " + "/dashboard/customer");
        ApiCallResult result = new ApiCallResult();
        try{
            ChartData customerChartData = dashboardService.getCustomerData(startDate,endDate);
            result.setContent(customerChartData);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get customer dashboard failed : " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
