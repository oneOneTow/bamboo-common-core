package com.luzhiqing.common.util;

import com.luzhiqing.common.exception.StringNullException;

public class StringUtils {
    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return org.apache.commons.lang.StringUtils.isBlank(str);
    }

    /**
     * 检查字符串是否为空
     *
     * @param str
     * @param messae
     */
    public static void isBlank(String str, String messae) {
        if (isBlank(str)) {
            throw new StringNullException();
        }
    }
}
