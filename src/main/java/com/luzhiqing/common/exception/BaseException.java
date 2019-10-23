package com.luzhiqing.common.exception;

public class BaseException extends RuntimeException {
    private String code;

    public BaseException(String code) {
        this.code = code;
    }

    public BaseException(String message, String code) {
        super(message);
        this.code = code;
    }
}
