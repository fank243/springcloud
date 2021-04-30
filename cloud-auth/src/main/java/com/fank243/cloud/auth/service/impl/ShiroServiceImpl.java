package com.fank243.cloud.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fank243.cloud.auth.mapper.SysPermissionMapper;
import com.fank243.cloud.auth.mapper.SysRoleMapper;
import com.fank243.cloud.auth.mapper.SysUserMapper;
import com.fank243.cloud.auth.service.ShiroService;
import com.fank243.cloud.component.domain.entity.system.SysPermission;
import com.fank243.cloud.component.domain.entity.system.SysRole;
import com.fank243.cloud.component.domain.entity.system.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysPermissionMapper permissionMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public Map<String, String> loadFilterChainDefinitionMap() {
        // 权限控制map
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 放行白名单
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");

        // 授权模块接口全部放行
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/auth/logout", "anon");

        // 获取系统所有权限菜单
        List<SysPermission> permissionList = permissionMapper.selectList(null);

        if (CollUtil.isEmpty(permissionList)) {
            filterChainDefinitionMap.put("/**", "jwt");
            return filterChainDefinitionMap;
        }

        permissionList.forEach(perm -> {
            if (StrUtil.isNotBlank(perm.getUri())) {

                // 查询权限对应角色列表
                List<SysRole> roleList = roleMapper.findByRoleId(perm.getId());

                StringJoiner joiner = new StringJoiner(",", "roles[", "]");
                roleList.forEach(role -> {
                    if (StrUtil.isNotBlank(role.getCode())) {
                        joiner.add(role.getCode());
                    }
                });

                String permUrl = "jwt,";
                if (!"roles[]".equals(joiner.toString())) {
                    permUrl += joiner.toString() + ",perms[" + perm.getPermission() + "]";
                } else {
                    permUrl += "perms[" + perm.getPermission() + "]";
                }
                filterChainDefinitionMap.put(perm.getUri(), permUrl);
            }
        });

        filterChainDefinitionMap.put("/**", "jwt");

        if (log.isDebugEnabled()) {
            log.debug("从数据库中加载所有角色权限到Shiro中：{}", JSONUtil.toJsonStr(filterChainDefinitionMap));
        }
        return filterChainDefinitionMap;
    }

    @Override
    public void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, String roleId, Boolean isRemoveSession)
        throws Exception {
        synchronized (this) {
            AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter)shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new Exception("get ShiroFilter from shiroFilterFactoryBean error!");
            }

            assert shiroFilter != null;
            PathMatchingFilterChainResolver filterChainResolver =
                (PathMatchingFilterChainResolver)shiroFilter.getFilterChainResolver();

            DefaultFilterChainManager manager = (DefaultFilterChainManager)filterChainResolver.getFilterChainManager();

            // 清空拦截管理器中的存储
            manager.getFilterChains().clear();

            // 清空拦截工厂中的存储,如果不清空这里,还会把之前的带进去
            // ps:如果仅仅是更新的话,可以根据这里的 map 遍历数据修改,重新整理好权限再一起添加
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

            // 动态查询数据库中所有权限
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitionMap());

            // 重新构建生成拦截
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                manager.createChain(entry.getKey(), entry.getValue());
            }

            // 动态更新该角色相关联的用户shiro权限
            if (roleId != null) {
                updatePermissionByRoleId(roleId, isRemoveSession);
            }
        }
    }

    @Override
    public void updatePermissionByRoleId(String roleId, Boolean isRemoveSession) {
        // 查询当前角色的用户shiro缓存信息 -> 实现动态权限
        List<SysUser> userList = userMapper.selectUserByRoleId(roleId);

        // 删除当前角色关联的用户缓存信息,用户再次访问接口时会重新授权 ; isRemoveSession为true时删除Session -> 即强制用户退出
        if (CollUtil.isNotEmpty(userList)) {
            for (SysUser sysUser : userList) {
                // ShiroUtils.deleteCache(sysUser.getUsername(), isRemoveSession);
            }
        }
    }
}
