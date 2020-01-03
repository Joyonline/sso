package com.me.top.config.redis;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
public class RedisClusterProperties {
    /*
     * spring.redis.redis.nodes[0] = 127.0.0.1:7379
     * spring.redis.redis.nodes[1] = 127.0.0.1:7380
     * ...
     */
    List<String> nodes;

    JedisPoolConfig jedisPoolConfig;

}
