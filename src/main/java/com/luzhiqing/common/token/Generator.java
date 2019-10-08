package com.luzhiqing.common.token;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 13:31
 */
public interface Generator {
    /**
     * 生成token
     * @param userInfo
     * @return
     */
    Token generate(UserInfo userInfo);
}
