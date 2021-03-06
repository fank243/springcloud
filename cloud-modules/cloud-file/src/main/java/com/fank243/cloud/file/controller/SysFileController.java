package com.fank243.cloud.file.controller;

import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.common.core.utils.file.FileUtils;
import com.fank243.cloud.file.service.ISysFileService;
import com.fank243.cloud.system.api.domain.SysFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件请求处理
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@RestController
public class SysFileController {
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    private ISysFileService sysFileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public ResultInfo<SysFile> upload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return ResultInfo.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return ResultInfo.fail(e.getMessage());
        }
    }
}