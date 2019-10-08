package com.luzhiqing.common.token;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 13:16
 */
@Component
@ConfigurationProperties(prefix = "token")
public class Properties {
    private long lifetime;
    private AlgorithmEnum algorithm;
    private TokenType type;
    private String password;

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public AlgorithmEnum getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(AlgorithmEnum algorithm) {
        this.algorithm = algorithm;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
