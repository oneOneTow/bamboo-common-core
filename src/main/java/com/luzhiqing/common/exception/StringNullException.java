package com.luzhiqing.common.exception;

public class StringNullException extends BaseException{
    private static final String msg = "字符串为空";
    public StringNullException() {
        super(ErrorCode.STRING_IS_BLANK.getCode(),ErrorCode.STRING_IS_BLANK.getMessage());
    }

    public StringNullException(String message) {
        super(message, ErrorCode.STRING_IS_BLANK.getCode());
    }

}
