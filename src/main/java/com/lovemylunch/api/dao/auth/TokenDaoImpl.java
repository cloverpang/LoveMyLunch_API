package com.lovemylunch.api.dao.auth;

import com.lovemylunch.common.util.*;
import com.lovemylunch.common.beans.system.TokenSession;
import com.lovemylunch.common.exceptions.AwfException;
import com.alibaba.fastjson.JSON;
import com.lovemylunch.api.dao.mybatis.mapper.AccessTokenMapper;
import com.lovemylunch.common.beans.system.AccessTokenDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
public class TokenDaoImpl {

    private static final Logger logger = LoggerFactory.getLogger(TokenDaoImpl.class);

    private static final String ISSUER_NAME = "http://asiainspection.com";
    private static final Integer TOKEN_EXPIRATION_TIME = 120;
    private static final String TOKEN_SUBJECT = "AI API token";

    private final String seperator = "~~~";
    private Map<String, Key> keys = new HashMap<String, Key>();
    private String TOKENKEY = "publicAPIToken";

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    //@CachePut(value = "publicAPIToken",key = "#sessionId")//create or update-----function will run every called
    public TokenSession generateToken(final String login, final String userId, String sessionId, final String userType,final String operationCenter){
        TokenSession tokenSession = new TokenSession();
        try {
            if (sessionId.isEmpty()) {
                tokenSession.setId(IDGenerator.uuid());
            } else {
                tokenSession.setId(sessionId);
            }
            tokenSession.setUserId(userId);

            Date currentTime = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentTime);
            cal.add(Calendar.MINUTE,TOKEN_EXPIRATION_TIME);
            Date exprieTime = cal.getTime();
            String exprieTimeStr = DateUtils.date2String(exprieTime, "yyyy-MM-dd HH:mm:ss");
            System.out.println("exprieTime is : " + exprieTimeStr);
            tokenSession.setValidBefore(exprieTimeStr);

            String tokenValueStr = generateValidateCode() + "|" + login + "|" + userId + "|" + sessionId + "|" +
                    userType + "|" + exprieTimeStr + "|" + operationCenter;

            tokenSession.setToken(tokenValueStr);

//            logger.info("finished tokenSession generation. ");
            String tokenStr = null;
            try{
                tokenStr = JSON.toJSONString(tokenSession);
                logger.info("success!! tokenSession ---->> String : "+tokenStr);
            }catch (Exception e){
                logger.error("error!! tokenSession can not be cast to String .");
            }
            if (StringUtils.isNotBlank(tokenStr)) {
//                logger.info("saving tokenSession to Redis for 1 day ...");
//                RedisUtil.hset(TOKENKEY, sessionId, tokenStr, RedisUtil.HOUR * 24);
//                logger.info("success!  saved!!!");
                this.saveTokenInRedisOrDatabase(sessionId, tokenValueStr,tokenSession);
            }
        }catch (Exception e){
            logger.error("error generateToken",e);
            tokenSession = null;
        }
        return tokenSession;
    }

    public boolean removePublicAPIToken(String token,String sessionId) {
        try{
            boolean removeTokenInRedis = this.removeTokenInRedis(sessionId);
            if(!removeTokenInRedis){
                this.removeTokenInDatabase(token);
            }
            return true;
        }catch (Exception e){
            logger.error("remove token failed!!!");
            return false;
        }
    }

    private boolean removeTokenInRedis(String sessionId){
        try{
            logger.info("removing tokenSession in redis, sessionId:" +sessionId);

            Long count = RedisUtil.hdel(TOKENKEY, sessionId);
            if (count != null && count.intValue() ==1) {
                logger.info("successful removed tokenSession in redis, sessionId[" + sessionId + "]");
                return true;
            }else {
                logger.info("failed to remove tokenSession in redis, sessionId[" + sessionId + "]");
                return false;
            }
        }catch (Exception e){
            logger.error("redis have issue!!!");
            logger.error("failed to remove tokenSession in redis, sessionId[" + sessionId + "]");
            return false;
        }
    }

    private void removeTokenInDatabase(String token){
        try{
            logger.info("removing tokenSession in database, token:" +token);

            AccessTokenDO accessTokenDO = accessTokenMapper.getByToken(token);
            if (null != accessTokenDO) {
                accessTokenMapper.removeByToken(token);
                logger.info("successful removed tokenSession in database, token[" + token + "]");
            }
        }catch (Exception e){
            logger.info("redis have issue!!!");
            logger.info("failed to remove tokenSession in database, token[" + token + "]");
        }
    }

    public TokenSession getTokenSessionFromRedis(String sessionId){
//        logger.info("sessionId is : " + sessionId);
        String resultStr = RedisUtil.hget(TOKENKEY, sessionId);
//        logger.info("token resultStr is : " + resultStr);
        if (StringUtils.isBlank(resultStr)) {
            logger.error("can't find session token in redis: " + sessionId);
            return null;
        } else {
            return JSON.parseObject(resultStr).toJavaObject(TokenSession.class);
        }
    }

    public TokenSession getTokenFromRedis(String sessionId){
        try{
           return this.getTokenSessionFromRedis(sessionId);
        }catch (Exception e){
            logger.error("get session token in redis field, redis issue!!!" + e.getMessage());
            return null;
        }
    }

    public String getTokenFromRedisOrDatabase(String token,String sessionId){
        try{
            TokenSession tokenSession = this.getTokenFromRedis(sessionId);
            if(null != tokenSession){
                return tokenSession.getToken();
            }else{
                AccessTokenDO accessTokenDO = accessTokenMapper.getByToken(token);
                if(null != accessTokenDO){
                    return accessTokenDO.getToken();
                }else {
                    return "";
                }
            }
        }catch (Exception e){
            logger.error("get token field, redis issue and database all field!!!");
            return "";
        }
    }

    public void removeDatabaseExpiryTokens(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);

        Date expiryTime = calendar.getTime();
        String expiry = DateUtils.date2String(expiryTime, "yyyy-MM-dd HH:mm:ss");
        System.out.println("removeDatabaseExpiryTokens , expiry time is : " + expiry);
        accessTokenMapper.removeByExpiry(expiry);
    }

    public boolean checkIfExpired(final String exprieTimeStr) {
        try {
            Date currentTime = DateUtils.now();
            Date exprieTime = DateUtils.toDate(exprieTimeStr, "yyyy-MM-dd HH:mm:ss");
            if (exprieTime.before(currentTime)) {
//                logger.info("Token expired now.");
                return false;
            } else {
//	            logger.info("Token still alive. ");
                return true;
            }
        }catch (Exception e){
            logger.error("check token if alive get error!", e);
        }
        return false;
    }

    // generate a 6 random as the supplier validate code
    private String generateValidateCode() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

    private void saveTokenInRedisOrDatabase(String sessionId,String tokenStr,TokenSession tokenSession){
        try{
            boolean saveTokenToRedis = this.saveTokenInRedis(sessionId, tokenSession);
            if(!saveTokenToRedis){
                this.saveOrUpdateTokenInDatabase(sessionId, tokenStr);
            }
        }catch (Exception e){
            logger.error("save token field!!!");
        }
    }

    private boolean saveTokenInRedis(String sessionId,TokenSession tokenSession){
        logger.info("saving tokenSession to Redis for 1 day ...");

        try{
            Long i = RedisUtil.hset(TOKENKEY, sessionId, JsonUtils.toJson(tokenSession), RedisUtil.HOUR * 24);
            if(null == i){
                logger.error("save token to redis field!!!");
                return  false;
            }else{
                logger.info("success save token to redis!  saved!!!");
                return true;
            }
        }catch (Exception e) {
            logger.error("save tokenSession into redis failed, exception:" + e.getMessage());
            return false;
        }
    }

    private boolean saveOrUpdateTokenInDatabase(String sessionId,String tokenStr) throws AwfException{
        try{
            logger.info("saving tokenSession to database...");
            String[] tokenArr = tokenStr.split("\\|");

            final String tokenUserLogin = tokenArr[1];
            final String tokenUserId = tokenArr[2];
            final String tokenSessionId = tokenArr[3];
            final String userType = tokenArr[4];
            final String exprieTimeStr = tokenArr[5];

            AccessTokenDO accessTokenDO = accessTokenMapper.getByUserId(tokenUserId);
            if(accessTokenDO != null){
                accessTokenDO.setExpiry(DateUtils.toDate(exprieTimeStr, "yyyy-MM-dd HH:mm:ss"));
                accessTokenDO.setToken(tokenStr);
                accessTokenDO.setModified(new Date());
                accessTokenMapper.refreshByToken(accessTokenDO);

                logger.info("success update token to database!  saved!!!");
            }else{
                accessTokenDO = new AccessTokenDO();
                accessTokenDO.setCreated(new Date());
                accessTokenDO.setExpiry(DateUtils.toDate(exprieTimeStr, "yyyy-MM-dd HH:mm:ss"));
                accessTokenDO.setToken(tokenStr);
                accessTokenDO.setUser_id(tokenUserId);
                accessTokenDO.setModified(new Date());

                accessTokenMapper.saveToken(accessTokenDO);

                logger.info("success save token to database!  saved!!!");
            }
            return true;
        }catch (Exception e){
            logger.error("save token to database field!!!" + e.getMessage());
            throw new AwfException("save token to database field!!!");
        }
    }
}
