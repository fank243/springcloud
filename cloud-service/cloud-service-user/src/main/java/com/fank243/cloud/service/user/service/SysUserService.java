package com.fank243.cloud.service.user.service;

import com.fank243.cloud.component.tool.utils.ResultInfo;
import com.fank243.cloud.component.domain.entity.system.SysUser;
import com.fank243.cloud.service.user.mapper.SysUserMapper;
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
    private SysUserMapper sysUserMapper;

    public SysUser findByUsername(String username){
        return sysUserMapper.findByUsername(username);
    }

    public ResultInfo addRecord(SysUser sysUser){
        sysUser = sysUserMapper.addRecord(sysUser);
        return ResultInfo.ok(sysUser);
    }
}
