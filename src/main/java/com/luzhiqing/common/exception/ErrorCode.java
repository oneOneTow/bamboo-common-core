package com.luzhiqing.common.exception;

public enum ErrorCode {
    STRING_IS_BLANK("14101310","字符串为空"),
    WX_FAIL_LOGIN("403","wx fail login")
    ;
    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
