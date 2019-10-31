package com.luzhiqing.common.token;

import com.luzhiqing.common.helper.SpringContextHelper;


import java.lang.reflect.Proxy;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/2 13:54
 */
public class GeneratorFactory {

    private Generator generator;

    private Properties properties;

    public GeneratorFactory() {
    }

    public GeneratorFactory(Generator generator) {
        this.generator = generator;
    }

    private void initGenerator(){
        if(null == properties ){
            this.properties = SpringContextHelper.getBean(Properties.class);
        }
        if(TokenType.JWT.equals(properties.getType())){
            this.generator = new JWTGenerator();
        }
        if(TokenType.CUSTOM.equals(properties.getType())){
            this.generator = new CustomGenerator(this.properties);
        }
    }

    /**
     * 创建token生成器
     * @return
     */
    public Generator produce(){
        if(null == this.generator){
            this.initGenerator();
        }
        ClassLoader classLoader = this.generator.getClass().getClassLoader();
        Class[] classes = this.generator.getClass().getInterfaces();
        Generator proxy = (Generator) Proxy.newProxyInstance(classLoader, classes, new GeneratorInvocationHandler(this.generator));
        return proxy;
    }


    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
