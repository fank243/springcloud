package com.fank243.cloud.core.annotation;

import com.fank243.cloud.core.enums.LogLevel;
import com.fank243.cloud.core.enums.LogType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 业务日志
 *
 * @author FanWeiJie
 * @date 2019-05-21 21:57:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Log {

    /** 日志类别 **/
    LogType logType();

    /** 日志级别 **/
    LogLevel logLevel() default LogLevel.INFO;

    /** 接口描述 **/
    String desc();

}
