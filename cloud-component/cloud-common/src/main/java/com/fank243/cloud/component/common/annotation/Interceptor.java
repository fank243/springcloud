package com.fank243.cloud.component.common.annotation;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 拦截器配置
 *
 * @author FanWeiJie
 * @date 2019-05-21 21:57:15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Interceptor {
    /** 拦截器名称 **/
    String[] value() default {};

    /** 包含资源 **/
    String[] include() default {};

    /** 排除资源 **/
    String[] exclude() default {};

    int order() default Ordered.LOWEST_PRECEDENCE;
}