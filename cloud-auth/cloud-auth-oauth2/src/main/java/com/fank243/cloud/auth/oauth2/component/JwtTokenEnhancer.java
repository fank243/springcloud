package com.fank243.cloud.auth.oauth2.component;

import cn.hutool.core.map.MapUtil;
import com.fank243.cloud.auth.oauth2.model.MyUserDetails;
import com.fank243.cloud.tool.utils.EntityUtils;
import com.fank243.cloud.component.common.properties.CommonProperties;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * JWT payload 添加自定义参数
 * 
 * @author FanWeiJie
 * @date 2020-09-25 22:45:45
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Resource
    private CommonProperties commonProperties;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
        DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken)accessToken;
        // 添加参数到jwt payload 中
        String un = EntityUtils.encrypt(commonProperties.getDesKey(), userDetails.getCurrUser().getId() + "");
        oAuth2AccessToken.setAdditionalInformation(MapUtil.of("un", un));
        return accessToken;
    }
}