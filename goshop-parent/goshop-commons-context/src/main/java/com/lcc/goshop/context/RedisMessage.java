package com.lcc.goshop.context;

import com.lcc.goshop.commons.utils.SerializeUtils;
import com.lcc.goshop.redis.service.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lcc on 2017/2/9.
 */
public class RedisMessage implements MessageService {

    @Autowired
    JedisClient jedisClient;

    private String PREFIX = "MESSAGE_REDIS_";

    public void set(String key, MessageInfo value) {
        jedisClient.set(SerializeUtils.serialize(PREFIX + key), SerializeUtils.serialize(value));
    }

    public MessageInfo get(String key) {
        byte[] k = SerializeUtils.serialize(PREFIX+ key);
        byte[] rawValue = jedisClient.get(k);
        jedisClient.del(k);
        return (MessageInfo) SerializeUtils.deserialize(rawValue);
    }
}
