package com.fank243.springcloud.gateway.core.router;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 创建过滤器定义模型
 * 
 * @author FanWeiJie
 * @date 2019-05-17 22:04:55
 */
@Data
public class GatewayFilterDefinition {
    /** Filter Name **/
    private String name;

    /** 对应的路由规则 **/
    private Map<String, String> args = new LinkedHashMap<>();
}