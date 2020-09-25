package com.fank243.cloud.gateway.admin.web.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.fank243.cloud.component.common.constant.RedisConstants;
import com.fank243.cloud.component.common.service.RedisService;
import com.fank243.cloud.component.common.utils.JwtUtils;
import com.fank243.cloud.component.common.utils.ResultInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证
 * 
 * @author FanWeiJie
 * @date 2020-09-24 09:41:12
 */
@RequestMapping("/authentication")
@RestController
public class AuthenticationController {

    @Resource
    private RedisService redisService;

    @PostMapping
    public ResultInfo authentication() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        String token = JwtUtils.createToken(map, 1000);

        redisService.set(RedisConstants.SYS_CURR_USER + 1, DigestUtil.md5Hex(token), 1000);

        return ResultInfo.ok(token);
    }
}
