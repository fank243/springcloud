package com.fank243.springcloud.gateway.admin.route;

import com.fank243.springcloud.gateway.admin.properties.AdminGatewayProperties;
import com.fank243.springcloud.gateway.core.route.AbstractNacosRouteListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 动态监听并持久化路由表
 *
 * 1.容器启动后，读取数据库路由表并发布更新到 nacos 中
 *
 * 2.nacos 更新路由表信息后，监听回调并同步更新到数据库
 *
 *
 * @author FanWeiJie
 * @date 2019-05-17 22:56:35
 */
@Slf4j
@Component
public class AdminNacosRouteListener extends AbstractNacosRouteListener {

    @Resource
    private AdminGatewayProperties properties;

    @Override
    public void run(String... args) {
        super.publishNacosConfig(properties.getApplicationName(), properties.getServerAddr(),
            properties.getNamespace());
    }
}
