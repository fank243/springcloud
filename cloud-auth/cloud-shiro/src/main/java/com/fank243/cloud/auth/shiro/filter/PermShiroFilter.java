package com.fank243.cloud.auth.shiro.filter;

import com.fank243.cloud.auth.shiro.utils.ShiroUtils;
import com.fank243.cloud.component.common.utils.WebUtils;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限拦截器
 * 
 * @author FanWeiJie
 * @date 2021-03-25 22:15:32
 */
@Slf4j
public class PermShiroFilter extends PermissionsAuthorizationFilter {

    /****
     * 当登录的主体没有权限时进入此方法
     * 
     * @param request ServletRequest
     * @param response ServletResponse
     * @param mappedValue 权限列表
     * @return 是否放心请求
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
        throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        if (log.isDebugEnabled()) {
            log.debug("用户[{}]访问资源[{}]受限...", ShiroUtils.getUserInfo().getUsername(), httpRequest.getRequestURI());
        }

        // 检查是否拥有访问权限
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            this.saveRequestAndRedirectToLogin(request, response);
        }

        WebUtils.printJson(response, ResultInfo.err403());
        return false;
    }
}
