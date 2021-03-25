package com.fank243.cloud.auth.shiro.token;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * JWT Token
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:55:53
 */
@NoArgsConstructor
@AllArgsConstructor
public class JWTShiroToken implements AuthenticationToken {

    /** JWT Token **/
    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
