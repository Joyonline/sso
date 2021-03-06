package com.me.top.service;

/**
 * redis
 */
public interface RedisService {

    Boolean save(String key,String value,Integer expire);

    Long del(String key);

    String query(String key);

    Long incrBy(String key,Long value);

    Long incr(String key);
}
