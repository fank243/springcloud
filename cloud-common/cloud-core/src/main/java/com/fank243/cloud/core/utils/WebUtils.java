package com.fank243.cloud.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author FanWeiJie
 * @date 2018/8/1 12:08
 **/
public final class WebUtils {

    private static final String UNKOWN = "unknown";

    /**
     * 获取Referer
     *
     * @return Referer
     */
    public static String getReferer() {
        return getReferer(getRequest());
    }

    /**
     * 获取Referer
     *
     * @return Referer
     */
    public static String getReferer(HttpServletRequest request) {
        return request.getHeader("referer");
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes =
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return null;
        }
        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes =
            (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getResponse();
    }

    public static String getContextPath() {
        return getRequest().getContextPath();
    }

    public static String getContextPath(HttpServletRequest request) {
        return request.getContextPath();
    }

    /**
     * 获取站点Baseurl
     *
     * @return BaseUrl
     */
    public static String getBaseUrl() {
        return getBaseUrl(getRequest());
    }

    /**
     * 获取站点Baseurl
     *
     * @param request HttpServletRequest
     * @return BaseUrl
     */
    public static String getBaseUrl(HttpServletRequest request) {
        int port = request.getServerPort();
        if (request.getServerPort() == 80 || request.getServerPort() == 443) {
            return getScheme(request) + "://" + request.getServerName();
        }
        return getScheme(request) + "://" + request.getServerName() + ":" + port;
    }

    /**
     * 获取URL Scheme
     *
     * Nginx 需要在location位置添加header参数
     *
     * proxy_set_header X-Forwarded-Proto $scheme;
     *
     * 或
     *
     * proxy_set_header X-Forwarded-Scheme $scheme;
     *
     * @param request HttpServletRequest
     * @return Scheme
     */
    public static String getScheme(HttpServletRequest request) {
        String scheme = request.getHeader("x-forwarded-proto");
        if (StringUtils.isBlank(scheme)) {
            scheme = request.getHeader("x-forwarded-scheme");
        }

        if (StringUtils.isBlank(scheme)) {
            scheme = request.getScheme();
        }

        return scheme;
    }

    /**
     * 是否ajax请求
     *
     * @return {@code true：是，false：否}
     */
    public static boolean isAjax() {
        return isAjax(getRequest());
    }

    /**
     * 是否ajax请求
     *
     * @param request HttpServletRequest
     * @return {@code true：是，false：否}
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }

    /**
     * 是否ajax请求
     *
     * @param xRequestedWith xRequestedWith
     * @return {@code true：是，false：否}
     */
    public static boolean isAjax(String xRequestedWith) {
        return "XMLHttpRequest".equals(xRequestedWith);
    }

    /**
     * 是否浏览器请求
     *
     * @return {@code true：是，false：否}
     */
    public static boolean isBrowser() {
        return isAjax(getRequest());
    }

    /**
     * 是否浏览器请求
     *
     * @param request HttpServletRequest
     * @return {@code true：是，false：否}
     */
    public static boolean isBrowser(HttpServletRequest request) {
        String header = request.getHeader("accept");

        return header.startsWith("text/html");
    }

    /**
     * 向浏览器输出字符串
     *
     * @param string 字符串
     */
    public static void printStr(String string) {
        HttpServletResponse response = getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向浏览器输出字符串
     *
     * @param string 字符串
     */
    public static void printStr2(String string) {
        HttpServletResponse response = getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            response.getOutputStream().write(string.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向浏览器输出JSON
     *
     * @param obj Object
     */
    public static void printJson(ServletResponse response, Object obj) {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            String str;
            if (obj instanceof String) {
                str = String.valueOf(obj);
            } else {
                str = JSON.toJSONString(obj);
            }
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向浏览器输出JSON
     *
     * @param obj Object
     */
    public static void printJson(Object obj) {
        HttpServletResponse response = getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            String str;
            if (obj instanceof String) {
                str = String.valueOf(obj);
            } else {
                str = JSON.toJSONString(obj);
            }
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向浏览器输出JSON
     *
     * @param obj Object
     */
    public static void printJson2(Object obj) {
        HttpServletResponse response = getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            String str;
            if (obj instanceof String) {
                str = String.valueOf(obj);
            } else {
                str = JSON.toJSONString(obj);
            }
            response.getOutputStream().write(str.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过输入流获取JSON数据
     *
     * @param request HttpServletRequest
     * @return JSON 字符串
     */
    public static JSONObject getJson(HttpServletRequest request) {
        try (ServletInputStream inputStream = request.getInputStream()) {

            String result = new BufferedReader(new InputStreamReader(inputStream)).lines()
                .collect(Collectors.joining(System.lineSeparator()));

            return JSON.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getParams(HttpServletRequest request) {
        JSONObject header = new JSONObject();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            header.put(headerName, request.getHeader(headerName));
        }

        JSONObject body = new JSONObject();
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            body.put(entry.getKey(), entry.getValue()[0]);
        }
        JSONObject json = new JSONObject();
        json.put("header", header);
        json.put("body", body);

        return json.toJSONString();
    }

    public static Map<String, String> getHeader(HttpServletRequest request) {
        Map<String, String> header = new HashMap<>(16);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            header.put(name, request.getHeader(name));
        }

        return header;
    }

    public static Map<String, String> getBody(HttpServletRequest request) {
        Map<String, String> body = new HashMap<>(request.getParameterMap().size());
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            body.put(entry.getKey(), entry.getValue()[0]);
        }

        return body;
    }

}