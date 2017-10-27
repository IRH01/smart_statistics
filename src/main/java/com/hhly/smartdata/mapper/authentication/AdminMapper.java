package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Admin;
import com.hhly.smartdata.util.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
    int delete(Integer id) throws Exception;

    int deleteByUserId(Integer userId) throws Exception;

    int insert(Admin record) throws Exception;

    Admin get(Integer id) throws Exception;

    int update(Admin record) throws Exception;

    List<Admin> searchAdmins(@Param("filter") Admin condition, @Param("page") Page page) throws Exception;
}