package com.fank243.cloud.auth.oauth2.web.controller;

import com.fank243.cloud.auth.oauth2.model.JwtToken;
import com.fank243.cloud.tool.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * 获取令牌
 * 
 * 获取oauth2令牌后,改造返回自定义参数
 * 
 * @author FanWeiJie
 * @date 2020-09-25 21:29:16
 */
@RequestMapping("/oauth")
@RestController
public class OauthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /** 获取令牌 **/
    @PostMapping(value = "/token")
    public ResultInfo postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
        throws HttpRequestMethodNotSupportedException {

        // 通过框架获取令牌
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        if (oAuth2AccessToken == null) {
            return ResultInfo.fail("获取令牌失败");
        }

        // @formatter:off
        JwtToken jwtToken = JwtToken.builder()
            .token(oAuth2AccessToken.getValue())
            .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
            .expiresIn(oAuth2AccessToken.getExpiresIn())
            .tokenType(oAuth2AccessToken.getTokenType())
            .build();
        // @formatter:on

        return ResultInfo.ok(jwtToken);
    }
}
