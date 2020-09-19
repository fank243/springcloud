package com.fank243.cloud.gateway.core.route.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建路由定义模型
 * 
 * @author FanWeiJie
 * @date 2019-05-17 22:03:35
 */
@Data
public class GatewayRouteDefinition {
    /** 路由的Id **/
    private String id;

    /** 路由断言集合配置 **/
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>(1);

    /** 路由过滤器集合配置 **/
    private List<GatewayFilterDefinition> filters = new ArrayList<>(1);

    /** 路由规则转发的目标uri **/
    private String uri;

    /** 路由执行的顺序 **/
    private int order = 0;
}