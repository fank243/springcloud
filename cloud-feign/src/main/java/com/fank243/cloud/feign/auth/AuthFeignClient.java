package com.fank243.cloud.feign.auth;

import com.fank243.cloud.component.tool.constant.Constants;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import com.fank243.cloud.feign.auth.fallback.AuthFallbackFactory;
import com.fank243.cloud.feign.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 服务调用 鉴权模块
 * 
 * @author FanWeiJie
 * @date 2021-03-25 23:45:27
 */
@Component
@FeignClient(value = "cloud-auth", path = "/api/auth", fallbackFactory = AuthFallbackFactory.class,
    configuration = FeignConfiguration.class)
public interface AuthFeignClient {

    /**
     * Gateway：校验登录用户是否拥有访问该资源的权限
     * 
     * @param uri 资源地址
     * @return 校验结果
     */
    @PostMapping("/hasRight")
    ResultInfo hasRight(@RequestHeader(Constants.TOKEN_NAME) String token, String uri);

    /**
     * 校验登录用户是否拥有访问该资源的权限
     * 
     * @param uri 资源地址
     * @return 校验结果
     */
    @PostMapping("/hasRight")
    ResultInfo hasRight(String uri);

    /**
     * Gateway：获取当前登录用户信息
     * 
     * @return 用户登录信息
     */
    @GetMapping("/getUserInfo")
    ResultInfo getUserInfo(@RequestHeader(Constants.TOKEN_NAME) String token);

    /**
     * 校验登录用户是否拥有访问该资源的权限
     * 
     * @return 用户登录信息
     */
    @GetMapping("/getUserInfo")
    ResultInfo getUserInfo();
}
