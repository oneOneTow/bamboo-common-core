package com.luzhiqing.common.token;

import com.alibaba.fastjson.JSON;
import com.luzhiqing.common.helper.SpringContextHelper;
import com.luzhiqing.common.token.exception.PropertiesException;
import com.luzhiqing.common.util.AESUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Assert;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 13:53
 */
public class CustomGenerator implements Generator {

    protected Properties properties;

    public CustomGenerator(Properties properties) {
        this.properties = properties;
    }

    @Override
    public Token generate(UserInfo userInfo) {
        Assert.isNull(properties, "未配置信息");
        Header header = new Header();
        header.setAlgorithm(properties.getAlgorithm().name());
        header.setType(properties.getType().name());
        String jsonHeader = new Base64().encodeToString(JSON.toJSONString(header).getBytes());
        Payload payload = new Payload();
        long expiration = System.currentTimeMillis() + properties.getLifetime();
        payload.setExpiration(expiration);
        payload.setNickname(userInfo.getNickname());
        payload.setUid(userInfo.getUid());
        payload.setAdmin(userInfo.getAdmin());
        String jsonPayload = new Base64().encodeToString(JSON.toJSONString(payload).getBytes());
        String tokenBody = jsonHeader + "." + jsonPayload;
        String signature = tokenEncrypt(tokenBody);
        String tokenStr = tokenBody + "." + signature;
        Token token = new Token();
        token.setToken(tokenStr);
        token.setExpiration(expiration);
        return token;
    }

    private String tokenEncrypt(String tokenBody){
        if(AlgorithmEnum.AES.equals(properties.getAlgorithm())){
            return AESUtil.encrypt(tokenBody, properties.getPassword());
        }
        throw new PropertiesException("未配置加密算法");
    }

}
