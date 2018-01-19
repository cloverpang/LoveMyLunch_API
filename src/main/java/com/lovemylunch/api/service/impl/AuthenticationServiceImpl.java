package com.lovemylunch.api.service.impl;

import com.lovemylunch.common.beans.system.TokenAndInfo;
import com.lovemylunch.common.security.MD5;
import com.lovemylunch.common.util.IDGenerator;
import com.lovemylunch.common.beans.ServiceCallResult;
import com.lovemylunch.common.beans.system.AccessTokenDO;
import com.lovemylunch.common.beans.system.AdminUser;
import com.lovemylunch.common.beans.system.TokenSession;
import com.alibaba.fastjson.JSON;
import com.lovemylunch.api.dao.SSOUserServiceDao;
import com.lovemylunch.api.dao.auth.TokenDaoImpl;
import com.lovemylunch.api.service.AuthenticationService;
import com.lovemylunch.api.service.AdminUserService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project Name    : Public-API
 * Package Name    : com.ai.api.service.impl
 * Creation Date   : 2016/8/23 17:53
 * Author          : Jianxiong.Cai
 * Purpose         : TODO
 * History         : TODO
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final String ssoToken = "4d09a5553e772093a7fea071b54cc510";

    @Autowired
    private TokenDaoImpl tokenDao;

	@Autowired
	private SSOUserServiceDao ssoUserServiceDao;

    @Autowired
    private AdminUserService adminUserService;


    @Override
    public ServiceCallResult userLogin(String userName, String password,HttpServletRequest request) {
        logger.info("userLogin ... userName:{}" ,userName);
        ServiceCallResult result = new ServiceCallResult();

        String userType = "admin";//default value is admin

        logger.info("getting client from database ... userName:"+userName);
        try {
            boolean foundUserInDataBase = false;

            //在数据库中查找用户
            String md5Password = MD5.toMD5(password);
            //"1" 表示的是 admin 类型用户
            AdminUser loginUser = new AdminUser();
            loginUser.setAdmin_login(userName);
            //loginUser.setAdmin_password(md5Password);
            loginUser.setAdmin_password(password);
            AdminUser user = adminUserService.login(loginUser);

            if(user != null){
                foundUserInDataBase = true;

                TokenSession tokenSession = tokenDao.generateToken(user.getAdmin_login(), user.getAdmin_id(), IDGenerator.uuid(), userType);

                if (tokenSession != null) {
                    TokenAndInfo tokenAndInfo = new TokenAndInfo();
                    tokenAndInfo.setTokenSession(tokenSession);
                    tokenAndInfo.setAdminUser(user);
                    tokenAndInfo.setOperationCenterCode(user.getOperationCenterCode());
                    //String token = JSON.toJSONString(tokenSession);
                    //result.setResponseString(token);
                    result.setResponseString(JSON.toJSONString(tokenAndInfo));
                    result.setStatusCode(HttpServletResponse.SC_OK);
                    result.setReasonPhase("User credential verified and token generated.");
                } else {
                    result.setResponseString("");
                    result.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    result.setReasonPhase("Error occurred while generating token.");
                }

            }else{
                logger.info("can't get user from database!");
                result.setResponseString("can't get user from database!");
                result.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
                result.setReasonPhase("can't get user from database!");
            }
        }catch (Exception e){ logger.error("Error Exception!can't get user from database!",e);
            result.setResponseString("Error Exception!can't get user from database!"+e);
            result.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
            result.setReasonPhase("Error Exception!can't get user from database!"+e);
        }

        return result;
    }

	@Override
	public ServiceCallResult removeAPIToken(HttpServletRequest request, HttpServletResponse response) {
		String authorization = request.getHeader("authorization");
		String apiAccessToken = request.getHeader("ai-api-access-token");

		try {
			//check api access token in header
			ServiceCallResult result = ssoUserServiceDao.checkAccessHeader(apiAccessToken);
			if (result.getStatusCode() != HttpServletResponse.SC_OK) {
				return result;
			}

			//find the token in redis
			String token = ssoUserServiceDao.getToken(authorization, response);

			if (token != null) {
                String[] tokenArr = token.split("\\|");

                final String tokenUserLogin = tokenArr[1];
                final String tokenUserId = tokenArr[2];
                final String tokenSessionId = tokenArr[3];
                final String userType = tokenArr[4];
                final String exprieTimeStr = tokenArr[5];

				//remove token session
				tokenDao.removePublicAPIToken(token,tokenSessionId);

				result.setStatusCode(HttpServletResponse.SC_OK);
				result.setReasonPhase("Token removed.");
				result.setResponseString("Token removed");
			} else {
				result.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
				result.setReasonPhase("Bad token.");
				result.setResponseString("Bad token.");
			}
			return result;
		} catch (Exception e) {
			logger.error("remove token error: " + ExceptionUtils.getFullStackTrace(e));
		}
		return null;
	}
}
