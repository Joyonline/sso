package com.me.top.config.redis;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis.single.jedis")
@Data
public class RedisSingleProperties {
    private String host;
    private Integer port;
}
