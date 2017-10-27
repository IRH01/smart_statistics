package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    int delete(Menu condition) throws Exception;

    int delById(Integer id) throws Exception;

    List<Menu> getMenuByPerms(List<String> permIds) throws Exception;

    int insert(Menu record) throws Exception;

    List<Menu> searchMenu(Menu condition) throws Exception;

    int update(Menu record) throws Exception;
}