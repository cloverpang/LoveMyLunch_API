package com.lovemylunch.common.cache;

import com.alibaba.fastjson.JSON;
import com.lovemylunch.common.util.JsonUtils;
import com.lovemylunch.common.util.RedisUtil;
import com.lovemylunch.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Component("redisCache")
public class RedisCache<T> implements ICache<T> {
    private static final Logger LOGGER = Logger.getLogger(RedisCache.class);

    @Override
    public void set(String key, T value, int expireTime) {
        try{
            RedisUtil.set(key, JsonUtils.toJson(value), expireTime);
        }catch (Exception e){
            LOGGER.error("set the cache into redis exception : " + e.getMessage());
        }
    }

    @Override
    public boolean clear() {
        return false;
    }

    @Override
    public T get(String key) {
        return null;
    }

    @Override
    public T get(String key, Class<T> clazz) {
        try{
            String value = RedisUtil.get(key);
            if(StringUtils.isNotEmpty(value)){
                return JsonUtils.toBean(value,clazz);
            }else{
                return null;
            }

        }catch (Exception e){
            LOGGER.error("get the cache from redis exception : " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean remove(String key) {
        try{
            long val = RedisUtil.del(key);
            if(val>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            LOGGER.error("remove the cache from redis exception : " + e.getMessage());
            return false;
        }
    }
}
