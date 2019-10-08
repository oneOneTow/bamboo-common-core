package com.luzhiqing.common.token;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 12:13
 */
public class Token {
    /**
     * 过期时间
     */
    private Long expiration;
    /**
     * token
     */
    private String token;

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
