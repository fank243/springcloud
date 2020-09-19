package com.fank243.cloud.auth.oauth2.config;

import com.fank243.cloud.auth.oauth2.service.UserServiceDetail;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserServiceDetail userServiceDetail;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().anyRequest().and().authorizeRequests().antMatchers("/users/**").permitAll();
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // auth.userDetailsService(userServiceDetail).passwordEncoder(new BCryptPasswordEncoder());
    // }
    //
    // @Override
    // public @Bean AuthenticationManager authenticationManagerBean() throws Exception {
    // return super.authenticationManagerBean();
    // }

}
