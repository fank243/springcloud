package com.fank243.cloud.gateway.admin.config;

import com.fank243.cloud.gateway.admin.component.RestAuthenticationEntryPoint;
import com.fank243.cloud.gateway.admin.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * Spring Security 配置类
 * 
 * @author FanWeiJie
 * @date 2020-09-18 18:00:47
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    /** 密码编码器 */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService createUserDetailsService() {
        return userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置自定义的userDetailsService以及密码编码器
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            //
            .exceptionHandling()
            //
            .authenticationEntryPoint(restAuthenticationEntryPoint)
            //
            .and()
            //
            .authorizeRequests()
            //
            .antMatchers("/api/foos").authenticated()
            //
            .antMatchers("/api/admin/**").hasRole("ADMIN")
            //
            .and()
            //
            .formLogin()
            // .successHandler(mySuccessHandler)
            // .failureHandler(myFailureHandler)
            //
            .and()
            //
            .logout();
    }

}
