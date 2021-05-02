package com.fank243.cloud.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fank243.cloud.common.core.domain.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fank243.cloud.common.core.constant.UserConstants;
import com.fank243.cloud.common.core.utils.SecurityUtils;
import com.fank243.cloud.common.core.utils.ServletUtils;
import com.fank243.cloud.common.core.utils.StringUtils;
import com.fank243.cloud.common.core.web.controller.BaseController;
import com.fank243.cloud.common.log.annotation.Log;
import com.fank243.cloud.common.log.enums.BusinessType;
import com.fank243.cloud.common.security.service.TokenService;
import com.fank243.cloud.system.api.RemoteFileService;
import com.fank243.cloud.system.api.domain.SysFile;
import com.fank243.cloud.system.api.domain.SysUser;
import com.fank243.cloud.system.api.model.LoginUser;
import com.fank243.cloud.system.service.ISysUserService;

/**
 * 个人信息 业务处理
 * 
 * @author FanWeiJie \n @date 2021-04-05 23:41:10
 */
@RestController
@RequestMapping("/user/profile")
public class SysProfileController extends BaseController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 个人信息
     */
    @GetMapping
    public ResultInfo<?> profile() {
        String username = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roleGroup", userService.selectUserRoleGroup(username));
        map.put("postGroup", userService.selectUserPostGroup(username));
        return ResultInfo.ok(map);
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultInfo<?> updateProfile(@RequestBody SysUser user) {
        if (StringUtils.isNotEmpty(user.getPhonenumber())
            && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return ResultInfo.fail("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
            && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return ResultInfo.fail("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        if (userService.updateUserProfile(user) > 0) {
            LoginUser loginUser = tokenService.getLoginUser();
            // 更新缓存用户信息
            loginUser.getSysUser().setNickName(user.getNickName());
            loginUser.getSysUser().setPhonenumber(user.getPhonenumber());
            loginUser.getSysUser().setEmail(user.getEmail());
            loginUser.getSysUser().setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return ResultInfo.ok();
        }
        return ResultInfo.fail("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public ResultInfo<?> updatePwd(String oldPassword, String newPassword) {
        String username = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        String password = user.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return ResultInfo.fail("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return ResultInfo.fail("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(username, SecurityUtils.encryptPassword(newPassword)) > 0) {
            // 更新缓存用户密码
            LoginUser loginUser = tokenService.getLoginUser();
            loginUser.getSysUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return ResultInfo.ok();
        }
        return ResultInfo.fail("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public ResultInfo<?> avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            ResultInfo<SysFile> fileResult = remoteFileService.upload(file);
            if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getPayload())) {
                return ResultInfo.fail("文件服务异常，请联系管理员");
            }
            String url = fileResult.getPayload().getUrl();
            if (userService.updateUserAvatar(loginUser.getUsername(), url)) {
                Map<String, Object> map = new HashMap<>(1);
                map.put("imgUrl", url);
                // 更新缓存用户头像
                loginUser.getSysUser().setAvatar(url);
                tokenService.setLoginUser(loginUser);
                return ResultInfo.ok(map);
            }
        }
        return ResultInfo.fail("上传图片异常，请联系管理员");
    }
}
