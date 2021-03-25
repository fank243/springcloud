package com.fank243.cloud.auth.shiro.filter;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fank243.cloud.auth.shiro.token.JWTShiroToken;
import com.fank243.cloud.auth.shiro.utils.JWTUtil;
import com.fank243.cloud.component.common.constant.Constants;
import com.fank243.cloud.component.common.constant.RedisConstants;
import com.fank243.cloud.component.common.service.RedisService;
import com.fank243.cloud.component.common.utils.WebUtils;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 未登录拦截
 *
 * 执行步骤：
 *
 * 1.首先会调用preHandle方法进行跨域处理
 *
 * 2.接着进入isAccessAllowed方法执行executeLogin方法进行登录
 * 
 * 3.如果登录验证通过，则进入onLoginSuccess方法验证token
 *
 * 4.如果验证失败，则会进入onAccessDenied方法
 * 
 * @author FanWeiJie
 * @date 2021-03-21 23:08:10
 */
@Slf4j
public class JWTShiroFilter extends BasicHttpAuthenticationFilter {

    /** 通过 ApplicationContext 注入 **/
    @Autowired
    private RedisService redisService;

    /**
     * 拦截器的前置方法，此处进行跨域处理
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",
            httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
        }

        // 如果不带token，不去验证shiro
        if (!isLoginAttempt(request, response)) {
            WebUtils.printJson(response, ResultInfo.fail("请携带认证参数"));
            return false;
        }
        return super.preHandle(request, response);

    }

    /**
     * 此方法用来验证是否客户端是否携带了请求头参数Authorization，如果没有携带则不进入Shiro认证
     */
    @Override
    protected boolean isLoginAttempt(String authzHeader) {
        HttpServletRequest request = WebUtils.getRequest();
        if (request == null) {
            return false;
        }
        return StrUtil.isNotBlank(request.getHeader(AUTHORIZATION_HEADER));
    }

    /**
     * 该方法从字面意思理解，即是否允许放行请求，继续进入后面的拦截器处理
     * 
     * 因此这里面只需要判断用户是否已经登录认证了，如果认证了则放行，否则不放行
     * 
     * 如果该方法返回false，则会进入到{@link JWTShiroFilter#onAccessDenied(ServletRequest, ServletResponse)} 方法中
     * 
     * 当用户未登录的时候，则需要在onAccessDenied()方法中返回未登录的JSON串
     * 
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);
        } catch (Exception e) {
            log.error("isAccessAllowed()执行异常：{}", e.toString());
            WebUtils.printJson(response, ResultInfo.fail("登录失败"));
            return false;
        }

    }

    /**
     * 覆盖此方法，创建自定义token
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String jwtToken = ((HttpServletRequest)request).getHeader(AUTHORIZATION_HEADER);
        if (jwtToken != null) {
            return new JWTShiroToken(jwtToken);
        }
        return null;
    }

    /**
     * 登录验证通过后对 JWTToken 进行验证
     * {@link AuthenticatingFilter#executeLogin(javax.servlet.ServletRequest, javax.servlet.ServletResponse)}方法验证通过后会进入此方法
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
        ServletResponse response) {

        String jwtToken = (String)token.getPrincipal();
        if (StrUtil.isBlank(jwtToken)) {
            return true;
        }

        try {
            if (!JWTUtil.verify(jwtToken)) {
                return false;
            }

            // 判断Redis是否存在所对应的RefreshToken
            String username = JWTUtil.getUsername(jwtToken);
            Long currentTime = JWTUtil.getCurrentTime(jwtToken);

            // 验证Token是否有效
            if (redisService.hasKey(RedisConstants.JWT_TOKEN_PRE + username)) {
                Long currentTimeMillisRedis = (Long)redisService.get(RedisConstants.JWT_TOKEN_PRE + username);
                return currentTimeMillisRedis.equals(currentTime);
            }

            return false;
        } catch (Exception e) {
            log.error("onLoginSuccess()执行异常：{}", e.toString());
            if (e instanceof TokenExpiredException) {
                log.error("JWT Token已过期，开始签发新Token并刷新有效时长：{}", e.toString());
                // Token 过期刷新Token
                return refreshToken(request, response);
            }
        }

        return true;
    }

    /**
     * 登录验证通过后对 JWTToken 进行验证失败后，执行此方法
     * {@link JWTShiroFilter#onLoginSuccess(AuthenticationToken, Subject, ServletRequest, ServletResponse)}方法验证失败后会进入此方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        this.sendChallenge(request, response);
        WebUtils.printJson(response, ResultInfo.fail("认证秘钥校验不通过或者秘钥已过期"));
        return false;
    }

    /**
     * 刷新AccessToken，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     */
    private boolean refreshToken(ServletRequest request, ServletResponse response) {
        String jwtToken = ((HttpServletRequest)request).getHeader(AUTHORIZATION_HEADER);

        String username = JWTUtil.getUsername(jwtToken);
        Long currentTime = JWTUtil.getCurrentTime(jwtToken);

        // 如何redis中不存在，则不刷新
        if (!redisService.hasKey(RedisConstants.JWT_TOKEN_PRE + username)) {
            return false;
        }

        // 获取RefreshToken的过期时间
        Long currentTimeMillisRedis = (Long)redisService.get(RedisConstants.JWT_TOKEN_PRE + username);

        // 获取当前AccessToken中的时间戳，与RefreshToken的时间戳对比，如果当前时间戳一致，进行AccessToken刷新
        if (currentTimeMillisRedis.equals(currentTime)) {

            // 获取当前时间戳
            long currentTimeMillis = System.currentTimeMillis();

            // 记录刷新后的时间
            redisService.set(RedisConstants.JWT_TOKEN_PRE + username, currentTimeMillis, JWTUtil.REFRESH_EXPIRE_TIME);

            // 重新签发Token
            jwtToken = JWTUtil.sign(username, currentTimeMillis);

            HttpServletResponse httpServletResponse = (HttpServletResponse)response;
            httpServletResponse.setHeader(Constants.TOKEN_NAME, jwtToken);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            return true;

        }
        return false;
    }

}
