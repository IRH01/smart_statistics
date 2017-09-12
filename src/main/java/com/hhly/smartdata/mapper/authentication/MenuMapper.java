package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper{

    int delete(Menu condition);

    int delById(Integer id);

    List<Menu> getMenuByPerms(List<String> permIds);

    int insert(Menu record);

    List<Menu> searchMenu(Menu condition);

    int update(Menu record);
}