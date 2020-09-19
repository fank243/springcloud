package com.fank243.cloud.service.user.service;

import com.fank243.cloud.component.common.utils.ResultInfo;
import com.fank243.cloud.service.user.BaseTest;
import com.fank243.cloud.service.user.domain.entity.SysUser;
import org.junit.Test;

import javax.annotation.Resource;

public class SysUserServiceTest extends BaseTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void findByUsername() {
        SysUser sysUser = sysUserService.findByUsername("aaa");
        System.out.println(sysUser);
    }

    @Test
    public void addRecord() {
        SysUser sysUser = new SysUser();
        sysUser.setId(3L);
        sysUser.setUsername("admin");
        sysUser.setMobile("13212345690");
        sysUser.setPassword("123456");
        sysUser.setSalt("AAAAAAAAAAAA");

        ResultInfo result = sysUserService.addRecord(sysUser);
        System.out.println(result);
    }
}