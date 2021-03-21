//package com.fank243.cloud.auth.shiro.filter;
//
//import com.fank243.cloud.component.common.utils.WebUtils;
//import com.fank243.cloud.component.tool.utils.ResultInfo;
//import org.apache.shiro.web.filter.authc.LogoutFilter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
///**
// * 自定义登出拦截器，shiro 默认退出的时候会301重定向，此处改造成返回JSON
// *
// * @author FanWeiJie
// * @date 2021-03-21 20:43:26
// */
//public class MyLogoutFilter extends LogoutFilter {
//
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        WebUtils.printJson(ResultInfo.ok().message("退出登录成功"));
//        return true;
//    }
//}
