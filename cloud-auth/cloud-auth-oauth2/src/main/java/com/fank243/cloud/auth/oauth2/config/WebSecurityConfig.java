package com.fank243.cloud.auth.oauth2.config;

import com.fank243.cloud.component.common.utils.WebUtils;
import com.fank243.cloud.tool.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * spring security 配置
 * 
 * @author FanWeiJie
 * @date 2020-09-20 23:14:36
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /** 访问策略 **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf().disable()
            .authorizeRequests()
            .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
            .antMatchers("/rsa/publicKey").permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling()
            // 未授权
            .accessDeniedHandler((request, response, accessDeniedException) -> {
                log.error("accessDeniedException == >{}", accessDeniedException.toString());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                WebUtils.printJson(response, ResultInfo.err403());
            })
            // 未认证
            .authenticationEntryPoint((request, response, authException) -> {
                log.error("authException == >{}", authException.toString());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                WebUtils.printJson(response, ResultInfo.err403());
            });
        // @formatter:on
    }

    @Autowired
    public DataSource dataSource;

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        // 从数据库读取账号密码
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
