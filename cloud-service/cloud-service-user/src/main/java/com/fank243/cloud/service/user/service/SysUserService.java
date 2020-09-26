package com.fank243.cloud.service.user.service;

import com.fank243.cloud.tool.utils.ResultInfo;
import com.fank243.cloud.component.domain.entity.SysUser;
import com.fank243.cloud.service.user.repository.SysUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员
 * 
 * @author FanWeiJie
 * @date 2020-09-19 20:19:52
 */
@Service
public class SysUserService {
    @Resource
    private SysUserRepository repository;

    public SysUser findByUsername(String username){
        return repository.findByUsername(username);
    }

    public ResultInfo addRecord(SysUser sysUser){
        sysUser = repository.save(sysUser);
        return ResultInfo.ok(sysUser);
    }
}
