package com.luzhiqing.common.token;


import com.luzhiqing.common.util.AESUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 00:06
 */
public class TokenUtil {
    private final static Logger logger = LoggerFactory.getLogger(TokenUtil.class);
    private final static String TOKEN_SPLIT = ".";
    private final static int TOKEN_SPLIT_LENGTH = 3;
    private final static String TOKEN = "token";
    /**
     * 验证token
     * @param token
     * @param password
     * @return
     */
    public static boolean verify(String token,String password){
        if(StringUtils.isBlank(token)){
            return false;
        }
        String [] tokens = token.split(TOKEN_SPLIT);
        if(TOKEN_SPLIT_LENGTH!=tokens.length){
            return false;
        }
        String header = tokens[0];
        String payload = tokens[1];
        String signature = tokens[2];
        String encode = AESUtil.encrypt(header+"."+payload,password);
        if(signature.equals(encode)){
            return true;
        }
        return false;
    }

    public static String acquireToken(HttpServletRequest request){
        Cookie [] cookies = request.getCookies();
        if(null == cookies ){
            return null;
        }
        for (Cookie cookie : cookies){
            if(TOKEN.equals(cookie.getName())){
                logger.info("token cookie:{}",cookie.toString());
                return cookie.getValue();
            }
        }
        return null;
    }
}
