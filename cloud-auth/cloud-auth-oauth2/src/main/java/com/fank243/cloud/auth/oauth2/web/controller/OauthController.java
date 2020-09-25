package com.fank243.cloud.auth.oauth2.web.controller;

import com.fank243.cloud.auth.oauth2.domain.Oauth2TokenDto;
import com.fank243.cloud.component.common.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RequestMapping("/oauth")
@RestController
public class OauthController {
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @PostMapping(value = "/token")
    public ResultInfo postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
        throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder().token(oAuth2AccessToken.getValue())
            .refreshToken(oAuth2AccessToken.getRefreshToken().getValue()).expiresIn(oAuth2AccessToken.getExpiresIn())
            .tokenHead("Bearer ").build();

        return ResultInfo.ok(oauth2TokenDto);
    }
}
