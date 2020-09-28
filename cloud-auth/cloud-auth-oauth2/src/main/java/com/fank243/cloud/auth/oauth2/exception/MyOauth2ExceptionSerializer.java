package com.fank243.cloud.auth.oauth2.exception;

import com.fank243.cloud.component.common.utils.WebUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Default translator that converts exceptions into {@link OAuth2Exception}s. The output matches the OAuth 2.0
 * specification in terms of error response format and HTTP status code.
 *
 * @author Dave Syer
 *
 */
public class MyOauth2ExceptionSerializer extends StdSerializer<MyOauth2Exception> {
    public MyOauth2ExceptionSerializer() {
        super(MyOauth2Exception.class);
    }

    @Override
    public void serialize(MyOauth2Exception ex, JsonGenerator gen, SerializerProvider provider) throws IOException {
        HttpServletRequest request = WebUtils.getRequest();

        gen.writeStartObject();
        gen.writeBooleanField("success", Boolean.FALSE);
        gen.writeStringField("payload", "");
        gen.writeNumberField("status", ex.getHttpErrorCode());
        gen.writeStringField("error", ex.getOAuth2ErrorCode());
        gen.writeStringField("message", ex.getMessage());
        gen.writeStringField("path", request == null ? "" : request.getServletPath());
        gen.writeNumberField("timestamp", System.currentTimeMillis());
        if (ex.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : ex.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
    }

}
