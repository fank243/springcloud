package com.fank243.cloud.gateway.core.route.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 创建路由断言定义模型
 * 
 * @author FanWeiJie
 * @date 2019-05-17 22:02:14
 */
@Data
public class GatewayPredicateDefinition {
    /** 断言对应的Name **/
    private String name;

    /** 配置的断言规则 **/
    private Map<String, String> args = new LinkedHashMap<>(1);
}