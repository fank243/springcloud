package com.fank243.cloud.common.security.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

/**
 * 自定义feign注解 添加basePackages路径
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableRyFeignClients {
    String[] value() default {};

    String[] basePackages() default {"com.fank243.cloud"};

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
