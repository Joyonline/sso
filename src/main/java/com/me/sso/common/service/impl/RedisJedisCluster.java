package com.me.sso.common.service.impl;

import com.me.sso.common.service.RedisService;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * jedis操作redis-cluster
 */
@Service
public class RedisJedisCluster implements RedisService {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public Boolean save(String key, String value,Integer expire) {
        return redisConnectionFactory.getClusterConnection().set(key.getBytes(), value.getBytes(),
                Expiration.seconds(expire), RedisStringCommands.SetOption.UPSERT);
    }

    @Override
    public Long del(String key) {
        return redisConnectionFactory.getClusterConnection().del(key.getBytes());
    }

    @Override
    public String query(String key){
        return new String(redisConnectionFactory.getClusterConnection().get(key.getBytes()));
    }
}
