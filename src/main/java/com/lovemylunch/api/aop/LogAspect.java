package com.lovemylunch.api.aop;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.system.OperationLog;
import com.lovemylunch.api.service.OperationLogService;

import com.lovemylunch.common.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lovemylunch.common.util.JsonUtils;

@Aspect
@Component
@Order(1)
public class LogAspect {
    private static final Logger LOGGER = Logger.getLogger(LogAspect.class);

    @Autowired
    private OperationLogService operationLogService;

    @Pointcut("execution(* com.lovemylunch.api.controller..*.*(..))")
    public void normalControllerLog(){}

    @Around("normalControllerLog() && @annotation(apiOperation)")
    public Object around(ProceedingJoinPoint pjp,ApiOperation apiOperation) throws Throwable{
        long start = System.currentTimeMillis();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String requestURI = request.getRequestURI();

        Object[] args = pjp.getArgs();
        Signature sig = pjp.getSignature();
        MethodSignature msig = (MethodSignature) sig;

        String operationName = "";
        String resultStr = "";
        String exceptionStr = "";
        StringBuilder parameterStr = new StringBuilder();

        if(null != apiOperation){
            operationName = apiOperation.value();
        }

        Object rvt = null;
        try{
            rvt =  pjp.proceed();
            //LOGGER.info("result : " + JsonUtils.toJson(rvt));

            ResponseEntity<ApiCallResult> resultObject = (ResponseEntity<ApiCallResult>) rvt;
            resultStr = JsonUtils.toJson(resultObject.getBody().getContent());

            if(null != args && args.length > 0){
                String[] parameterNames = msig.getParameterNames();

                for(int i = 0;i<args.length;i++){
                    //LOGGER.info("para : " + parameterNames[i] + ":" + JsonUtils.toJson(args[i]));
                    parameterStr.append(parameterNames[i] + ":" + JsonUtils.toJson(args[i]) + " - ");
                }
            }

        }catch (Exception e){
            //LOGGER.info("exception : " + e.getMessage());
            exceptionStr = e.getMessage();
        }

        long end = System.currentTimeMillis();

        long runTime = end - start;

        try{
            if(StringUtils.isNotEmpty(operationName) && (operationName != "Search operationLog" || operationName != "get OperationLog")){
                OperationLog operationLog = new OperationLog();
                operationLog.setOperationUser("clover");//todo
                operationLog.setOperationToken("clover token");//todo
                operationLog.setOperationName(operationName);
                operationLog.setRequestParameters(parameterStr.toString());
                operationLog.setOperationUrl(requestURI);
                operationLog.setOperationReturn(resultStr);
                operationLog.setOperationException(exceptionStr);
                operationLog.setRunTime(runTime);
                operationLogService.insert(operationLog);
            }
        }catch (Exception e){
            LOGGER.error("Can not save operation log : " + e.getMessage());
        }

        LOGGER.info("Run time monitor : " + requestURI + " Run time is " + runTime + "ms");
        //System.out.println("Run time monitor : " + requestURI + " Run time is " + runTime + "ms");

        return rvt;
    }
}
