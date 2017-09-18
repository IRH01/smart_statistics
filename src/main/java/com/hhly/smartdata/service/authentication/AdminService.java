package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.mapper.authentication.AdminMapper;
import com.hhly.smartdata.mapper.authentication.UserMapper;
import com.hhly.smartdata.model.authentication.Admin;
import com.hhly.smartdata.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService{

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    public List<Admin> searchAdmins(Admin condition, Page page){
        return adminMapper.searchAdmins(condition, page);
    }

    @Transactional
    public void save(Admin admin){
        userMapper.insert(admin);
        adminMapper.insert(admin);
    }

    @Transactional
    public void update(Admin admin){
        userMapper.update(admin);
        adminMapper.update(admin);
    }

    public Admin get(Integer id){
        return adminMapper.get(id);
    }

    @Transactional
    public int deleteByUserId(int userId){
        int result = userMapper.delete(userId);
        return result;
    }

}
