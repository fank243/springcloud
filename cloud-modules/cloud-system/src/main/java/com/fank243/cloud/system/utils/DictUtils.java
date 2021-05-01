package com.fank243.cloud.system.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.fank243.cloud.common.core.constant.Constants;
import com.fank243.cloud.common.core.utils.SpringUtils;
import com.fank243.cloud.common.core.utils.StringUtils;
import com.fank243.cloud.common.redis.service.RedisService;
import com.fank243.cloud.system.domain.SysDictData;

import java.util.Collection;
import java.util.List;

/**
 * 字典工具类
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public class DictUtils {

    /**
     * 设置字典缓存
     * 
     * @param key 参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas) {
        SpringUtils.getBean(RedisService.class).setCacheObject(getCacheKey(key), dictDatas);
    }

    /**
     * 获取字典缓存
     * 
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<SysDictData> getDictCache(String key) {
        Object cacheObj = SpringUtils.getBean(RedisService.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj)) {
            return StringUtils.cast(cacheObj);
        }
        return null;
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache() {
        Collection<String> keys = SpringUtils.getBean(RedisService.class).keys(Constants.SYS_DICT_KEY + "*");
        SpringUtil.getBean(RedisService.class).deleteObject(keys);
    }

    /**
     * 设置cache key
     * 
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return Constants.SYS_DICT_KEY + configKey;
    }
}
