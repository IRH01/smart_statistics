package com.hhly.smartdata.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
@Transactional
public class RedisTest{

//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Resource(name = "redisTemplate")
//    private ListOperations<String, String> listOps;
//
//    @Test
//    public void addLink(String userId, URL url){
//        listOps.leftPush(userId, url.toExternalForm());
//    }

    @Test
    public void testJedis(){
        System.err.println("测试: ");
    }
}
