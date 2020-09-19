package com.fank243.springcloud.gateway.admin.service;

import com.fank243.springcloud.gateway.admin.entity.User;
import com.fank243.springcloud.gateway.admin.model.JwtUser;
import com.fank243.springcloud.gateway.admin.repository.SysUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户
 *
 * @author FanWeiJie
 * @date 2020-09-17 17:04:52
 */
@Service
public class SysUserService {
    @Resource
    private SysUserRepository sysUserRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = sysUserRepository.findByUsername(username);
        return new JwtUser(user);
    }
}
