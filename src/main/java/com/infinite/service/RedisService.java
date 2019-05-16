package com.infinite.service;

public interface RedisService {
    /**
     * 设置缓存
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 设置缓存
     * @param key
     * @param value
     * @param seconds 缓存有效期
     */
    void set(String key, Object value, int seconds);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除缓存
     * @param key
     */
    void delete(String key);
}