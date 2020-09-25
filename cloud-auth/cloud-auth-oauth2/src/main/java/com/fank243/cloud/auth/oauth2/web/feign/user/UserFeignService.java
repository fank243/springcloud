package com.fank243.cloud.auth.oauth2.web.feign.user;

import com.fank243.cloud.auth.oauth2.web.feign.fallback.UserFallbackFactory;
import com.fank243.cloud.component.common.utils.ResultInfo;
import com.fank243.cloud.component.domain.dto.UserFormDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-service-user", path = "/user", fallbackFactory = UserFallbackFactory.class)
public interface UserFeignService {

    @PostMapping("/validateUser")
    ResultInfo validateUser(@RequestBody UserFormDTO userForm);

    @PostMapping("/sendSms")
    ResultInfo sendSms(UserFormDTO userForm);

}
