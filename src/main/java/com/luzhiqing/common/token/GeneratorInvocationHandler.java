package com.luzhiqing.common.token;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 14:06
 */
public class GeneratorInvocationHandler implements InvocationHandler {

    private Object target;

    public GeneratorInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.target,args);
    }
}
