package com.fank243.springcloud.service.core.interceptor;

import com.fank243.springcloud.common.annotation.Interceptor;
import com.fank243.springcloud.common.annotation.RepeatSubmit;
import com.fank243.springcloud.common.utils.ResultInfo;
import com.fank243.springcloud.common.utils.WebUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * repeat submit interceptor
 * 
 * @author FanWeiJie
 * @date 2020-09-15 11:24:30
 */
@Interceptor(include = "/**", order = 1)
public abstract class RepeatSubmitInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request)) {
                    WebUtils.printJson(response, ResultInfo.fail("不允许重复提交，请稍后再试"));
                    return false;
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     * 
     * @param request HttpServletRequest
     * @return repeat submit result
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request) throws Exception;
}
