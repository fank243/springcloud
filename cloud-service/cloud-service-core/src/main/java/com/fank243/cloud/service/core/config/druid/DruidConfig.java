package com.fank243.cloud.service.core.config.druid;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sql 日志配置
 *
 * @author FanWeiJie
 * @date 2020-04-10 12:30:29
 */
@Configuration
public class DruidConfig {

    @Bean
    public Slf4jLogFilter logFilter() {
        Slf4jLogFilter logFilter = new Slf4jLogFilter();
        logFilter.setStatementExecutableSqlLogEnable(true);
        logFilter.setStatementLogEnabled(false);
        return logFilter;
    }

    @Bean
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        // 慢sql秒数
        statFilter.setSlowSqlMillis(3000);
        // 输出慢sql
        statFilter.setLogSlowSql(true);
        // 合并sql
        statFilter.setMergeSql(true);
        return statFilter;
    }

    @Bean
    public WallFilter wallFilter(WallConfig wallConfig) {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        // 对被认为是攻击的SQL进行LOG.error输出
        wallFilter.setLogViolation(true);
        // 对被认为是攻击的SQL抛出SQLException
        wallFilter.setThrowException(false);
        return wallFilter;
    }

    @Bean
    public WallConfig wallConfig() {
        WallConfig wallConfig = new WallConfig();
        wallConfig.setAlterTableAllow(false);
        wallConfig.setCreateTableAllow(false);
        wallConfig.setDeleteAllow(false);
        wallConfig.setMergeAllow(false);
        wallConfig.setDescribeAllow(false);
        wallConfig.setShowAllow(false);
        return wallConfig;
    }
}