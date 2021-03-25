//package com.fank243.cloud.auth.shiro.filter;
//
//import com.fank243.cloud.component.common.utils.WebUtils;
//import com.fank243.cloud.component.tool.utils.ResultInfo;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
///**
// * 未登录拦截，Shiro拦截到未登录请求时，会301重定向到/login页面，因此需要将其修改为返回JSON未登录提示
// *
// * @author FanWeiJie
// * @date 2021-03-21 23:08:10
// */
//public class UnAuthenticationFilter extends FormAuthenticationFilter {
//
//    /**
//     * 该方法从字面意思理解，即是否允许放行请求，继续进入后面的拦截器处理
//     *
//     * 因此这里面只需要判断用户是否已经登录认证了，如果认证了则放行，否则不放行
//     *
//     * 如果该方法返回false，则会进入到{@link UnAuthenticationFilter#onAccessDenied(ServletRequest, ServletResponse)} 方法中
//     *
//     * 当用户未登录的时候，则需要在onAccessDenied()方法中返回未登录的JSON串
//     *
//     */
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        Subject subject = getSubject(request, response);
//        // 已经登录认证通过了，则放行请求
//        if (subject != null && subject.isAuthenticated()) {
//            return Boolean.TRUE;
//        }
//        // 否则不放行请求
//        return Boolean.FALSE;
//
//    }
//
//    /**
//     * 该方法只会在用户未登录认证的时候进入，因此直接返回未登录的提示即可
//     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
//        // 返回未登录的提示
//        WebUtils.printJson(ResultInfo.err201());
//
//        return Boolean.FALSE;
//    }
//}
