package com.fank243.cloud.component.common.exception;

import lombok.Getter;

/**
 * 拦截404错误页面，返回JSON错误消息
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
@Getter
public class NotFoundException extends Exception {}
