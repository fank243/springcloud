package com.fank243.cloud.common.core.exception.file;

/**
 * 文件名称超长限制异常类
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public class FileNameLengthLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[] {defaultFileNameLength});
    }
}
