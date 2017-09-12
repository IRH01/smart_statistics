package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Admin;
import com.hhly.smartdata.util.page.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper{
    int delete(Integer id);

    int deleteByUserId(Integer userId);

    int insert(Admin record);

    Admin get(Integer id);

    int update(Admin record);

    List<Admin> searchAdmins(Admin condition, Page page);
}