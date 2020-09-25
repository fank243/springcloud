package com.fank243.cloud.auth.oauth2.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加解密工具类
 * 
 * @author FanWeiJie
 * @date 2020-09-21 11:04:42
 */
public class EncryptUtils {
    /**
     * bCrype 加密
     * 
     * @param password 明文
     * @return 密文
     */
    public static String bCryptEncoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
