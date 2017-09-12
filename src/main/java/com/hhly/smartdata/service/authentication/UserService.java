package com.hhly.smartdata.service.authentication;

import java.util.List;

import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.util.page.Page;

/**
 * Created by vxbb
 */
public interface UserService {
    User getUserByUsername(String username);

    List<User> searchUsers(User condition,Page page);

    int update(User user);

    User getUser(Integer id);

    User saveUser(User user);
    
    int delete(Integer id);
}
