package com.fank243.cloud.auth.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.fank243.cloud.auth.JWTShiroUser;
import com.fank243.cloud.auth.configuration.ShiroConfiguration;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

/**
 * Shiro Tool
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:44:45
 */
public class ShiroUtils {

//    private static final RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

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

    /**
     * 获取当前登录用户信息
     * 
     * @return JWTShiroUser
     */
    public static JWTShiroUser getUserInfo() {
        return (JWTShiroUser)SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 删除用户缓存信息
     * @param  username  用户名称
     * @param  isRemoveSession 是否删除Session，删除后用户需重新登录
     */
//    public static void deleteCache(String username, boolean isRemoveSession){
//        //从缓存中获取Session
//        Session session = null;
//        // 获取当前已登录的用户session列表
//        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
//
//        JWTShiroUser shiroUser;
//        Object attribute = null;
//        // 遍历Session,找到该用户名称对应的Session
//        for(Session sessionInfo : sessions){
//            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
//            if (attribute == null) {
//                continue;
//            }
//            shiroUser = (JWTShiroUser) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
//            if (shiroUser == null) {
//                continue;
//            }
//
//            if (Objects.equals(shiroUser.getUsername(), username)) {
//                session=sessionInfo;
//                // 清除该用户以前登录时保存的session，强制退出  -> 单用户登录处理
//                if (isRemoveSession) {
//                    redisSessionDAO.delete(session);
//                }
//            }
//        }
//
//        if (session == null||attribute == null) {
//            return;
//        }
//
//        //删除session
//        if (isRemoveSession) {
//            redisSessionDAO.delete(session);
//        }
//
//        //删除Cache，再访问受限接口时会重新授权
//        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
//        Authenticator authc = securityManager.getAuthenticator();
//        ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
//    }

}
