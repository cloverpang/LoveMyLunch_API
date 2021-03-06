package com.lovemylunch.api.controller.dashboard;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.api.service.DashboardService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.annotation.PermssionSecured;
import com.lovemylunch.common.beans.annotation.TokenSecured;
import com.lovemylunch.common.beans.dashboard.ChartData;
import com.lovemylunch.common.beans.dashboard.CreateCount;
import com.lovemylunch.common.beans.dashboard.Dashboard;
import com.lovemylunch.common.beans.order.SumItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping(value={"/{center}"})
@Api(tags = {"dashboard"}, description = "Dashboard APIs")
public class DashboardController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private DashboardService dashboardService;

    @TokenSecured
    @PermssionSecured(value="dashboard_summary")
    @RequestMapping(value={"/dashboard/summary"}, method= RequestMethod.GET)
    @ApiOperation(value = "Get  dashboard summary", response = Dashboard.class)
    public ResponseEntity<ApiCallResult> getDashboard(@PathVariable String center,                                                @ApiParam(value = "forceData")
    @RequestParam(value = "force", required = false, defaultValue = "notForceData") String force){
        logger.info("invoke: " + "/dashboard/summary");
        ApiCallResult result = new ApiCallResult();
        try{
            Dashboard dashboard = dashboardService.getDashoard(center,force);
            result.setContent(dashboard);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get dashboard summary failed : " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="dashboard_order_chart")
    @RequestMapping(value={"/dashboard/order/chart"}, method= RequestMethod.GET)
    @ApiOperation(value = "Get order chart data", response = SumItem.class,responseContainer = "List")
    public ResponseEntity<ApiCallResult> getOrderData(@PathVariable String center,
                                                      @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                                      @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                                                      @ApiParam(value = "force")
                                                      @RequestParam(value = "force", required = false, defaultValue = "notForceData") String force){
        logger.info("invoke: " + "/dashboard/order");
        ApiCallResult result = new ApiCallResult();
        try{
            ChartData orderChartData = dashboardService.getOrderData(center,startDate, endDate,force);
            result.setContent(orderChartData);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get order dashboard failed : " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="dashboard_customer_chart")
    @RequestMapping(value={"/dashboard/customer/chart"}, method= RequestMethod.GET)
    @ApiOperation(value = "Get customer chart data", response = SumItem.class,responseContainer = "List")
    public ResponseEntity<ApiCallResult> getCustomerData(@PathVariable String center,
                                                         @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                                         @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                                                         @ApiParam(value = "force")
                                                         @RequestParam(value = "force", required = false, defaultValue = "notForceData") String force){
        logger.info("invoke: " + "/dashboard/customer");
        ApiCallResult result = new ApiCallResult();
        try{
            ChartData customerChartData = dashboardService.getCustomerData(center,startDate,endDate,force);
            result.setContent(customerChartData);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get customer dashboard failed : " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
