package com.lovemylunch.api.interceptor;

import com.lovemylunch.common.consts.Consts;
import com.lovemylunch.common.util.HttpUtil;
import com.lovemylunch.common.beans.annotation.TokenSecured;
import com.lovemylunch.common.beans.ServiceCallResult;

import com.lovemylunch.api.config.ServiceConfig;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class TokenCheckInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TokenCheckInterceptor.class);

    private String baseURL;

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    @Autowired
    private ServiceConfig serviceConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Call super to keep the existing process

        Boolean result = super.preHandle(request, response, handler);
        //Check if we are at the method level
        if (handler instanceof HandlerMethod) {
            HandlerMethod methodHandler = (HandlerMethod) handler;
            TokenSecured securedAnnotation = methodHandler.getMethodAnnotation(TokenSecured.class);

            System.out.println("start to handle request");
            System.out.println("base url is : " + baseURL);

            //Check if the method contain the @ClientAccountTokenCheck annotation
            if (securedAnnotation != null) {

                System.out.println("request.getRequestURL() : " + request.getRequestURL());
                System.out.println("request.getRemoteAddr() : " + request.getRemoteAddr());
                System.out.println("request.getLocalAddr() : " + request.getLocalAddr());

                System.out.println();
//                if (request.getRemoteAddr().equals(request.getLocalAddr()) ) {
//                    //skip for unit test case running or while developing
//                    return true;
//                }

                System.out.println("referer page is : " + request.getHeader("referer"));

                if (null != request.getHeader("referer")){
                    System.out.println("request.getHeader(\"referer\") : " + request.getHeader("referer"));

                    String requestedHost = request.getHeader("referer").replaceAll("/doc/index.html", "");
                    //String requestedURLNew = requestedHost + request.getRequestURI();
                    String requestedURLNew = requestedHost + request.getRequestURI().replaceAll("api/", "");

                    System.out.println("request.getRequestURL() : " + request.getRequestURL());
                    System.out.println("requestedURLNew : " + requestedURLNew);
                    if (request.getRequestURL().toString().equalsIgnoreCase(requestedURLNew)) {
                        //skip for request from swagger document 'try-it-out'
                        return true;
                    }
                }

                return tokenVerified(request, response);
            }
        }

        return result;
    }

    private boolean tokenVerified(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //check access token
        //boolean validateResult = HttpUtil.validatePublicAPICallToken(request);
        boolean validateResult = this.validatePublicAPICallToken(request);
        if (!validateResult) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, " API call token not present or invalid.");
            return false;
        }

        //check user token exists
        final String header = request.getHeader("authorization");
        if (header == null) {
            logger.error("No token found");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No valid authentication token found");
            return false;
        }

        //check user token in SSO
        //String apiAccessToken = HttpUtil.getPublicAPICallToken(request);
        String apiAccessToken = this.getPublicAPICallToken(request);
        //check request uri -- on production, url not start with /api/
        String requestURL = request.getRequestURI();
        if (requestURL.startsWith("/api/")) {
            requestURL = request.getRequestURI().substring(4);
        }
//        ServiceCallResult result = 	tokenJWTService.verifyPublicAPIToken(header, apiAccessToken, requestURL);
        ServiceCallResult result = 	this.verifyPublicAPIToken(header, apiAccessToken, requestURL);
        if (result.getStatusCode() == HttpServletResponse.SC_OK ) {
            response.setHeader("AI-Token", result.getResponseString());
            return true;
        } else {
//			if (result.getReasonPhase().equals("Expired token")) {
            logger.error(result.getResponseString());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, result.getReasonPhase());
//			} else {
//				logger.error("Passed token not valid after verification.");
//				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token not valid.");
//			}
            return false;
        }
    }

    public boolean validatePublicAPICallToken(HttpServletRequest request) {
        if(request.getRemoteAddr().equals(request.getLocalAddr())) {
            return true;
        } else {
            String pubKey = request.getHeader("ai-api-access-token");
            if(pubKey == null) {
                logger.error("No  public api access token found.");
                return false;
            } else if(!Consts.Http.PUBLIC_API_ACCESS_TOKENS.containsKey(pubKey)) {
                logger.error(" public api access token found but not correct." + pubKey);
                return false;
            } else {
                return true;
            }
        }
    }

    public String getPublicAPICallToken(HttpServletRequest request) {
        return request.getHeader("ai-api-access-token");
    }

    public ServiceCallResult verifyPublicAPIToken(final String header, final String apiAccessToken, final String requestURL) {
        //String uri = StringUtils.toString(new String[]{this.publicAPIUrl, "/auth/verify-token"});
        //String uri = "http://localhost:8080/auth/verify-token";
        String uri = baseURL + "auth/verify-token";
        try {
            ServiceCallResult result = HttpUtil.issueGetRequestCheckResponseString(uri, new HashMap() {
                private static final long serialVersionUID = -7820644900078882569L;

                {
                    this.put("authorization", header);
                    this.put("ai-api-access-token", apiAccessToken);
                    this.put("requested-url", requestURL);
                }
            });
            return result;
        } catch (IOException var7) {
            this.logger.error("Error happened when calling user service to verify client account token: " + ExceptionUtils.getStackTrace(var7));
            return null;
        }
    }
}

