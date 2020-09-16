package com.fank243.springcloud.api.framework.config.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Druid 监控配置
 *
 * @author FanWeiJie
 * @date 2020-04-10 12:31:30
 */
@WebServlet(urlPatterns = "/admin/druid/*", initParams = {//
    @WebInitParam(name = "resetEnable", value = "false"),// 禁用HTML页面上的“Reset All”功能
})
public class DruidServlet extends StatViewServlet {

}
