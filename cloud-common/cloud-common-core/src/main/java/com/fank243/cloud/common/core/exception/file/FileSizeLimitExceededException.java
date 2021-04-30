package com.fank243.cloud.common.core.exception.file;

/**
 * 文件名大小限制异常类
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public class FileSizeLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[] {defaultMaxSize});
    }
}
