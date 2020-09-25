package com.fank243.cloud.auth.oauth2.service;

import com.fank243.cloud.auth.oauth2.model.MyUserDetails;
import com.fank243.cloud.auth.oauth2.repository.SysUserRepository;
import com.fank243.cloud.component.common.utils.WebUtils;
import com.fank243.cloud.component.domain.dto.CurrUser;
import com.fank243.cloud.component.domain.entity.SysPermission;
import com.fank243.cloud.component.domain.entity.SysRole;
import com.fank243.cloud.component.domain.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户
 *
 * @author FanWeiJie
 * @date 2020-09-17 17:04:52
 */
@Slf4j
@Service
public class MyUserServiceDetail implements UserDetailsService {
    @Resource
    private SysUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpServletRequest request = WebUtils.getRequest();

        log.info(WebUtils.getParams(request));

        SysUser sysUser = repository.findByUsername(username);
        CurrUser currUser = new CurrUser();
        currUser.setId(sysUser.getId());
        currUser.setUsername(username);
        currUser.setPassword(sysUser.getPassword());

        List<String> permList = new ArrayList<>();
        Set<SysRole> roles = sysUser.getRoles();
        for (SysRole sysRole : roles) {
            permList.addAll(
                sysRole.getPermissions().stream().map(SysPermission::getPermission).collect(Collectors.toList()));
        }

        currUser.setPermList(permList);

        return new MyUserDetails(currUser);
    }
}
