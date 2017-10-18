package com.hhly.smartdata.utils;

import org.apache.shiro.dao.DataAccessException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
public class RedisTest{

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testValueOps(){
        this.redisTemplate.opsForValue().set("value", "test");
    }

    @Test
    public void testList(){
        this.redisTemplate.opsForList().leftPush("list", "test");
    }

    @Test
    public void testHash(){
        this.redisTemplate.opsForHash().put("hash", "互娱", "平台");
    }

    @Test
    public void testSet(){
        this.redisTemplate.opsForSet().add("set", "互娱");
    }

    @Test
    public void testZSet(){
        this.redisTemplate.opsForZSet().add("zset", "平台", 1.2);
    }

    @Test
    public void testGeo(){
        this.redisTemplate.opsForGeo().geoAdd("geo", new Point(123.1, 30.0), "member");
    }

    /**
     * 仅限单机模式下使用
     */
    @Test
    @Ignore
    public void testMulti(){
        List<Object> txResults = redisTemplate.execute(new SessionCallback<List<Object>>(){
            public List<Object> execute(RedisOperations operations) throws DataAccessException{
                operations.multi();
                operations.opsForSet().add("multi", "value1");
                return operations.exec();
            }
        });
        System.err.println("Number of items added to set: " + txResults.get(0));
    }


}
