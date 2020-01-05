package com.me.top.service.impl;

import com.me.top.service.RedisService;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * jedis操作redis-redis
 */
@Service
public class RedisJedisCluster implements RedisService {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public Boolean save(String key, String value,Integer expire) {
        redisConnectionFactory.getClusterConnection().close();
        return redisConnectionFactory.getClusterConnection().set(key.getBytes(), value.getBytes(),
                Expiration.seconds(expire), RedisStringCommands.SetOption.UPSERT);
    }

    @Override
    public Long del(String key) {
        return redisConnectionFactory.getClusterConnection().del(key.getBytes());
    }

    @Override
    public String query(String key){
        byte[] bytes = redisConnectionFactory.getClusterConnection().get(key.getBytes());
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return new String(bytes);
    }

    @Override
    public Long incrBy(String key, Long value) {
        return redisConnectionFactory.getClusterConnection().incrBy(key.getBytes(), value);
    }

    @Override
    public Long incr(String key) {
        return redisConnectionFactory.getClusterConnection().incr(key.getBytes());
    }
}
