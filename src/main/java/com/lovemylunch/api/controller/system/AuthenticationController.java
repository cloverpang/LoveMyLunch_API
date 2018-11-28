package com.lovemylunch.api.controller.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovemylunch.api.dao.SSOUserServiceDao;
import com.lovemylunch.api.service.AdminUserService;
import com.lovemylunch.api.service.AuthenticationService;
import com.lovemylunch.common.beans.ServiceCallResult;
import com.lovemylunch.common.beans.annotation.NotLogMethod;
import com.lovemylunch.common.beans.system.LoginBean;
import com.lovemylunch.common.util.HttpUtil;
import com.lovemylunch.common.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@SpringBootApplication
@Api(tags = {"Authentication"}, description = "Authentication APIs")
public class AuthenticationController {
    protected Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    SSOUserServiceDao ssoUserServiceDao; // Service which will do all data
    // retrieval/manipulation work

    @Autowired
    AuthenticationService authenticationService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/auth/token")
    @ApiOperation(value = "User Login Then Get Api Token", response = String.class)
    public String userLogin(@ApiParam(required = true) @RequestBody LoginBean loginBean, HttpServletRequest request,
                            HttpServletResponse response) throws JsonProcessingException {
        logger.info("user login ......");
        // user can be client or employee
        String account = loginBean.getAccount();
        System.out.println("account is " + account);
        // password should be in MD5 format
        String password = loginBean.getPassword();
        String userType = loginBean.getUserType();
        logger.info("account:" + account);
        logger.info("userType:" + userType);
        ServiceCallResult result = new ServiceCallResult();
        ObjectMapper mapper = new ObjectMapper();

        if ((StringUtils.isBlank(account)) || (StringUtils.isBlank(password))) {
            result.setStatusCode(HttpServletResponse.SC_FORBIDDEN);
            result.setResponseString("");// username and password can not be
            // null
            result.setReasonPhase("username/password empty");
            return mapper.writeValueAsString(result);
        }

        // check api access token
        boolean validateResult = HttpUtil.validatePublicAPICallToken(request);
        logger.info("validate token result :" + validateResult);
        if (!validateResult) {
            result.setStatusCode(HttpServletResponse.SC_FORBIDDEN);
            result.setResponseString("");
            result.setReasonPhase("API call token not present or invalid for login.");
            return mapper.writeValueAsString(result);
        }
        logger.info("start to get user from user-service --> verify pw --> generate tokenSession");
        result = authenticationService.userLogin(account, password, request);
        logger.info("user login result: " + result.getResponseString());
        return mapper.writeValueAsString(result);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/auth/refresh-token")
    @ApiOperation(value = "Refresh Api Token", response = String.class)
    public String refreshAPIToken(HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {

        logger.info("refresh token ...........");
        ObjectMapper mapper = new ObjectMapper();
        ServiceCallResult result = ssoUserServiceDao.refreshAPIToken(request, response);
        logger.info("refresh token result: " + result.getResponseString());
        return mapper.writeValueAsString(result);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/auth/remove-token")
    @ApiOperation(value = "Remove Api Token", response = String.class)
    public String removeAPIToken(HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("remove token ...........");
        ServiceCallResult result = authenticationService.removeAPIToken(request, response);
        logger.info("remove token result: " + result.getResponseString());
        return mapper.writeValueAsString(result);
    }

    @NotLogMethod
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/auth/verify-token")
    @ApiOperation(value = "Verify Public Api Token", response = String.class)
    public String verifyPublicAPIToken(HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("verify token ...........");
        ServiceCallResult result = ssoUserServiceDao.verifyAPIToken(request, response);
        logger.info("verify token result: " + result.getResponseString());
        return mapper.writeValueAsString(result);
    }
}
