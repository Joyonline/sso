package com.me.top.config;


import com.me.top.config.redis.RedisClusterProperties;
import com.me.top.config.redis.RedisSingleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class AppConfig {

    @Autowired
    RedisClusterProperties clusterProperties;
    @Autowired
    RedisSingleProperties jedisPoolProperties;

    @Bean
    public RedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory(new RedisClusterConfiguration(clusterProperties.getNodes()),
                clusterProperties.getJedisPoolConfig());
    }

    /**
     * jedisPool
     * @return
     */
    @Bean
    public JedisPool jedisPool(){
        return new JedisPool(new JedisPoolConfig(),jedisPoolProperties.getHost(),jedisPoolProperties.getPort());
    }

    /**
     * StringRedisTemplate
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate(connectionFactory());
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        return stringRedisTemplate;
    }

}
