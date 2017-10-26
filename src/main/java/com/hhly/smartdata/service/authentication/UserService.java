package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.mapper.authentication.UserMapper;
import com.hhly.smartdata.model.authentication.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Iritchie.ren
 */
@Service
public class UserService{
    protected final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username) throws Exception{
        User user = new User();
        try{
            user = userMapper.getByUsername(username);
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }
        return user;
    }

    public int update(User user) throws Exception{
        return userMapper.update(user);
    }

    public User getUser(Integer id) throws Exception{
        return userMapper.getUser(id);
    }

    public int delete(Integer id) throws Exception{
        return userMapper.delete(id);
    }
}
