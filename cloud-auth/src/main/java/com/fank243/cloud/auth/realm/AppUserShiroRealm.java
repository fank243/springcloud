//package com.fank243.cloud.auth.realm;
//
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import com.fank243.cloud.auth.SysShiroUser;
//import com.fank243.cloud.auth.service.LoginService;
//import com.fank243.cloud.auth.token.SysShiroToken;
//import com.fank243.cloud.auth.token.SysShiroForm;
//import com.fank243.cloud.component.tool.utils.ResultInfo;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.pam.UnsupportedTokenException;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * 后台用户Realm
// *
// * @author FanWeiJie
// * @date 2021-03-21 17:36:41
// */
//public class AppUserShiroRealm extends AuthorizingRealm {
//
//    @Autowired
//    private LoginService loginService;
//
//    /**
//     * 此方法会在用户第一次请求需要权限的接口时调用并缓存到redis中，后面在当前登录会话下不再调用
//     *
//     * @param principals 登录主体
//     * @return AuthorizationInfo
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SysShiroUser sysShiroUser = (SysShiroUser)principals.getPrimaryPrincipal();
//
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(sysShiroUser.getRoles());
//        authorizationInfo.setStringPermissions(sysShiroUser.getPerms());
//        return authorizationInfo;
//    }
//
//    /***
//     * 此方法主要用来处理用户的登录请求，验证用户的账号密码是否正确以及自定义的一些登录验证
//     *
//     * @param token 自定义token，{@link SysShiroToken}
//     * @return 认证信息
//     * @throws AuthenticationException 当认证失败时抛出异常，由{@code subject.login()}调用者处理异常并返回客户端
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        if (!(token instanceof SysShiroToken)) {
//            throw new UnsupportedTokenException(token.getClass().getName());
//        }
//        SysShiroToken shiroToken = (SysShiroToken)token;
//
//        SysShiroForm sysShiroForm = (SysShiroForm)shiroToken.getPrincipal();
//
//        // 执行登录逻辑
//        ResultInfo result = loginService.login(sysShiroForm);
//        if (!result.isSuccess()) {
//            throw new AuthenticationException(result.getMessage());
//        }
//        JSONObject json = JSONUtil.parseObj(result.getPayload());
//        SysShiroUser sysShiroUser = JSONUtil.toBean(json.getJSONObject("shiroUser"), SysShiroUser.class);
//
//        String salt = json.getStr("salt", "");
//        String password = json.getStr("password", "");
//
//        // 通过shiro验证密码
//        return new SimpleAuthenticationInfo(sysShiroUser, password, ByteSource.Util.bytes(salt), getName());
//    }
//}
