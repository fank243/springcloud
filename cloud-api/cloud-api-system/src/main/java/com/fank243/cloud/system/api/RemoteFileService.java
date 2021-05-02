package com.fank243.cloud.system.api;

import com.fank243.cloud.common.core.domain.ResultInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import com.fank243.cloud.common.core.constant.ServiceNameConstants;
import com.fank243.cloud.system.api.domain.SysFile;
import com.fank243.cloud.system.api.factory.RemoteFileFallbackFactory;

/**
 * 文件服务
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE,
    fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService {
    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultInfo<SysFile> upload(@RequestPart(value = "file") MultipartFile file);
}
