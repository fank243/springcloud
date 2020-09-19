package com.fank243.cloud.gateway.admin.config;

import com.fank243.cloud.gateway.admin.filter.JwtAuthenticationFilter;
import com.fank243.cloud.gateway.admin.filter.JwtAuthorizationFilter;
import com.fank243.cloud.gateway.admin.service.MyUserDetailsService;
import com.fank243.cloud.gateway.core.exception.JwtAccessDeniedHandler;
import com.fank243.cloud.gateway.core.exception.JwtAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
    private MyUserDetailsService userDetailsService;

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
        http.cors().and()
            // 禁用 CSRF
            .csrf().disable()
            // 登录接口
            .authorizeRequests().antMatchers(HttpMethod.POST, "/auth/login").permitAll()
            // 指定路径下的资源需要验证了的用户才能访问
            .antMatchers("/api/**").authenticated()
            //
            .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
            // 其他都放行了
            .anyRequest().permitAll().and()
            // 添加自定义Filter
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService))
            // 不需要session（不创建会话）
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // 授权异常处理
            .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
            .accessDeniedHandler(new JwtAccessDeniedHandler());
        // 防止H2 web 页面的Frame 被拦截
        http.headers().frameOptions().disable();
    }

}
