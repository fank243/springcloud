package com.fank243.cloud.gateway.service;

import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.common.core.exception.CaptchaException;

import java.io.IOException;
import java.util.Map;

/**
 * 验证码处理
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     * 
     * @return 验证码
     * @throws IOException IOException
     * @throws CaptchaException CaptchaException
     */
    ResultInfo<Map<String, Object>> createCapcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     * 
     * @param key 验证码KEY
     * @param value 验证码
     * @throws CaptchaException 校验验证码
     */
    void checkCapcha(String key, String value) throws CaptchaException;
}
