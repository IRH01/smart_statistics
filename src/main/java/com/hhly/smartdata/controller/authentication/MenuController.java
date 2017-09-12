package com.hhly.smartdata.controller.authentication;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hhly.smartdata.model.authentication.Menu;
import com.hhly.smartdata.service.authentication.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/menu")
public class MenuController{

    @Autowired
    private MenuService menuService;

    @RequestMapping("/list.do")
    @RequiresPermissions("sys_menu_list")
    public String list(){
        return "sys/menu/list.main";
    }

    @RequestMapping("/menuList")
    public @ResponseBody
    String menuList(){
        List<Menu> menuList = menuService.getAll();
        return JSON.toJSONString(menuList);
    }

    @RequestMapping("/menuListByRole")
    public @ResponseBody
    String menuListByRole(HttpServletRequest req){
        String ids = req.getParameter("roleIds");
        String[] idList = ids.split(",");
        List<Integer> roleIds = Lists.newArrayList();
        for(String s : idList){
            Integer roleId = Integer.parseInt(s);
            roleIds.add(roleId);
        }
        List<Menu> menuList = menuService.getMenuListByRole(roleIds);
        return JSON.toJSONString(menuList);
    }

    @RequestMapping("/sort.do")
    @RequiresPermissions("sys_menu_sort")
    public @ResponseBody
    Map<String, Integer> sort(@RequestParam String menuTree){
        Map<String, Integer> result = menuService.sortAndUpdateMenus(JSON.parseArray(menuTree), 0);
        return result;
    }

    @RequestMapping("/update")
    @RequiresPermissions("!sys_menu_update")
    public @ResponseBody
    String update(@ModelAttribute Menu menu){
        menuService.update(menu);
        return "SUCCESS";
    }

    @RequestMapping("/menuDetail")
    @RequiresPermissions("sys_menu_list")
    public @ResponseBody
    Menu menuDetail(@RequestParam Integer id){
        return menuService.get(id);
    }
}
