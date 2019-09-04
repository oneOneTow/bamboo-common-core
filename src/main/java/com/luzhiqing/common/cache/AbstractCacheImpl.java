package com.luzhiqing.common.cache;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/8/30 19:33
 */
public abstract class AbstractCacheImpl<T> implements Cache {
    protected Object cache;
    protected String appId;
    protected ThreadLocal<String> cacheKey;
    protected ThreadLocal<Long> expireTime;

    protected String getAppId(){
        return "bamboo";
    }

    @Override
    public Long getExpireTime() {
        Long localValue =  expireTime.get();
        expireTime.remove();
        return localValue;
    }

    @Override
    public String getCacheKey() {
        String localValue =  cacheKey.get();
        cacheKey.remove();
        return localValue;
    }

    @Override
    public void setCacheKey(String cacheKey) {
        this.cacheKey.remove();
        this.cacheKey.set(cacheKey);
    }
    @Override
    public void setExpireTime(Long expireTime) {
        this.expireTime.remove();
        this.expireTime.set(expireTime);
    }
}
