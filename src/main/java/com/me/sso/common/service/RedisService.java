package com.me.sso.common.service;

/**
 * redis
 */
public interface RedisService {

    Boolean save(String key,String value,Integer expire);

    Long del(String key);

    String query(String key);
}
