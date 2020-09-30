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
 * 自定义oauth2异常JSON
 * 
 * @author FanWeiJie
 * @date 2020-09-29 16:28:40
 */
public class MyOauth2ExceptionSerializer extends StdSerializer<MyOauth2Exception> {
    public MyOauth2ExceptionSerializer() {
        super(MyOauth2Exception.class);
    }

    @Override
    public void serialize(MyOauth2Exception ex, JsonGenerator generator, SerializerProvider provider)
        throws IOException {
        HttpServletRequest request = WebUtils.getRequest();

        generator.writeStartObject();
        generator.writeBooleanField("success", Boolean.FALSE);
        generator.writeStringField("payload", "");
        generator.writeNumberField("status", ex.getHttpErrorCode());
        generator.writeStringField("error", ex.getOAuth2ErrorCode());
        generator.writeStringField("message", ex.getMessage());
        generator.writeStringField("path", request == null ? "" : request.getServletPath());
        generator.writeNumberField("timestamp", System.currentTimeMillis());
        if (ex.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : ex.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                generator.writeStringField(key, add);
            }
        }
        generator.writeEndObject();
    }

}
