package com.fank243.cloud.component.framework.error;

import cn.hutool.core.bean.BeanUtil;
import com.fank243.cloud.tool.exception.BaseException;
import com.fank243.cloud.tool.exception.ForbiddenException;
import com.fank243.cloud.tool.exception.NotFoundException;
import com.fank243.cloud.tool.exception.UnauthorizedException;
import com.fank243.cloud.tool.utils.ErrUtils;
import com.fank243.cloud.tool.utils.ResultInfo;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 拦截404异常，并抛出自定义404异常
 *
 * 由于spring默认不抛出404 NoHandleException异常，导致使用@RestControllerAdvice无法拦截到
 *
 * 但是spring会将404错误重定向到/error控制器，因此可以在此处抛出自定义404异常，就可以被@RestControllerAdvice拦截到
 *
 * @author FanWeiJie
 * @date 2019-10-23 16:02:37
 */
@RequestMapping("${server.error.path:${error.path:/error}}")
@Controller
public class MyErrorController extends BasicErrorController {

    public MyErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @SneakyThrows
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        throw new NotFoundException();
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> errMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());

        ResultInfo result = BeanUtil.toBeanIgnoreError(errMap, ResultInfo.class);

        HttpStatus httpStatus = getStatus(request);

        throw ErrUtils.exception(httpStatus.value(), result);
    }

}
