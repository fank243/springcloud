package com.fank243.cloud.feign.auth.fallback;

import com.fank243.cloud.component.tool.exception.BaseException;
import com.fank243.cloud.component.tool.utils.ErrUtils;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import com.fank243.cloud.feign.auth.AuthFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Hystrix 熔断处理工厂 认证鉴权模块
 *
 * @author FanWeiJie
 * @date 2021-03-26 00:02:22
 */
@Slf4j
@Component
public class AuthFallbackFactory implements FallbackFactory<AuthFeignClient> {

    @Override
    public AuthFeignClient create(Throwable throwable) {
        ResultInfo result;
        if (throwable instanceof BaseException) {
            BaseException exception = (BaseException)throwable;
            result = exception.getResult();
            exception = ErrUtils.getResult(result.getStatus(), result.getError());
            result = exception.getResult();
        } else {
            result = ResultInfo.err500();
            result.setError(throwable.toString());
        }
        ResultInfo finalResult = result;
        return new AuthFeignClient() {
            @Override
            public ResultInfo hasRight(String token, String uri) {
                log.error("Gateway|Auth|鉴权接口网络异常，参数：{}，异常信息：{}", uri, throwable.toString());
                return finalResult;
            }

            @Override
            public ResultInfo hasRight(String uri) {
                log.error("Auth|鉴权接口网络异常，参数：{}，异常信息：{}", uri, throwable.toString());
                return finalResult;
            }

            @Override
            public ResultInfo getUserInfo(String token) {
                log.error("Gateway|Auth|获取登录用户信息接口网络异常：{}", throwable.toString());
                return finalResult;
            }

            @Override
            public ResultInfo getUserInfo() {
                log.error("Auth|获取登录用户信息接口网络异常：{}", throwable.toString());
                return finalResult;
            }
        };
    }
}
