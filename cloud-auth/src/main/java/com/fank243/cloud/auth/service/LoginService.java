package com.fank243.cloud.auth.service;

import com.fank243.cloud.component.domain.entity.system.SysUser;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import org.springframework.stereotype.Service;

/**
 * 登录接口
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:42:44
 */
@Service
public interface LoginService {

    /**
     * 登录接口
     * 
     * @param sysUser SysUser
     * @return ResultInfo
     */
    ResultInfo login(SysUser sysUser);

    /**
     * 根据用户名查找
     *
     * @param username 用户名
     * @return SysUser
     */
    SysUser findByUsername(String username);
}
