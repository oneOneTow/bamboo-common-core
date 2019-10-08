package com.luzhiqing.common.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;



/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 12:19
 */
public class AESUtil {

    private final static Logger logger = LoggerFactory.getLogger(AESUtil.class);

    private final static String  AES = "AES";
    private final static String  CHARSET = "utf-8";
    private final static int  ENCODE_LENGTH = 128;


    /**
     * 加密
     *
     * @param content 待加密内容
     * @param password 加密密钥
     * @return 加密后的内容
     */
    public static String encrypt(String content, String password) {
        Assert.notNull(content, "加密信息不能为空");
        try {
            SecretKey secretKey = generateKey(password);
            byte[] secretKeyCode = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(secretKeyCode, AES);
            Cipher cipher = Cipher.getInstance(AES);
            byte[] byteContent = content.getBytes(CHARSET);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return new Base64().encodeToString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content 待解密字符串
     * @param password 对称密钥
     * @return 解密内容
     */
    public static String decrypt(String content, String password) {
        Assert.notNull(content, "解密信息不能为空");
        try {
            SecretKey secretKey = generateKey(password);
            byte[] secretKeyCode = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(secretKeyCode, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            return new String(result,CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SecretKey generateKey(String password) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(AES);
            keyGen.init(ENCODE_LENGTH, new SecureRandom(password.getBytes()));
            SecretKey secretKey = keyGen.generateKey();
            return secretKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


}
