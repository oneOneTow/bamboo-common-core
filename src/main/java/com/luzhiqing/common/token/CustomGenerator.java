package com.luzhiqing.common.token;

import com.alibaba.fastjson.JSON;
import com.luzhiqing.common.helper.SpringContextHelper;
import com.luzhiqing.common.util.AESUtil;
import org.apache.commons.codec.binary.Base64;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 13:53
 */
public class CustomGenerator extends AbstractGenerator {

    @Override
    public Token generate(UserInfo userInfo) {
        if (null == properties){
            properties = SpringContextHelper.getBean(Properties.class);
        }
        Header header = new Header();
        header.setAlgorithm(AlgorithmEnum.AES.name());
        header.setType(TokenType.CUSTOM.name());
        String jsonHeader = new Base64().encodeToString(JSON.toJSONString(header).getBytes());
        Payload payload = new Payload();
        long expiration = System.currentTimeMillis()+properties.getLifetime();
        payload.setExpiration(expiration);
        payload.setNickname(userInfo.getNickname());
        payload.setUid(userInfo.getUid());
        payload.setAdmin(userInfo.getAdmin());
        String jsonPayload = new Base64().encodeToString(JSON.toJSONString(payload).getBytes());
        String tokenStr = jsonHeader+"."+jsonPayload;
        String signature = AESUtil.encrypt(tokenStr,properties.getPassword());
        tokenStr = tokenStr + "." +signature;
        Token token = new Token();
        token.setToken(tokenStr);
        token.setExpiration(expiration);
        return token;
    }
}
