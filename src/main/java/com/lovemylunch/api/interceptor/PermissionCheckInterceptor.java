package com.lovemylunch.api.interceptor;

import com.lovemylunch.api.service.AdminUserService;
import com.lovemylunch.common.beans.ServiceCallResult;
import com.lovemylunch.common.beans.annotation.PermssionSecured;
import com.lovemylunch.common.beans.annotation.TokenSecured;
import com.lovemylunch.common.beans.system.AdminUser;
import com.lovemylunch.common.consts.Consts;
import com.lovemylunch.common.util.HttpUtil;
import com.lovemylunch.common.util.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class PermissionCheckInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(PermissionCheckInterceptor.class);

    AdminUserService adminUserService;

    public AdminUserService getAdminUserService() {
        return adminUserService;
    }

    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Call super to keep the existing process

        Boolean result = super.preHandle(request, response, handler);
        //Check if we are at the method level
        if (handler instanceof HandlerMethod) {
            HandlerMethod methodHandler = (HandlerMethod) handler;
            PermssionSecured securedAnnotation = methodHandler.getMethodAnnotation(PermssionSecured.class);
            //System.out.println("start to check permission");

            //Check if the method contain the @ClientAccountTokenCheck annotation
            if (securedAnnotation != null) {
                String currentPermissionCode = securedAnnotation.value();
                //logger.info("currentPermissionCode is : " + currentPermissionCode);
                if(StringUtils.isNotEmpty(currentPermissionCode)){
                    return permissionVerified(request, response,currentPermissionCode);
                }
            }
        }

        return result;
    }

    private boolean permissionVerified(HttpServletRequest request, HttpServletResponse response,String permissionCode)
            throws IOException {
        boolean result = false;
        try{
            final String authorizationToken = request.getHeader("authorization");
            if(StringUtils.isEmpty(authorizationToken)){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No valid authentication token found");
                return false;
            }

            String[] tokenArr = authorizationToken.split("\\|");

            final String tokenUserLogin = tokenArr[1];
            final String tokenUserId = tokenArr[2];

            //todo -- could get from redis in this to improve speed
            //logger.info("tokenUserLogin is : " + tokenUserLogin);
            AdminUser adminUser = adminUserService.getByLogin(tokenUserLogin);

            if(null != adminUser){
                String backendPermissons = adminUser.getBackend_permissions();
                //logger.info("backendPermissons is : " + backendPermissons);
                if(StringUtils.isNotEmpty(backendPermissons)){
                    if(backendPermissons.contains(permissionCode)){
                        result = true;
                        return true;
                    }else{
                        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "You don't have this permission!");
                        return false;
                    }
                }
            }
        }catch(Exception e){
            logger.error("check permission have exception : " + e.getMessage());
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Check Permission have exception");
            return false;
        }

        return result;
    }
}

