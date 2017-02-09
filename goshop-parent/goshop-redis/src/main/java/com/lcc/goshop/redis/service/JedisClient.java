package com.lcc.goshop.redis.service;

import java.util.Set;

/**
 * Created by lcc on 2017/2/9.
 */
public interface JedisClient {

    /**
     * 获取缓存
     */
    String get(String key);

    byte[] get(byte[] key);

    /**
     * 设置缓存
     */
    String set(String key, String value);

    /**
     * 设置缓存
     *
     * @param key    主键
     * @param value  值
     * @param expire 过去时间
     */
    String set(String key, String value, int expire);

    String set(byte[] key, byte[] value);

    String set(byte[] key, byte[] value, int expire);

    /**
     * 哈希 获取缓存
     */
    String hget(String hkey, String key);

    /**
     * 哈希 设置缓存
     */
    long hset(String hkey, String key, String value);

    /**
     * 获取自增值
     */
    long incr(String key);

    /**
     * 设置有效期
     */
    long expire(String key, int second);

    /**
     * 获取有效期
     */
    long ttl(String key);

    /**
     * 删除缓存
     */
    long del(String key);
    long del(byte[] key);

    long hdel(String hkey,String key);

    Set<byte[]> keys(String pattern);

    /**
     * 刷新数据
     */
    void flushDB();

    Long dbSize();

}
