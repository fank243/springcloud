package com.fank243.cloud.auth.shiro.service;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.Map;

/**
 * 动态获取并刷新权限
 *
 * @author FanWeiJie
 * @date 2021-03-24 20:46:44
 */
public interface ShiroService {

    /**
     * 初始化权限 -> 拿全部权限
     * 
     * @return 权限集合
     */
    Map<String, String> loadFilterChainDefinitionMap();

    /**
     * 在对uri权限进行增删改操作时，需要调用此方法进行动态刷新加载数据库中的uri权限
     *
     * @param roleId 角色ID
     * @param isRemoveSession 是否移除Session
     */
    void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, String roleId, Boolean isRemoveSession)
        throws Exception;

    /**
     * shiro动态权限加载 -> 原理：删除shiro缓存，重新执行doGetAuthorizationInfo方法授权角色和权限
     *
     * @param roleId 角色ID
     * @param isRemoveSession 是否移除Session
     */
    void updatePermissionByRoleId(String roleId, Boolean isRemoveSession);
}
