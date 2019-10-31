package com.luzhiqing.common.exception;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/27 19:16
 */
public class BaseException extends RuntimeException {
    private String code;
    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }
}
