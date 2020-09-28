package com.fank243.cloud.auth.oauth2.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * Default translator that converts exceptions into {@link OAuth2Exception}s. The output matches the OAuth 2.0
 * specification in terms of error response format and HTTP status code.
 *
 * @author Dave Syer
 *
 */
@JsonSerialize(using = MyOauth2ExceptionSerializer.class)
public class MyOauth2Exception extends OAuth2Exception {

    public MyOauth2Exception(String msg) {
        super(msg);
    }
}
