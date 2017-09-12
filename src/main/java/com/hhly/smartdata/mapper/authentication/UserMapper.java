package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.util.page.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper{

    int delete(Integer id);

    int insert(User record);

    User getUser(Integer id);

    User getByUsername(String username);

    int update(User record);

    List<User> searchUsers(User condition, Page page);
}