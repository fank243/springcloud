package com.fank243.cloud.auth.oauth2.controller;

import com.fank243.cloud.component.common.utils.ResultInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @GetMapping(value = "/json")
    public ResultInfo getJson(Principal principal) {
        return ResultInfo.ok();
    }
}
