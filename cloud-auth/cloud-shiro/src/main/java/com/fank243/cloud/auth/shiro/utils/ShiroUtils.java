package com.fank243.cloud.auth.shiro.utils;

import com.fank243.cloud.auth.shiro.configuration.ShiroConfiguration;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

/**
 * Shiro Tool
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:44:45
 */
public class ShiroUtils {

    /** 迭代次数 **/
    public static final int HASH_ITERATIONS = 1024;

    /**
     * Shiro 密码加密算法 - MD5
     *
     * 此处的{@code HASH_ITERATIONS} 务必与 {@link ShiroConfiguration#hashedCredentialsMatcher()}保持一致
     *
     * @param password 明文密码
     * @param salt 盐
     * @return 密文密码
     */
    public static String md5Hash(String password, String salt) {
        return new SimpleHash(Md5Hash.ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toHex();
    }

    /** 获取Subject **/
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static void main(String[] args) {
        System.out.println(md5Hash("123456","jack"));
    }
}
