/***************************************************************************
 * This document contlovemylunchns confidential and proprietary information
 * subject to non-disclosure agreements with AsiaInspection. This
 * information shall not be distributed or copied without written
 * permission from the AsiaInspection.
 ***************************************************************************/
package com.lovemylunch.api.dao.auth;

import com.lovemylunch.common.consts.Consts;
import com.lovemylunch.common.util.HttpUtil;
import com.lovemylunch.common.util.IDGenerator;
import com.lovemylunch.common.util.StringUtils;
import com.lovemylunch.common.beans.ServiceCallResult;
import com.lovemylunch.common.beans.system.TokenSession;
import com.alibaba.fastjson.JSON;
import com.lovemylunch.api.dao.SSOUserServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SSOUserServiceDaoImpl implements SSOUserServiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(SSOUserServiceDaoImpl.class);
	
	@Autowired
	private TokenDaoImpl tokenDao;

	@Override
	public ServiceCallResult refreshAPIToken(HttpServletRequest request,HttpServletResponse response) {
        String accessToken = request.getHeader("ai-api-access-token");
        String authorization = request.getHeader("authorization");

		try {
            ServiceCallResult result = this.checkAccessHeader(accessToken);
            if (result.getStatusCode() != HttpServletResponse.SC_OK) {
                return result;
            }
			if (!HttpUtil.validateRefreshTokenKey(request)) {

				result.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
				result.setReasonPhase("Refresh key invalid.");
				result.setResponseString("Please check your token refresh key.");
				return result;
			}

            String token = this.getToken(authorization, response);

            if (StringUtils.isNotEmpty(token)) {
				String[] tokenArr = token.split("\\|");

				final String tokenUserLogin = tokenArr[1];
				final String tokenUserId = tokenArr[2];
				final String tokenSessionId = tokenArr[3];
				final String userType = tokenArr[4];
				final String exprieTimeStr = tokenArr[5];
				final String operationCenter = tokenArr[6];

				String oldToken = tokenDao.getTokenFromRedisOrDatabase(token, tokenSessionId);
	            //TokenSession oldToken = tokenDao.getTokenSessionFromRedis(tokenSessionId);

	            TokenSession tokenSession = null;
	            if (StringUtils.isNotEmpty(oldToken)){
					TokenSession oldTokenSession = tokenDao.getTokenSessionFromRedis(tokenSessionId);
					if(null != oldTokenSession){
						tokenSession = tokenDao.generateToken(tokenUserLogin, oldTokenSession.getUserId(),
								oldTokenSession.getId(), userType,operationCenter);
						LOGGER.info("refresh generate token in redis");
					}else{
						LOGGER.info("redis have issue!!!");
						tokenSession = tokenDao.generateToken(tokenUserLogin, tokenUserId,
								IDGenerator.uuid(), userType,operationCenter);
						LOGGER.info("refresh generate token in database");
					}
	            }
                if (null==tokenSession) {
                    //not valid
                    result.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
                    result.setReasonPhase("Bad token to refresh");
                    result.setResponseString("Bad token to refresh");
                } else {
                    //valid
	                String resultJWT = JSON.toJSONString(tokenSession);
                    result.setStatusCode(HttpServletResponse.SC_OK);
                    result.setReasonPhase("Token refreshed");
                    result.setResponseString(resultJWT);
                }
            } else {
                result.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
                result.setReasonPhase("No token found");
                result.setResponseString("");
            }
            return result;
		} catch (Exception e) {
			//LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error("refreshAPIToken exception : " + e.getStackTrace());
		}
		return null;
	}

	@Override
	public ServiceCallResult verifyAPIToken(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("verifyAPIToken request start ");
		System.out.println("verifyAPIToken request start at : " + request.getRequestURI());

		String authorization = request.getHeader("authorization");
		String apiAccessToken = request.getHeader("ai-api-access-token");
		String requestedURL = request.getHeader("requested-url");

		if(StringUtils.isEmpty(requestedURL)){
			requestedURL = request.getRequestURI();
		}else{
			requestedURL = requestedURL.toLowerCase();
		}

		try {
			System.out.println("authorization : " + authorization);
			System.out.println("apiAccessToken : " + apiAccessToken);
			System.out.println("requestedURL : " + requestedURL);
			//check api access token in header
			ServiceCallResult result = checkAccessHeader(apiAccessToken);
			if (result.getStatusCode() != HttpServletResponse.SC_OK) {
				return result;
			}
			String token = this.getToken(authorization, response);
			//LOGGER.info("get token  :"+token);
			if (StringUtils.isNotEmpty(token)) {
				String[] tokenArr = token.split("\\|");

				final String tokenUserLogin = tokenArr[1];
				final String tokenUserId = tokenArr[2];
				final String tokenSessionId = tokenArr[3];
				final String userType = tokenArr[4];
				final String exprieTimeStr = tokenArr[5];
				final String operationCenter = tokenArr[6];
				LOGGER.info("userid: " + tokenUserId + ", usertype: " + userType);
				LOGGER.info("requested url: " + requestedURL);

				//check session in redis
				//TokenSession oldToken = tokenDao.getTokenSessionFromRedis(tokenSessionId);
				String oldToken = tokenDao.getTokenFromRedisOrDatabase(token,tokenSessionId);
				if (StringUtils.isEmpty(oldToken)||!token.equals(oldToken)){
					//not valid
					result.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
					result.setReasonPhase("Bad token");
					result.setResponseString("Bad token");
					return result;
				}

				//check the token center is equal to current request center
				String[] requestUrlArr =  requestedURL.split("/");
				String currentRequestCenter = requestUrlArr[2];
				LOGGER.info("currentRequestCenter: " + currentRequestCenter);
				if(!operationCenter.equals(currentRequestCenter)){
					result.setStatusCode(HttpServletResponse.SC_FORBIDDEN);
					result.setReasonPhase("You are not belong to this operation center!");
					result.setResponseString("You are not belong to this operation center!");
					return result;
				}

				boolean stillAlive = tokenDao.checkIfExpired(exprieTimeStr);
				if (stillAlive) {
					//valid
					result.setStatusCode(HttpServletResponse.SC_OK);
					result.setReasonPhase("Token verified");
					result.setResponseString("Token verified");

					//todo - maybe need add an new logic, if exprie time - current time < 60 mintues, update token exprie time

				} else  {
					//expired, please renew with refresh key
					result.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
					result.setReasonPhase("Expired token");
					result.setResponseString("Please renew your token using refresh key.");
				}
			} else {
				result.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
				result.setReasonPhase("No token found");
				result.setResponseString("");
			}
			return result;
		} catch (Exception e) {
			//LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error("verifyAPIToken exception : " + e.getStackTrace());
		}
		return null;
	}

	@Override
	public ServiceCallResult checkAccessHeader(final String headerValue) {
		ServiceCallResult result = new ServiceCallResult();
		if (headerValue == null || headerValue.isEmpty()) {
			result.setStatusCode(HttpServletResponse.SC_FORBIDDEN);
			result.setResponseString("");
			result.setReasonPhase("AI API call token not present.");
		} else if (!Consts.Http.PUBLIC_API_ACCESS_TOKENS.containsKey(headerValue)) {
			result.setStatusCode(HttpServletResponse.SC_FORBIDDEN);
			result.setResponseString("");
			result.setReasonPhase("AI public api access token found but not correct.");
		} else {
			result.setStatusCode(HttpServletResponse.SC_OK);
			result.setResponseString("OK");
			result.setReasonPhase("");
		}
//		LOGGER.info("checkAccessHeader result :"+JSON.toJSONString(result));
		return result;
	}


	@Override
    public String getToken(String authorizationHeader, HttpServletResponse response) throws IOException {
        String token = null;
        if (authorizationHeader == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Unauthorized: No Authorization header was found");
	        return null;
        }

		token = authorizationHeader;

//        String[] parts = authorizationHeader.split(" ");
//        if (parts.length != 2) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
//                    "Unauthorized: Format is Authorization: Bearer [token]");
//        } else {
//            String scheme = parts[0];
//            String credentials = parts[1];
//            Pattern pattern = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
//            if (pattern.matcher(scheme).matches()) {
//                token = credentials;
//            }
//        }

		System.out.println("getToken : " + token);

        return token;
    }
}
