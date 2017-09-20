package com.hhly.smartdata.service.authentication;

import com.alibaba.fastjson.JSONObject;
import com.hhly.smartdata.common.BaseTest;
import com.hhly.smartdata.model.authentication.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>九月 20, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
@Transactional
public class UserServiceTest extends BaseTest{
    @Autowired
    private UserService UserService;

    /**
     * Method: getUserByUsername(String username)
     */
    @Test
    public void testGetUserByUsername() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: update(User user)
     */
    @Test
    public void testUpdate() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getUser(Integer id)
     */
    @Test
    public void testGetUser() throws Exception{
        User user = this.UserService.getUser(141);
        System.err.println(JSONObject.toJSONString(user));
    }

    /**
     * Method: delete(Integer id)
     */
    @Test
    public void testDelete() throws Exception{
        //TODO: Test goes here...
    }


} 
