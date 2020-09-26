package com.fank243.cloud.auth.oauth2.web.feign.fallback;

import com.fank243.cloud.auth.oauth2.web.feign.user.UserFeignService;
import com.fank243.cloud.tool.utils.ResultInfo;
import com.fank243.cloud.component.domain.dto.UserFormDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * feign 异常处理
 * 
 * @author FanWeiJie
 * @date 2020-09-21 16:41:44
 */
@Slf4j
@Component
public class UserFallbackFactory implements FallbackFactory<UserFeignService> {

    @Override
    public UserFeignService create(Throwable throwable) {
        String message = throwable == null ? "" : throwable.getMessage();
        if (StringUtils.isNotBlank(message)) {
            log.error(message);
        }
        return new UserFeignService() {
            @Override
            public ResultInfo validateUser(UserFormDTO userForm) {
                return ResultInfo.err503();
            }

            @Override
            public ResultInfo sendSms(UserFormDTO userForm) {
                return ResultInfo.err503();
            }
        };
    }
}
