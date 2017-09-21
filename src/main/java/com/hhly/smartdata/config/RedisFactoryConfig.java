package com.hhly.smartdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * Created by Iritchie.ren on 2017/9/21.
 */
@Configuration
@Profile("dev")
public class RedisFactoryConfig{

    @Value("${redis.cluster.nodes}")
    List<String> nodes;

    @Value("${redis.maxIdle}")
    private int maxIdle;

    @Value("${redis.maxWait}")
    private int maxWait;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.hostName}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.timeout}")
    private int timeout;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        RedisClusterConfiguration configuration = new RedisClusterConfiguration(nodes);
        return new JedisConnectionFactory(configuration);
    }
}
