package com.fank243.cloud.service.core.aspectj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fank243.cloud.component.common.annotation.Log;
import com.fank243.cloud.component.common.utils.ResultInfo;
import com.fank243.cloud.component.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 业务日志
 *
 * @author FanWeiJie
 * @date 2020-04-11 21:36:10
 */
@Slf4j
@Aspect
@Component
// @Order(2)
public class SysLogAspectj {

    @Pointcut("@annotation(com.fank243.cloud.component.common.annotation.Log)")
    public void point() {}

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        try {
            Signature signature = joinPoint.getSignature();
            if (!(signature instanceof MethodSignature)) {
                return joinPoint.proceed();
            }
            MethodSignature methodSignature = (MethodSignature)signature;
            Log annotation = methodSignature.getMethod().getAnnotation(Log.class);
            if (annotation == null) {
                return joinPoint.proceed();
            }

            proceed = joinPoint.proceed();
            if (!(proceed instanceof ResultInfo)) {
                return proceed;
            }
            ResultInfo result = (ResultInfo)proceed;

            // 获取目标类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取方法名
            String methodName = joinPoint.getSignature().getName();

            ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                return proceed;
            }
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse();

            Map<String, String> header = WebUtils.getHeader(request);
            String headers = JSON.toJSONString(header);

            String body;
            if (RequestMethod.POST.name().equals(request.getMethod())) {
                Object[] paramsArray = joinPoint.getArgs();
                body = argsArrayToString(paramsArray);
            } else {
                Map<?, ?> paramsMap = (Map<?, ?>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                body = JSONObject.toJSONString(paramsMap);
            }

            // 添加记录
            result = addRecord(annotation, result, request, response, className, methodName, headers, body);
            if (!result.isSuccess()) {
                log.warn(result.toString());
            }
        } catch (Exception e) {
            log.error(e.toString());
            throw e;
        }
        return proceed;
    }

    private ResultInfo addRecord(Log annotation, ResultInfo result, HttpServletRequest request,
        HttpServletResponse response, String className, String methodName, String headers, String body) {
        // SysLogDO sysLog = new SysLogDO();
        //
        // sysLog.setAdminId(adminId);
        // sysLog.setLogLevel(annotation.logLevel());
        // sysLog.setLogType(annotation.logType());
        // sysLog.setLogDesc(annotation.desc());
        //
        // sysLog.setClassName(className);
        // sysLog.setMethodName(methodName);
        //
        // sysLog.setRequestIp(WebUtils.getIp(request));
        // sysLog.setRequestUri(request.getRequestURI());
        // sysLog.setRequestMethod(request.getMethod());
        // sysLog.setSessionId(request.getSession().getId());
        // if (ThreadUtils.get() != null) {
        // sysLog.setRequestId(ThreadUtils.get().getRequestId());
        // } else {
        // sysLog.setRequestId("");
        // }
        // sysLog.setRequestHeader(headers);
        // sysLog.setRequestBody(body);
        // sysLog.setResponseStatus(response.getStatus());
        // sysLog.setResponseBody(result.toString());
        //
        // sysLog.setResultCode(result.getCode());
        //
        // return sysLogService.addRecord(sysLog);
        return ResultInfo.ok();
    }

    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object param : paramsArray) {
                Object obj = JSON.toJSON(param);
                if (obj instanceof JSON) {
                    JSONObject json = (JSONObject)obj;
                    for (Map.Entry<String, Object> entry : json.entrySet()) {
                        String key = entry.getKey();
                        if (key.contains("password") || key.contains("pwd")) {
                            json.put(key, "******");
                        }
                    }
                }
                params.append(obj.toString()).append(" ");
            }
        }
        return params.toString().trim();
    }
}
