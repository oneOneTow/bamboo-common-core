package com.luzhiqing.common.cache;


import java.util.List;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/8/30 19:20
 */
public interface Cache {
    /**
     * 缓存数据
     *
     * @return
     */
    <T> T getCache(Callback<T> callback);

    /**
     * 丢弃数据
     *
     * @param nameSpace
     * @param key
     * @return
     */
    boolean abandon(String nameSpace, String key);

    /**
     * 丢弃数据
     *
     * @param nameSpace
     * @param keys
     * @return
     */
    boolean abandon(String nameSpace, List<String> keys);

    /**
     * 丢弃数据
     *
     * @param nameSpace
     * @return
     */
    boolean abandon(String nameSpace);


    Long getExpireTime();

    String getCacheKey();

    void setCacheKey(String cacheKey);

    void setExpireTime(Long expireTime);

}
