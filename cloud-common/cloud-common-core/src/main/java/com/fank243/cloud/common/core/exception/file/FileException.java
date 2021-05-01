package com.fank243.cloud.common.core.exception.file;

import com.fank243.cloud.common.core.exception.BaseException;

/**
 * 文件信息异常类
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
