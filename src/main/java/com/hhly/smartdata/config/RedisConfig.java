package com.hhly.smartdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
@Profile("dev")
public class RedisConfig{

    @Value("#{configProperties['redis.maxIdle']}")
    private Integer maxIdle;

    @Value("#{configProperties['redis.maxWait']}")
    private Integer maxWait;

    @Value("#{configProperties['redis.testOnBorrow']}")
    private boolean testOnBorrow;

    @Value("#{configProperties['redis.model']}")
    private String model;

    @Value("#{configProperties['redis.cluster.nodes']}")
    private List<String> nodes;

    @Value("#{configProperties['redis.standalone.hostName']}")
    private String hostName;

    @Value("#{configProperties['redis.port']}")
    private Integer port;

    @Value("#{configProperties['redis.password']}")
    private String password;

    @Value("#{configProperties['redis.timeout']}")
    private Integer timeout;

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
