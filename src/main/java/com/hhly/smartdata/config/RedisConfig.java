package com.hhly.smartdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * Created by Iritchie.ren on 2017/9/21.
 */
@Configuration
public class RedisConfig{

    @Value("${redis.maxIdle}")
    private int maxIdle;

    @Value("${redis.maxWait}")
    private int maxWait;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.model}")
    private String model;

    @Value("${redis.cluster.nodes}")
    private List<String> nodes;

    @Value("${redis.standalone.hostName}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.timeout}")
    private int timeout;

    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory, StringRedisSerializer stringRedisSerializer){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        return redisTemplate;
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer(){
        return new StringRedisSerializer();
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        if(model.equals("cluster")){
            RedisClusterConfiguration configuration = new RedisClusterConfiguration(nodes);
            return new JedisConnectionFactory(configuration);
        }else if(model.equals("standalone")){
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
            jedisConnectionFactory.setHostName(hostName);
            jedisConnectionFactory.setPort(port);
            jedisConnectionFactory.setPassword(password);
            jedisConnectionFactory.setUsePool(true);
            jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
            jedisConnectionFactory.setTimeout(timeout);
            return jedisConnectionFactory;
        }
        return null;
    }
}
