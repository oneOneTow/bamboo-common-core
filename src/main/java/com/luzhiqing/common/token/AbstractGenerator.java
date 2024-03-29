package com.luzhiqing.common.token;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 13:33
 */
public abstract class AbstractGenerator implements Generator {

    protected Properties properties;

    @Override
    public abstract Token generate(UserInfo userInfo);
}
