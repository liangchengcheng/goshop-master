package com.lcc.goshop.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

/**
 * Created by lcc on 2017/2/9.
 */
public class JedisClientClusterImpl implements JedisClient{

    @Autowired
    private JedisCluster jedisCluster;

    public String get(String key) {
        return jedisCluster.get(key);
    }

    public byte[] get(byte[] key) {
        return new byte[0];
    }

    public String set(String key, String value) {
        return jedisCluster.set(key,value);
    }

    public String set(String key, String value, int expire) {
        value = jedisCluster.set(key,value);
        if (expire != 0){
            jedisCluster.expire(key,expire);
        }
        return value;
    }

    public String set(byte[] key, byte[] value) {
        return null;
    }

    public String set(byte[] key, byte[] value, int expire) {
        return null;
    }

    public String hget(String hkey, String key) {
        return jedisCluster.hget(hkey,key);
    }

    public long hset(String hkey, String key, String value) {
        return jedisCluster.hset(hkey,key,value);
    }

    public long incr(String key) {
        return jedisCluster.incr(key);
    }

    public long expire(String key, int second) {
        return jedisCluster.expire(key,second);
    }

    public long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    public long del(String key) {
        return jedisCluster.del(key);
    }

    public long del(byte[] key) {
        return 0;
    }

    public long hdel(String hkey, String key) {
        return jedisCluster.hdel(hkey,key);
    }

    public Set<byte[]> keys(String pattern) {
        return null;
    }

    public void flushDB() {

    }

    public Long dbSize() {
        return null;
    }
}
