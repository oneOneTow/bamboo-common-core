package com.luzhiqing.common.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/9 19:02
 */
public class MD5Util {

    /**
     * md5加密
     *
     * @param content
     * @return
     */
    public static String encode(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] res = md.digest(content.getBytes("utf-8"));
            return Base64.encodeBase64String(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
