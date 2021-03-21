package com.fank243.cloud.auth.shiro.filter;

import com.fank243.cloud.component.common.utils.WebUtils;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 未授权拦截
 * 
 * @author FanWeiJie
 * @date 2021-03-21 23:08:10
 */
public class UnAuthorizationFilter extends PermissionsAuthorizationFilter {

    /**
     * 该方法用来校验用是否具有对应的权限
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.printJson(ResultInfo.err403());
        return false;
    }
}
