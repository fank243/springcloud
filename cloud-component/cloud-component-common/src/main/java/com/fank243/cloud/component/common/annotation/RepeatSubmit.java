package com.fank243.cloud.component.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解防止表单重复提交
 * 
 * @author FanWeiJie
 * @date 2020-09-15 09:54:38
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

}