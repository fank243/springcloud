package com.fank243.cloud.gateway.service;

import com.fank243.cloud.gateway.mapper.SysUserMapper;
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
    private SysUserMapper repository;
}
