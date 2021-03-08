package com.fank243.cloud.gateway.core.constants;

/**
 * Spring Security相关配置常量
 * 
 * @author FanWeiJie
 * @date 2020-09-18 17:38:35
 */
public class SecurityConstants {

    private SecurityConstants() {}

    /** 登录的地址 */
    public static final String AUTH_LOGIN_URL = "/auth/login";

    /** rememberMe 为 false 的时候过期时间是1个小时 */
    public static final long EXPIRATION = 60 * 60L;
    /** rememberMe 为 true 的时候过期时间是7天 */
    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    /** 权限前缀 **/
    public static final String AUTHORITY_PREFIX = "perm:";
    /** 权限声明名称 **/
    public static final String AUTHORITIES_CLAIM_NAME = "authorities";

}
