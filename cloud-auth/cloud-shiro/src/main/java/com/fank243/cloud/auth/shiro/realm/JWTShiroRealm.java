package com.fank243.cloud.auth.shiro.realm;

import cn.hutool.core.util.StrUtil;
import com.fank243.cloud.auth.shiro.JWTShiroUser;
import com.fank243.cloud.auth.shiro.mapper.SysPermissionMapper;
import com.fank243.cloud.auth.shiro.mapper.SysRoleMapper;
import com.fank243.cloud.auth.shiro.service.LoginService;
import com.fank243.cloud.auth.shiro.token.JWTShiroToken;
import com.fank243.cloud.auth.shiro.utils.JWTUtil;
import com.fank243.cloud.component.domain.entity.system.SysPermission;
import com.fank243.cloud.component.domain.entity.system.SysRole;
import com.fank243.cloud.component.domain.entity.system.SysUser;
import com.fank243.cloud.component.domain.enums.PermissionType;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * JWT Realm
 * 
 * @author FanWeiJie
 * @date 2021-03-21 17:36:41
 */
@Slf4j
public class JWTShiroRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTShiroToken;
    }

    /**
     * 此方法会在用户第一次请求需要权限的接口时调用并缓存到redis中，后面在当前登录会话下不再调用
     * 
     * @param principals 登录主体
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        JWTShiroUser shiroUser = (JWTShiroUser)principals.getPrimaryPrincipal();

        Set<String> roleSet = new HashSet<>();
        Set<String> permSet = new HashSet<>();

        // 查询用户所有的角色
        List<SysRole> roleList = roleMapper.findByUserId(shiroUser.getId());
        roleList.forEach(role -> {
            if (StrUtil.isNotBlank(role.getCode())) {
                roleSet.add(role.getCode());
            }

            // 查询该角色下的所有权限，只需要查询接口权限即可
            List<SysPermission> permissionList =
                permissionMapper.findByRoleIdAndPermType(role.getId(), PermissionType.BUTTON.name());
            permissionList.forEach(perm -> {
                if (StrUtil.isNotBlank(perm.getPermission())) {
                    permSet.add(perm.getPermission());
                }
            });
        });

        if (log.isDebugEnabled()) {
            log.debug("当前登录用户[{}]拥有角色及权限列表：[{}],[{}]", shiroUser.getUsername(), roleSet, permSet);
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(permSet);
        return authorizationInfo;
    }

    /***
     * 此方法主要用来处理用户的登录请求，验证用户的账号密码是否正确以及自定义的一些登录验证
     *
     * 但此处校验用户的请求头参数Authorization中的JWT Token是否合法
     * 
     * @param token JWT Token，{@link JWTShiroToken}
     * @return 认证信息
     * @throws AuthenticationException 当认证失败时抛出异常，由{@code subject.login()}调用者处理异常并返回客户端
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (!(token instanceof JWTShiroToken)) {
            throw new UnsupportedTokenException(token.getClass().getName());
        }
        String jwtToken = (String)token.getCredentials();

        // 通过JWT Token获取用户名
        String username = JWTUtil.getUsername(jwtToken);
        if (StrUtil.isBlank(username)) {
            throw new UnknownAccountException("用户名或密码错误");
        }

        // 验证用户名
        SysUser sysUser = loginService.findByUsername(username);
        if (sysUser == null) {
            throw new UnknownAccountException("用户名或密码错误");
        }

        // 执行登录逻辑
        ResultInfo result = loginService.login(sysUser);
        if (!result.isSuccess()) {
            throw new AuthenticationException(result.getMessage());
        }
        JWTShiroUser jwtShiroUser = (JWTShiroUser)result.getPayload();

        // 通过shiro验证密码
        return new SimpleAuthenticationInfo(jwtShiroUser, jwtToken, getName());
    }
}
