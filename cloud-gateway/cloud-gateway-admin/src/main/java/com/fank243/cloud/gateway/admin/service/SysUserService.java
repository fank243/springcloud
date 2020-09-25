package com.fank243.cloud.gateway.admin.service;

import com.fank243.cloud.gateway.admin.repository.SysUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员
 * 
 * @author FanWeiJie
 * @date 2020-09-24 17:36:35
 */
@Service
public class SysUserService {
    @Resource
    private SysUserRepository repository;
}
