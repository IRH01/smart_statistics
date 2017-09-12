package com.hhly.smartdata.service.authentication.impl;

import com.hhly.smartdata.mapper.authentication.UserRepository;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.UserService;
import com.hhly.smartdata.util.page.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username){
        User user = new User();
        try{
            user = userRepository.getByUsername(username);
        }catch(Exception e){
            // TODO: handle exception
            e.printStackTrace();
            System.out.println(e);
        }
        return user;
    }

    @Override
    public List<User> searchUsers(User condition, Page page){
        return userRepository.searchUsers(condition, page);
    }

    @Override
    public int update(User user){
        return userRepository.update(user);
    }

    @Override
    public User getUser(Integer id){
        return userRepository.getUser(id);
    }

    @Override
    public User saveUser(User user){
        userRepository.insert(user);
        return user;
    }

    @Override
    public int delete(Integer id){
        // TODO Auto-generated method stub
        return userRepository.delete(id);
    }
}
