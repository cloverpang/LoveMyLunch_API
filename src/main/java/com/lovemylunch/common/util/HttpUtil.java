package com.lovemylunch.common.util;

import com.lovemylunch.common.consts.Consts.Http;
import com.lovemylunch.common.beans.GetRequest;
import com.lovemylunch.common.beans.RestServiceCallClient;
import com.lovemylunch.common.beans.ServiceCallResult;
import com.lovemylunch.common.http.InputStreamFileObject;
import com.lovemylunch.common.http.SimpleFileObject;
import com.lovemylunch.common.beans.UserSecurityModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

public final class HttpUtil {
    private static final Logger LOGGER = Logger.getLogger(HttpUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public HttpUtil() {
    }

    public static ServiceCallResult issueGetRequest(String service, Map<String, String> headers) throws IOException {
        LOGGER.info("issueGetRequest! service:" + service);
        HttpGet httpGet = new HttpGet(service);
        setCommonHeader(httpGet);
        setHeader(httpGet, headers);
        ServiceCallResult result = execute(httpGet);
        if(result.getStatusCode() != HttpStatus.OK.value() && !result.getReasonPhase().equalsIgnoreCase("OK")) {
            LOGGER.info("issueGetRequest result:" + result);
        }

        return result;
    }

    public static ServiceCallResult issueGetRequestCheckResponseString(String service, Map<String, String> headers) throws IOException {
        LOGGER.info("issueGetRequestCheckResponseString! service:" + service);
        HttpGet httpGet = new HttpGet(service);
        setCommonHeader(httpGet);
        setHeader(httpGet, headers);
        ServiceCallResult result = executeCheckResponseString(httpGet);
        if(result.getStatusCode() != HttpStatus.OK.value() && !result.getReasonPhase().equalsIgnoreCase("OK")) {
            LOGGER.info("issueGetRequestCheckResponseString result:" + result);
        }

        return result;
    }

    public static ServiceCallResult issueGetRequest(String service, Map<String, String> headers, HttpSession session) throws IOException {
        LOGGER.info("issueGetRequest! service:" + service);
        HttpGet httpGet = new HttpGet(service);
        setCommonHeader(httpGet);
        setHeader(httpGet, headers);
        ServiceCallResult result = execute(httpGet, session);
        if(result.getStatusCode() != HttpStatus.OK.value() && !result.getReasonPhase().equalsIgnoreCase("OK")) {
            LOGGER.info("issueGetRequest result:" + result);
        }

        return result;
    }

    public static ServiceCallResult issueGetRequest(String service, Map<String, String> headers, Map<String, String> params, HttpSession session) throws IOException {
        LOGGER.info("issueGetRequest! service:" + service);
        GetRequest getReq = GetRequest.newInstance().setUrl(service).setHeaders(headers).setParams(params);
        ServiceCallResult result = issueGetRequest(getReq);
        if(result.getStatusCode() != HttpStatus.OK.value() && !result.getReasonPhase().equalsIgnoreCase("OK")) {
            LOGGER.info("issueGetRequest result:" + result);
        }

        return result;
    }

    public static ServiceCallResult issueGetRequest(GetRequest req) throws IOException {
        try {
            LOGGER.info("issueGetRequest! URL:" + req.getUrl());
            URIBuilder e = new URIBuilder(req.getUrl());
            if(req.getParams() != null && !req.getParams().isEmpty()) {
                Iterator httpGet = req.getParams().entrySet().iterator();

                while(httpGet.hasNext()) {
                    Entry result = (Entry)httpGet.next();
                    e.addParameter((String)result.getKey(), (String)result.getValue());
                }
            }

            HttpGet httpGet1 = new HttpGet(e.build());
            setCommonHeader(httpGet1);
            setHeader(httpGet1, req.getHeaders());
            ServiceCallResult result1 = execute(httpGet1);
            if(result1.getStatusCode() != HttpStatus.OK.value() && !result1.getReasonPhase().equalsIgnoreCase("OK")) {
                LOGGER.info("issueGetRequest result:" + result1);
            }

            return result1;
        } catch (URISyntaxException var4) {
            throw new IOException("URL syntax error: " + req.getUrl());
        }
    }

    public static ServiceCallResult issueGetRequest(GetRequest req, Object paramsObject) throws IOException {
        try {
            LOGGER.info("issueGetRequest! URL:" + req.getUrl());
            if(null != paramsObject) {
                HashMap e = new HashMap();

                try {
                    BeanInfo httpGet = Introspector.getBeanInfo(paramsObject.getClass());
                    PropertyDescriptor[] result = httpGet.getPropertyDescriptors();
                    PropertyDescriptor[] var5 = result;
                    int var6 = result.length;

                    for(int var7 = 0; var7 < var6; ++var7) {
                        PropertyDescriptor property = var5[var7];
                        String key = property.getName();
                        if(!key.equals("class")) {
                            Method getter = property.getReadMethod();
                            String value = (String)getter.invoke(paramsObject, new Object[0]);
                            e.put(key, value);
                        }
                    }
                } catch (Exception var12) {
                    LOGGER.error("transBean2Map Error ", var12);
                }

                req.setParams(e);
            }

            URIBuilder var14 = new URIBuilder(req.getUrl());
            if(req.getParams() != null && !req.getParams().isEmpty()) {
                Iterator var15 = req.getParams().entrySet().iterator();

                while(var15.hasNext()) {
                    Entry var17 = (Entry)var15.next();
                    var14.addParameter((String)var17.getKey(), (String)var17.getValue());
                }
            }

            HttpGet var16 = new HttpGet(var14.build());
            setCommonHeader(var16);
            setHeader(var16, req.getHeaders());
            ServiceCallResult var18 = execute(var16);
            if(var18.getStatusCode() != HttpStatus.OK.value() && !var18.getReasonPhase().equalsIgnoreCase("OK")) {
                LOGGER.info("issueGetRequest result:" + var18);
            }

            return var18;
        } catch (URISyntaxException var13) {
            throw new IOException("URL syntax error: " + req.getUrl());
        }
    }

    public static ServiceCallResult issuePostRequest(String service, Map<String, String> headers, Object data) throws IOException {
        if(data == null) {
            throw new NullPointerException("Nothing to post");
        } else {
            LOGGER.info("issuePostRequest! service:" + service);
            service.trim().replace(" ", "%20");
            HttpPost httpPost = new HttpPost(service);
            setCommonHeader(httpPost);
            setHeader(httpPost, headers);

            try {
                String result = mapper.writeValueAsString(data);
                httpPost.setEntity(new StringEntity(result, ContentType.APPLICATION_JSON));
            } catch (JsonGenerationException var5) {
                throw new IllegalArgumentException(var5.getMessage(), var5);
            } catch (JsonMappingException var6) {
                throw new IllegalArgumentException(var6.getMessage(), var6);
            }

            ServiceCallResult result1 = execute(httpPost);
            if(result1.getStatusCode() != HttpStatus.OK.value() && !result1.getReasonPhase().equalsIgnoreCase("OK")) {
                LOGGER.info("issuePostRequest result:" + result1);
            }

            return result1;
        }
    }

    public static ServiceCallResult issuePutRequest(String service, Map<String, String> headers, Object data) throws IOException {
        if(data == null) {
            throw new NullPointerException("Nothing to put");
        } else {
            LOGGER.info("issuePutRequest! service:" + service);
            HttpPut httpPut = new HttpPut(service);
            setCommonHeader(httpPut);
            setHeader(httpPut, headers);

            try {
                String result = mapper.writeValueAsString(data);
                httpPut.setEntity(new StringEntity(result, ContentType.APPLICATION_JSON));
            } catch (JsonGenerationException var5) {
                throw new IllegalArgumentException(var5.getMessage(), var5);
            } catch (JsonMappingException var6) {
                throw new IllegalArgumentException(var6.getMessage(), var6);
            }

            ServiceCallResult result1 = execute(httpPut);
            if(result1.getStatusCode() != HttpStatus.OK.value() && !result1.getReasonPhase().equalsIgnoreCase("OK")) {
                LOGGER.info("issuePutRequest result:" + result1);
            }

            return result1;
        }
    }

    public static ServiceCallResult issuePatchRequest(String service, Map<String, String> headers, Object data) throws IOException {
        if(data == null) {
            throw new NullPointerException("Nothing to update");
        } else {
            LOGGER.info("issuePatchRequest! service:" + service);
            HttpPatch httpPatch = new HttpPatch(service);
            setCommonHeader(httpPatch);
            setHeader(httpPatch, headers);

            try {
                String result = mapper.writeValueAsString(data);
                httpPatch.setEntity(new StringEntity(result, ContentType.APPLICATION_JSON));
            } catch (JsonGenerationException var5) {
                throw new IllegalArgumentException(var5.getMessage(), var5);
            } catch (JsonMappingException var6) {
                throw new IllegalArgumentException(var6.getMessage(), var6);
            }

            ServiceCallResult result1 = execute(httpPatch);
            if(result1.getStatusCode() != HttpStatus.OK.value() && !result1.getReasonPhase().equalsIgnoreCase("OK")) {
                LOGGER.info("issuePatchRequest result:" + result1);
            }

            return result1;
        }
    }

    public static ServiceCallResult issuePatchRequest(String service, Map<String, String> headers, Object data, HttpSession session) throws IOException {
        if(data == null) {
            throw new NullPointerException("Nothing to update");
        } else {
            LOGGER.info("issuePatchRequest! service:" + service);
            HttpPatch httpPatch = new HttpPatch(service);
            setCommonHeader(httpPatch);
            setHeader(httpPatch, headers);

            try {
                String result = mapper.writeValueAsString(data);
                httpPatch.setEntity(new StringEntity(result, ContentType.APPLICATION_JSON));
            } catch (JsonGenerationException var6) {
                throw new IllegalArgumentException(var6.getMessage(), var6);
            } catch (JsonMappingException var7) {
                throw new IllegalArgumentException(var7.getMessage(), var7);
            }

            ServiceCallResult result1 = execute(httpPatch, session);
            if(result1.getStatusCode() != HttpStatus.OK.value() && !result1.getReasonPhase().equalsIgnoreCase("OK")) {
                LOGGER.info("issuePatchRequest result:" + result1);
            }

            return result1;
        }
    }

    public static ServiceCallResult issueDeleteRequest(String service, Map<String, String> headers) throws IOException {
        LOGGER.info("issueDeleteRequest! service:" + service);
        HttpDelete httpDelete = new HttpDelete(service);
        setCommonHeader(httpDelete);
        setHeader(httpDelete, headers);
        ServiceCallResult result = execute(httpDelete);
        if(result.getStatusCode() != HttpStatus.OK.value() && !result.getReasonPhase().equalsIgnoreCase("OK")) {
            LOGGER.info("issueDeleteRequest result:" + result);
        }

        return result;
    }

    private static ServiceCallResult execute(HttpRequestBase action) throws IOException {
        RestServiceCallClient client = new RestServiceCallClient();
        ServiceCallResult result = new ServiceCallResult();

        ServiceCallResult var6;
        try {
            HttpResponse e = client.execute(action);
            StatusLine status = e.getStatusLine();
            if(status != null) {
                result.setStatusCode(status.getStatusCode());
                result.setReasonPhase(status.getReasonPhrase());
            }

            String respString = getResponseString(e.getEntity(), getEncodingFromResponse(e));
            result.setResponseString(respString);
            if(result.getStatusCode() != HttpStatus.OK.value() && !result.getReasonPhase().equalsIgnoreCase("OK")) {
                LOGGER.info("http execute result :" + result);
            }

            var6 = result;
        } catch (ClientProtocolException var15) {
            throw new IllegalArgumentException(var15.getMessage(), var15);
        } finally {
            try {
                action.releaseConnection();
                action.abort();
            } catch (Exception var14) {
                LOGGER.warn(var14);
            }

            client = null;
            action = null;
        }

        return var6;
    }

    private static ServiceCallResult executeCheckResponseString(HttpRequestBase action) throws IOException {
        RestServiceCallClient client = new RestServiceCallClient();
        ServiceCallResult result = null;

        ServiceCallResult var5;
        try {
            HttpResponse e = client.execute(action);
            String respString = getResponseString(e.getEntity(), getEncodingFromResponse(e));
            result = (ServiceCallResult) JsonUtils.toBean(respString, ServiceCallResult.class);
            var5 = result;
        } catch (ClientProtocolException var14) {
            throw new IllegalArgumentException(var14.getMessage(), var14);
        } finally {
            try {
                action.releaseConnection();
                action.abort();
            } catch (Exception var13) {
                LOGGER.warn(var13);
            }

            client = null;
            action = null;
        }

        return var5;
    }

    private static ServiceCallResult execute(HttpRequestBase action, HttpSession session) throws IOException {
        RestServiceCallClient client = new RestServiceCallClient();
        ServiceCallResult result = new ServiceCallResult();

        try {
            HttpResponse e = client.execute(action);
            StatusLine status = e.getStatusLine();
            if(status != null) {
                result.setStatusCode(status.getStatusCode());
                result.setReasonPhase(status.getReasonPhrase());
            }

            String respString = getResponseString(e.getEntity(), getEncodingFromResponse(e));
            result.setResponseString(respString);
            Header[] headers = e.getAllHeaders();
            Header[] var8 = headers;
            int e1 = headers.length;

            for(int var10 = 0; var10 < e1; ++var10) {
                Header header = var8[var10];
                if(header.getName().equals("AI-Token")) {
                    UserSecurityModel user = (UserSecurityModel)session.getAttribute("_usm_");
                    user.setToken(header.getValue());
                    session.setAttribute("_usm_", user);
                }
            }

            ServiceCallResult var22 = result;
            return var22;
        } catch (ClientProtocolException var20) {
            throw new IllegalArgumentException(var20.getMessage(), var20);
        } finally {
            try {
                action.releaseConnection();
                action.abort();
            } catch (Exception var19) {
                LOGGER.warn(var19);
            }

            client = null;
            action = null;
        }
    }

    public static void setCommonHeader(HttpRequestBase action) {
        BasicHeader header = new BasicHeader("service-call-token", "4d09a5553e772093a7fea071b54cc510");
        action.setHeader(header);
        header = new BasicHeader("Accept", "application/json");
        action.setHeader(header);
    }

    private static void setHeader(HttpRequestBase action, Map<String, String> headers) {
        if(headers != null && !headers.isEmpty()) {
            Iterator var2 = headers.entrySet().iterator();

            while(var2.hasNext()) {
                Entry pEntry = (Entry)var2.next();
                BasicHeader header = new BasicHeader((String)pEntry.getKey(), String.valueOf(pEntry.getValue()));
                action.setHeader(header);
            }
        }

    }

    public static String getEncodingFromResponse(HttpResponse resp) {
        if(resp.getEntity() == null) {
            return null;
        } else {
            Header contentTypeHeader = resp.getEntity().getContentType();
            String encoding = null;
            if(contentTypeHeader != null) {
                String contentType = contentTypeHeader.getValue();
                encoding = contentType != null && contentType.indexOf("charset=") >= 0?contentType.substring(contentType.indexOf("charset=") + 8):null;
            }

            return encoding;
        }
    }

    public static String getResponseString(HttpEntity resp, String encoding) {
        if(resp == null) {
            return null;
        } else {
            BufferedReader in = null;
            StringBuilder stringBuffer = new StringBuilder(255);
            if(encoding == null || encoding.trim().isEmpty()) {
                encoding = "UTF-8";
            }

            try {
                in = new BufferedReader(new InputStreamReader(resp.getContent(), encoding));
                String e = null;

                for(boolean isFirstLine = true; (e = in.readLine()) != null; stringBuffer.append(e)) {
                    if(!isFirstLine) {
                        stringBuffer.append("\n");
                    } else {
                        isFirstLine = false;
                    }
                }

                String var6 = stringBuffer.toString();
                return var6;
            } catch (Exception var15) {
                throw new RuntimeException(var15);
            } finally {
                if(in != null) {
                    try {
                        in.close();
                    } catch (IOException var14) {
                        LOGGER.warn(var14);
                        in = null;
                    }
                }

            }
        }
    }

    public static String getClientAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static String getClientAddressToken(HttpServletRequest request) {
        String ip = getClientAddress(request);
        if(ip != null && !ip.trim().isEmpty()) {
            String[] ipArray = ip.split("\\,");
            return ipArray[0].trim();
        } else {
            return "NA";
        }
    }

    public static boolean isRequestFromInternalServiceCall(HttpServletRequest request) {
        String pubKey = request.getHeader("service-call-token");
        return pubKey == null?false:"4d09a5553e772093a7fea071b54cc510".equals(pubKey.trim());
    }

    public static boolean isRequestFromMobileServiceCall(HttpServletRequest request) {
        String pubKey = request.getHeader("service-call-token-mob");
        return pubKey == null?false:"m4d09a5553e772093oa7fea071b54cc510b".equals(pubKey.trim());
    }

    public static ServiceCallResult issueMultipartRequest(String service, Map<String, String> headers, Map<String, String> dataMap, SimpleFileObject... files) throws IOException {
        if(dataMap == null && files == null) {
            throw new NullPointerException("! issueMultipartRequest:: Nothing to post");
        } else {
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            if(dataMap != null) {
                Iterator httpPost = dataMap.entrySet().iterator();

                while(httpPost.hasNext()) {
                    Entry entry = (Entry)httpPost.next();
                    reqEntity.addPart((String)entry.getKey(), new StringBody((String)entry.getValue(), Consts.UTF_8));
                }
            }

            if(files != null) {
                SimpleFileObject[] var9 = files;
                int var11 = files.length;

                for(int var7 = 0; var7 < var11; ++var7) {
                    SimpleFileObject file = var9[var7];
                    reqEntity.addPart(file.getFilename(), file);
                }
            }

            HttpPost var10 = new HttpPost(service);
            setCommonHeader(var10);
            setHeader(var10, headers);
            var10.setEntity(reqEntity);
            return execute(var10);
        }
    }

    public static ServiceCallResult issueMultipartRequest(String service, Map<String, String> headers, Map<String, String> dataMap, InputStreamFileObject... files) throws IOException {
        if(dataMap == null && files == null) {
            throw new NullPointerException("! issueMultipartRequest:: Nothing to post");
        } else {
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            if(dataMap != null) {
                Iterator httpPost = dataMap.entrySet().iterator();

                while(httpPost.hasNext()) {
                    Entry entry = (Entry)httpPost.next();
                    reqEntity.addPart((String)entry.getKey(), new StringBody((String)entry.getValue(), Consts.UTF_8));
                }
            }

            if(files != null) {
                InputStreamFileObject[] var9 = files;
                int var11 = files.length;

                for(int var7 = 0; var7 < var11; ++var7) {
                    InputStreamFileObject file = var9[var7];
                    reqEntity.addPart(file.getFilename(), file);
                }
            }

            HttpPost var10 = new HttpPost(service);
            setCommonHeader(var10);
            setHeader(var10, headers);
            var10.setEntity(reqEntity);
            return execute(var10);
        }
    }

    public static boolean validateServiceCallToken(String serviceCallToken) {
        return "4d09a5553e772093a7fea071b54cc510".equals(serviceCallToken);
    }

    public static boolean validatePublicAPICallToken(HttpServletRequest request) {
        if(request.getRemoteAddr().equals(request.getLocalAddr())) {
            return true;
        } else {
            String pubKey = request.getHeader("ai-api-access-token");
            if(pubKey == null) {
                LOGGER.error("No AI public api access token found.");
                return false;
            } else if(!Http.PUBLIC_API_ACCESS_TOKENS.containsKey(pubKey)) {
                LOGGER.error("AI public api access token found but not correct." + pubKey);
                return false;
            } else {
                return true;
            }
        }
    }

    public static boolean validateRefreshTokenKey(HttpServletRequest request) {
        if(request.getRemoteAddr().equals(request.getLocalAddr())) {
            return true;
        } else {
            String pubKey = request.getHeader("ai-api-refresh-key");
            if(pubKey == null) {
                LOGGER.error("No refresh token header found.");
                return false;
            } else if(!Http.PUBLIC_API_ACCESS_TOKENS.containsValue(pubKey)) {
                LOGGER.error("Refresh token doesn\'t match: " + pubKey);
                return false;
            } else {
                return true;
            }
        }
    }

    public static String getPublicAPICallToken(HttpServletRequest request) {
        return request.getHeader("ai-api-access-token");
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
