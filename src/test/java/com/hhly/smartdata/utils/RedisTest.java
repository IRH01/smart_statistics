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

import java.util.List;@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
public class RedisTest{

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testValueOps(){
        for(int i = 0; i < 2100000000; i++){
            this.redisTemplate.opsForValue().set("value" + 1, i + "");
        }
    }

    @Test
    public void testList(){
        for(int i = 0; i < 2100000000; i++){
            this.redisTemplate.opsForList().leftPush("list", i + "");
        }
    }

    @Test
    public void testHash(){
        for(int i = 0; i < 2100000000; i++){
            this.redisTemplate.opsForHash().put("hash" + (i % 5), "互娱" + i, "平台" + i);
        }
    }

    @Test
    public void testSet(){
        for(int i = 0; i < 2100000000; i++){
            this.redisTemplate.opsForSet().add("set", "互娱" + i);
        }
    }

    @Test
    public void testZSet(){
        for(int i = 0; i < 2100000000; i++){
            this.redisTemplate.opsForZSet().add("zset" + i, "平台", 1.2 + i);
        }
    }

    @Test
    public void testGeo(){
        for(int i = 0; i < 2100000000; i++){
            this.redisTemplate.opsForGeo().geoAdd("geo" + i, new Point(123.1, 30.0), "member");
        }
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
