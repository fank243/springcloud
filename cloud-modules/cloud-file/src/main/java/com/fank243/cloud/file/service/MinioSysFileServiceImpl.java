package com.fank243.cloud.file.service;

import com.fank243.cloud.file.config.MinioConfig;
import com.fank243.cloud.file.utils.FileUploadUtils;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Minio 文件存储
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@Service
public class MinioSysFileServiceImpl implements ISysFileService {
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient client;

    /**
     * 本地文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String fileName = FileUploadUtils.extractFilename(file);
        PutObjectArgs args = PutObjectArgs.builder().bucket(minioConfig.getBucketName()).object(fileName)
            .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build();
        client.putObject(args);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }
}
