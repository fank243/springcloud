package com.fank243.cloud.auth.oauth2.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 自定义oauth2异常JSON
 *
 * @author FanWeiJie
 * @date 2020-09-29 16:28:40
 */
@Component
public class MyOauth2ExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        OAuth2Exception oAuth2Exception = (OAuth2Exception)e;
        MyOauth2Exception oauth2Exception = new MyOauth2Exception(oAuth2Exception.getMessage());
        return ResponseEntity.status(oAuth2Exception.getHttpErrorCode()).body(oauth2Exception);
    }
}
