package com.hhly.smartdata.service.authentication;

import com.alibaba.fastjson.JSONArray;
import com.hhly.smartdata.model.authentication.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService{
    List<Menu> getMenuByPerms(List<String> perms);

    List<Menu> getAll();

    List<Menu> searchMenus(Menu menu);

    void update(Menu menu);

    Menu get(Integer id);

    Map<String, Integer> sortAndUpdateMenus(JSONArray menuTree, Integer parentId);

    List<Menu> getMenuListByRole(List<Integer> roleIds);
}
