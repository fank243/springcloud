package com.fank243.springcloud.api.framework.error;

import com.fank243.springcloud.common.utils.ResultInfo;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 捕获Filter异常
 *
 * @author FanWeiJie
 * @date 2019-10-23 16:02:37
 */
@Controller
public class MyErrorController extends BasicErrorController {

    public MyErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);

        Map<String, Object> map;
        switch (status) {
            // 401
            case UNAUTHORIZED:
                map = buildResp(ResultInfo.unauthorized());
                break;
            // 403
            case FORBIDDEN:
                map = buildResp(ResultInfo.forbidden());
                break;
            // 404
            case NOT_FOUND:
                map = buildResp(ResultInfo.notFund());
                break;
            // 405
            case METHOD_NOT_ALLOWED:
                map = buildResp(ResultInfo.methodNotSupported());
                break;

            // default
            default:
                map = buildResp(ResultInfo.exception());
                break;
        }

        return new ResponseEntity<>(map, status);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private Map<String, Object> buildResp(ResultInfo result) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("status", result.getStatus());
        map.put("message", result.getMessage());
        map.put("success", result.isSuccess());
        map.put("timestamp", System.currentTimeMillis());
        map.put("payload", result.getPayload());
        return map;
    }
}
