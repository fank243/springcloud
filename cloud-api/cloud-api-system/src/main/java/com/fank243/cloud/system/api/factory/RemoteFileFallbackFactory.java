package com.fank243.cloud.system.api.factory;

import com.fank243.cloud.common.core.domain.R;
import com.fank243.cloud.system.api.RemoteFileService;
import com.fank243.cloud.system.api.domain.SysFile;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务降级处理
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@Slf4j
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService> {

    @Override
    public RemoteFileService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService() {
            @Override
            public R<SysFile> upload(MultipartFile file) {
                return R.fail("上传文件失败:" + throwable.getMessage());
            }
        };
    }
}
