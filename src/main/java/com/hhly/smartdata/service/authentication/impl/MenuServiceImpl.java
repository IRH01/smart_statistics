package com.hhly.smartdata.service.authentication.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hhly.smartdata.model.authentication.Menu;
import com.hhly.smartdata.mapper.authentication.MenuRepository;
import com.hhly.smartdata.service.authentication.MenuService;
import com.hhly.smartdata.service.authentication.RoleService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private RoleService roleService;
    
    
    @Override
    public List<Menu> getMenuByPerms(List<String> perms) {
        return menuRepository.getMenuByPerms(perms);
    }

    @Override
    public List<Menu> getAll() {
        return searchMenus(null);
    }

    @Override
    public List<Menu> searchMenus(Menu menu) {
        return menuRepository.searchMenu(menu);
    }
    @Override
    public Menu get(Integer id){
        Menu condtion = new Menu();
        condtion.setId(id);
        List<Menu> menus = menuRepository.searchMenu(condtion);
        return menus.isEmpty()?null:menus.get(0);
    }

    @Override
    public void update(Menu menu) {
        menuRepository.update(menu);
    }

    public Map<String,Integer> updateMenuTree(JSONArray menuTree, Integer parentId){
        Map<String, Integer> ids = Maps.newHashMap();
        for (int i = 0; i < menuTree.size(); i++) {
            JSONObject menuJSON = menuTree.getJSONObject(i);
            Menu menu = new Menu();
            menu.setName(menuJSON.getString("name"));
            menu.setIndex(i + 1);
            menu.setParentId(parentId);
            if (menuJSON.containsKey("id")) {
                menu.setId(menuJSON.getInteger("id"));
                update(menu);
            } else {
                menuRepository.insert(menu);
            }
            ids.put(menuJSON.getString("tId"), menu.getId());
            if (menuJSON.containsKey("children")) {
                //遍历子集
                ids.putAll(updateMenuTree(menuJSON.getJSONArray("children"), menu.getId()));
            }
        }
        return ids;
    }

    @Override
    public Map<String,Integer> sortAndUpdateMenus(JSONArray menuTree, Integer parentId){
        //1:更新菜单树--树结构的menu
        Map<String, Integer> result = updateMenuTree(menuTree, parentId);

        //2:查询所有的菜单
        List<Menu> allMenu = getAll();

        //3:剔除1中的菜单id，即为不存在菜单id 进行删除
        Collection<Integer> existIds = result.values();
        for (Menu menu : allMenu) {
            if (!existIds.contains(menu.getId())) {
                menuRepository.delete(menu);
            }
        }
        return result;
    }

	@Override
	public List<Menu> getMenuListByRole( List<Integer> roleIds) {
		  List<String> perms = Lists.newArrayList();
		  perms = roleService.getPerms(roleIds);
		  System.out.println(perms.size());
		  List<Menu> menus = this.getMenuByPerms(perms);
		return menus;
	}
}
