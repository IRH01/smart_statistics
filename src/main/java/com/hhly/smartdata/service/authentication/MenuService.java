package com.hhly.smartdata.service.authentication;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hhly.smartdata.mapper.authentication.MenuMapper;
import com.hhly.smartdata.mapper.authentication.RoleMapper;
import com.hhly.smartdata.model.authentication.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class MenuService{
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;

    public List<Menu> getMenuByPerms(List<String> perms){
        return menuMapper.getMenuByPerms(perms);
    }

    public List<Menu> getAll(){
        return searchMenus(null);
    }

    public List<Menu> searchMenus(Menu menu){
        return menuMapper.searchMenu(menu);
    }

    public Menu get(Integer id){
        Menu condition = new Menu();
        condition.setId(id);
        List<Menu> menus = menuMapper.searchMenu(condition);
        return menus.isEmpty() ? null : menus.get(0);
    }

    public void update(Menu menu){
        menuMapper.update(menu);
    }

    public Map<String, Integer> updateMenuTree(JSONArray menuTree, Integer parentId){
        Map<String, Integer> ids = Maps.newHashMap();
        for(int i = 0; i < menuTree.size(); i++){
            JSONObject menuJSON = menuTree.getJSONObject(i);
            Menu menu = new Menu();
            menu.setName(menuJSON.getString("name"));
            menu.setIndex(i + 1);
            menu.setParentId(parentId);
            if(menuJSON.containsKey("id")){
                menu.setId(menuJSON.getInteger("id"));
                update(menu);
            }else{
                menuMapper.insert(menu);
            }
            ids.put(menuJSON.getString("tId"), menu.getId());
            if(menuJSON.containsKey("children")){
                //遍历子集
                ids.putAll(updateMenuTree(menuJSON.getJSONArray("children"), menu.getId()));
            }
        }
        return ids;
    }

    public Map<String, Integer> sortAndUpdateMenus(JSONArray menuTree, Integer parentId){
        //1:更新菜单树--树结构的menu
        Map<String, Integer> result = updateMenuTree(menuTree, parentId);

        //2:查询所有的菜单
        List<Menu> allMenu = getAll();

        //3:剔除1中的菜单id，即为不存在菜单id 进行删除
        Collection<Integer> existIds = result.values();
        for(Menu menu : allMenu){
            if(!existIds.contains(menu.getId())){
                menuMapper.delete(menu);
            }
        }
        return result;
    }

    public List<Menu> getMenuListByRole(List<Integer> roleIds){
        List<String> perms = roleMapper.getPerms(roleIds);
        System.out.println(perms.size());
        List<Menu> menus = this.getMenuByPerms(perms);
        return menus;
    }
}
