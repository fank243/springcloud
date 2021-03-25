package com.fank243.cloud.feign.auth;

import com.fank243.cloud.component.tool.utils.ResultInfo;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 服务调用 鉴权模块
 * 
 * @author FanWeiJie
 * @date 2021-03-25 23:45:27
 */
// @Component
// @FeignClient(value = "cloud-auth", path = "/api/auth", fallbackFactory = AuthFallbackFactory.class)
public interface AuthFeignClient {

    /**
     * 校验登录用户是否拥有访问该资源的权限
     * 
     * @param uri 资源地址
     * @return 校验结果
     */
    @PostMapping("/hasRight")
    ResultInfo hasRight(String uri);
}
