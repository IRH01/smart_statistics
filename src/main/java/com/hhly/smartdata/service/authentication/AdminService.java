package com.hhly.smartdata.service.authentication;

import java.util.List;

import com.hhly.smartdata.model.authentication.Admin;
import com.hhly.smartdata.util.page.Page;

public interface AdminService {

    public List<Admin> searchAdmins(Admin condition,Page page);

    public void save(Admin admin);

    public int deleteByUserId(int userId);
    
    void update(Admin admin);
    Admin get(Integer id);

}
