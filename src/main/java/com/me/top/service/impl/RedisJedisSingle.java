package com.me.top.service.impl;

import com.me.top.service.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;


@Service
public class RedisJedisSingle implements RedisService {

    @Resource
    private JedisPool jedisPool;

    @Override
    public Boolean save(String key, String value, Integer expire) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(key, expire, value);
            return true;
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public Long del(String key) {
        return null;
    }

    @Override
    public String query(String key) {
        return null;
    }

    @Override
    public Long incrBy(String key, Long value) {
        return null;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } catch (Exception e) {
            return Long.parseLong(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
