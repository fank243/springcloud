package com.fank243.cloud.auth.shiro.filter;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fank243.cloud.auth.shiro.token.JWTShiroToken;
import com.fank243.cloud.auth.shiro.utils.JWTUtil;
import com.fank243.cloud.component.common.service.RedisService;
import com.fank243.cloud.component.common.utils.WebUtils;
import com.fank243.cloud.component.tool.constant.Constants;
import com.fank243.cloud.component.tool.constant.RedisConstants;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
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
     * 1. 登录验证第一步，前置拦截
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
            WebUtils.printJson(response, ResultInfo.fail("非法请求"));
            return false;
        }
        return super.preHandle(request, response);

    }

    /**
     * 2. 登录第二步，验证客户端请求头参数“Authorization”是否存在，不存在则不进入shiro验证流程，直接返回错误信息
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
     * 3. 登录验证第三步，调用登录方法，执行登录认证流程
     * 
     * 3.1 如果登录认证失败，则调用
     * {@link JWTShiroFilter#onLoginFailure(AuthenticationToken, AuthenticationException, ServletRequest, ServletResponse)}
     * }
     *
     * 3.2 如果登录认证成功，则调用
     * {@link JWTShiroFilter#onLoginSuccess(AuthenticationToken, Subject, ServletRequest, ServletResponse)}}
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);
        } catch (Exception e) {
            log.error("isAccessAllowed()执行异常：{}", e.toString());
        }
        return false;
    }

    /**
     * 3.1 登录认证成功后调用，在此方法里面执行我们自己的JWT Token认证流程及刷新Token流程
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
                log.error("用户凭证已过期，开始进入签发新凭证流程：{}", e.toString());
                // Token 过期刷新Token
                return refreshToken(request, response);
            }
        }

        return true;
    }

    /**
     * 3.2 登录认证失败后调用
     *
     * 3.2.1 如果返回true，则不继续往下执行，即不调用 {@link JWTShiroFilter#onAccessDenied(ServletRequest, ServletResponse)}
     *
     * 3.2.2 如果返回false，则调用 {@link JWTShiroFilter#onAccessDenied(ServletRequest, ServletResponse)}
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
        ServletResponse response) {
        WebUtils.printJson(response, ResultInfo.fail("登录失败，请核实登录信息"));
        return true;
    }

    /**
     * 4. 当登录认证失败接口调用后，执行此方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        // 发送响应信息
        WebUtils.printJson(response, ResultInfo.err201());
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
            log.error("刷新用户凭证失败，Redis中刷新凭证KEY已丢失");
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
        log.error("刷新用户凭证失败，与Redis刷新凭证时间不一致");
        return false;
    }

}
