package com.fank243.springcloud.gateway.web.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fank243.springcloud.gateway.properties.FankGatewayProperties;
import com.fank243.springcloud.gateway.properties.GatewayProperties;
import com.fank243.springcloud.gateway.service.DynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

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
public class NacosRouterListener implements CommandLineRunner {

    @Resource
    private DynamicRouteService dynamicRouteService;

    @Resource(name = "MyGatewayProperties")
    private GatewayProperties gatewayProperties;
    @Resource
    private FankGatewayProperties fankGatewayProperties;

    @Override
    public void run(String... args) {
        publishNacosConfig();
    }

    /** 容器启动后，读取数据库最新路由表数据并发布到 nacos server 中 */
    private void publishNacosConfig() {
        if (fankGatewayProperties.getDataId() == null || fankGatewayProperties.getGroup() == null) {
            log.warn("网关路由nacos尚未配置");
            return;
        }

        ConfigService configService = initConfigService();
        if (configService == null) {
            log.warn("initConfigService fail");
            return;
        }

        // 向 nacos server 注册监听器，后续 nacos server 发布更新路由表信息，客户端都能够收到回调
        try {
            String configInfo =
                configService.getConfig(fankGatewayProperties.getDataId(), fankGatewayProperties.getGroup(), 3000);
            List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);

            for (RouteDefinition definition : definitionList) {
                log.info("update route : {}", definition.toString());
                dynamicRouteService.add(definition);
            }

            // 注册监听
            if (!addRouterListener(configService, fankGatewayProperties.getDataId(),
                fankGatewayProperties.getGroup())) {
                log.warn("注册nacos监听失败");
            }
        } catch (NacosException e) {
            e.printStackTrace();
            log.error("发布Nacos路由配置异常：{}", e.toString());
        }
    }

    /** 初始化网关路由 nacos config */
    private ConfigService initConfigService() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", gatewayProperties.getServerAddr());
            properties.setProperty("namespace", gatewayProperties.getNamespace());
            return NacosFactory.createConfigService(properties);
        } catch (Exception e) {
            log.error("初始化网关路由时发生错误", e);
            return null;
        }
    }

    /**
     * 监听 nacos server 下发的动态路由配置
     *
     * @param dataId 数据ID
     * @param group 分组名称
     * @return 操作结果
     */
    private boolean addRouterListener(ConfigService configService, String dataId, String group) {
        final boolean[] flag = {true};
        try {
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    RouteDefinition definition = JSON.parseObject(configInfo, RouteDefinition.class);
                    String msg = dynamicRouteService.update(definition);
                    if (!"success".equalsIgnoreCase(msg)) {
                        log.warn("更新路由失败：{}", msg);
                        flag[0] = false;
                    } else {
                        flag[0] = true;
                    }
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
            return false;
        }

        return flag[0];
    }

}
