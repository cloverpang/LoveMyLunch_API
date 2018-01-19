package com.lovemylunch;

import com.lovemylunch.api.service.AuthenticationService;
import com.lovemylunch.api.service.DistributionFormService;
import com.lovemylunch.api.service.OperationLogService;
import com.lovemylunch.common.beans.system.OperationLog;
import com.lovemylunch.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
    protected Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private DistributionFormService distributionFormService;

    @Autowired
    AuthenticationService authenticationService;

    //每天21;30,22:30,23:30分执行
    //生成配送单
    @Scheduled(cron = "0 30 15,21,22,23 * * ?")
    public void generateDistributionFormTask(){
        String currentTime= DateUtils.toFormatString(DateUtils.now(), DateUtils.Format.DATE_FORMAT_LOTUS.getValue());
        logger.info("Generate distribution form task start at :" + currentTime);
        String todayStr =  DateUtils.toFormatString(DateUtils.now(), DateUtils.Format.DATE_SEARCH_FORMAT.getValue());
        int result = 0;
        long start = System.currentTimeMillis();
        try{
           result = distributionFormService.generateDistributionForm(todayStr);
        }catch (Exception e){
           logger.error("Generate distribution form exception!" + e.getMessage());
        }

        try{
            OperationLog operationLog = new OperationLog();
            operationLog.setOperationUser("System Job");
            operationLog.setOperationToken("");
            operationLog.setOperationName("Auto generate distribution form in : " + currentTime);
            operationLog.setRequestParameters("");
            operationLog.setOperationUrl("Auto generate distribution form");
            operationLog.setOperationReturn(String.valueOf(result));
            operationLog.setOperationException("");

            long end = System.currentTimeMillis();

            long runTime = end - start;
            operationLog.setRunTime(runTime);
            operationLogService.insert(operationLog);
        }catch (Exception e){

        }

        logger.info("Generate distribution form task end!");
    }

    //每个小时第45分钟
    //删除过期token
    @Scheduled(cron = "0 45 * * * ?")
    public void removeExpireTokenTask(){

    }
}
