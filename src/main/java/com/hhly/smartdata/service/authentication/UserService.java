package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.mapper.authentication.UserMapper;
import com.hhly.smartdata.model.authentication.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Iritchie.ren
 */
@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username){
        User user = new User();
        try{
            user = userMapper.getByUsername(username);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return user;
    }

    public int update(User user){
        return userMapper.update(user);
    }

    public User getUser(Integer id){
        return userMapper.getUser(id);
    }

    public int delete(Integer id){
        return userMapper.delete(id);
    }
}
