package com.fank243.springcloud.gateway.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fank243.springcloud.gateway.core.router.GatewayFilterDefinition;
import com.fank243.springcloud.gateway.core.router.GatewayPredicateDefinition;
import com.fank243.springcloud.gateway.core.router.GatewayRouteDefinition;

/**
 * Spring Cloud Gateway 路由
 * 
 * @author FanWeiJie
 * @date 2019-05-18 23:12:33
 */
public class RouterUtils {

    /** 生成路由表 JSON 格式 */
    public static String getRouteDefinition(String id, String uri, int order, String filters, String predicates) {
        GatewayRouteDefinition routeDefinition = new GatewayRouteDefinition();
        routeDefinition.setId(id);
        routeDefinition.setUri(uri);
        routeDefinition.setOrder(order);

        // 过滤器
        routeDefinition.setFilters(JSONArray.parseArray(filters, GatewayFilterDefinition.class));
        // // 路由映射
        routeDefinition.setPredicates(JSONArray.parseArray(predicates, GatewayPredicateDefinition.class));

        return JSON.toJSONString(routeDefinition);
    }
}
