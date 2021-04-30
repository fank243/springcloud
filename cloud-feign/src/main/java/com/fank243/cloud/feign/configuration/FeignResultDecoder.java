package com.fank243.cloud.feign.configuration;

import com.fank243.cloud.common.tool.exception.BaseException;
import com.fank243.cloud.common.tool.utils.ResultInfo;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author FanWeiJie
 * @date 2021-03-27 02:46:47
 */
@Slf4j
public class FeignResultDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        StringBuilder sb = new StringBuilder();
        int status = response.status();

        sb.append("Feign Client 调用异常：[").append(status).append("，").append(response.request().url());

        String bodyStr = "";
        try {
            Response.Body body = response.body();
            if (body != null) {
                bodyStr = Util.toString(body.asReader(Util.UTF_8));
                sb.append(",").append(bodyStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("]");
        log.warn(sb.toString());

        ResultInfo result = new ResultInfo();
        result.setStatus(status);
        result.setError(bodyStr);
        return new BaseException(result);
    }
}
