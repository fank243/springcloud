package com.fank243.cloud.auth.oauth2.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * Default translator that converts exceptions into {@link OAuth2Exception}s. The output matches the OAuth 2.0
 * specification in terms of error response format and HTTP status code.
 *
 * @author Dave Syer
 *
 */
@Component
public class MyOauth2ExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        OAuth2Exception oAuth2Exception = (OAuth2Exception)e;

        return ResponseEntity.status(oAuth2Exception.getHttpErrorCode())
            .body(new MyOauth2Exception(oAuth2Exception.getMessage()));
    }
}
