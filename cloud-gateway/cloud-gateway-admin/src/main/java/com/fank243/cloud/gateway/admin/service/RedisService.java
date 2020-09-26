package com.fank243.cloud.gateway.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * cache tool
 * 
 * @author FanWeiJie
 * @date 2020-09-12 21:45:13
 */
@Slf4j
@Component
public class RedisService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /** Set redis Key expire time */
    private void setExpireTime(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /** Set Key **/
    public boolean set(String key, String value) {
        return set(key, value, -1);
    }

    /** Set Key **/
    public boolean set(String key, String value, long expireTime) {
        try {
            if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
                if (log.isWarnEnabled()) {
                    log.warn("the redis key or value must be not null or empty:{},{}", key, value);
                }
                return false;
            }
            // set key
            redisTemplate.opsForValue().set(key, value);
            // set expire time
            if (expireTime > 0) {
                setExpireTime(key, expireTime);
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("redis set key error:{}", e.toString());
            }
            return false;
        }
        return true;
    }

    /** Get Key **/
    public Object get(String key) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        return redisTemplate.opsForValue().get(key);
    }

    /** Put Hash Key **/
    public boolean hashPut(String key, String hashKey, Object value) {
        return hashPut(key, hashKey, value, -1);
    }

    /** Put Hash Key **/
    public boolean hashPut(String key, String hashKey, Object value, long expireTime) {
        try {
            if (StringUtils.isBlank(key) || value == null) {
                if (log.isWarnEnabled()) {
                    log.warn("the redis hash key or hashKey or value must be not null or empty:{},{},{}", key, hashKey,
                        value);
                }
                return false;
            }
            // put hash key
            redisTemplate.opsForHash().put(key, hashKey, value);
            // set expire time
            if (expireTime > 0) {
                setExpireTime(key, expireTime);
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("redis set hash key error:{}", e.toString());
            }
            return false;
        }

        return true;
    }

    /** Get Hash Key **/
    public Object hashGet(String key, String hashKey) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /** delete single key **/
    public boolean delete(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        Boolean isDelete = redisTemplate.delete(key);
        return isDelete != null && isDelete;
    }

    /** delete multiple keys **/
    public boolean delete(Collection<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return false;
        }
        Long rows = redisTemplate.delete(keys);
        return rows != null && rows > 0;
    }

    /** remove single hash key **/
    public boolean remove(String key, String hashKey) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(hashKey)) {
            return false;
        }
        return redisTemplate.opsForHash().delete(key, hashKey) > 0;
    }

    /** remove multiple hash key **/
    public boolean remove(String key, Object... hashKeys) {
        if (StringUtils.isBlank(key) || hashKeys == null || hashKeys.length <= 0) {
            return false;
        }
        return redisTemplate.opsForHash().delete(key, hashKeys) > 0;
    }

    /** redis has this key **/
    public boolean hasKey(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        Boolean hasKey = redisTemplate.hasKey(key);
        return hasKey != null && hasKey;
    }

}
