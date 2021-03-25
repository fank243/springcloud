package com.fank243.cloud.auth.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 角色拦截器
 * 
 * @author FanWeiJie
 * @date 2021-03-25 22:15:32
 */
public class RoleShiroFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[])mappedValue;
        // 没有角色限制，有权限访问
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (String s : rolesArray) {
            // 若当前用户是rolesArray中的任何一个，则有权限访问
            if (subject.hasRole(s)) {
                return true;
            }
        }
        return false;
    }
}
