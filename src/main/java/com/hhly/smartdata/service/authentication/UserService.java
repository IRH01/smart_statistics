package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.mapper.authentication.UserMapper;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vxbb
 */
@Service
public class UserService{
    @Autowired
    private UserMapper userRepository;

    public User getUserByUsername(String username){
        User user = new User();
        try{
            user = userRepository.getByUsername(username);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return user;
    }

    public List<User> searchUsers(User condition, Page page){
        return userRepository.searchUsers(condition, page);
    }

    public int update(User user){
        return userRepository.update(user);
    }

    public User getUser(Integer id){
        return userRepository.getUser(id);
    }

    public User saveUser(User user){
        userRepository.insert(user);
        return user;
    }

    public int delete(Integer id){
        return userRepository.delete(id);
    }
}
