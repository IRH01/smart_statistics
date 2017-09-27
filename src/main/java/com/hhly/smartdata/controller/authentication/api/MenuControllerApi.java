package com.hhly.smartdata.controller.authentication.api;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.Menu;
import com.hhly.smartdata.service.authentication.MenuService;
import com.hhly.smartdata.util.Result;
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
public class MenuControllerApi extends BaseController{

    @Autowired
    private MenuService menuService;

    /**
     * 菜单列表查询
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys_menu_list")
    public String list(){
        return "system/menu/list";
    }

    @RequestMapping("/menuList")
    @ResponseBody
    public Result menuList(){
        List<Menu> menuList = null;
        try{
            menuList = menuService.getAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.success(menuList);
    }

    @RequestMapping("/menuListByRole")
    @ResponseBody
    public Result menuListByRole(HttpServletRequest req){
        String ids = req.getParameter("roleIds");
        String[] idList = ids.split(",");
        List<Integer> roleIds = Lists.newArrayList();
        for(String s : idList){
            Integer roleId = Integer.parseInt(s);
            roleIds.add(roleId);
        }
        List<Menu> menuList = null;
        try{
            menuList = menuService.getMenuListByRole(roleIds);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.success(menuList);
    }

    @RequestMapping("/sort")
    @RequiresPermissions("sys_menu_sort")
    @ResponseBody
    public Map<String, Integer> sort(@RequestParam String menuTree){
        Map<String, Integer> result = null;
        try{
            result = menuService.sortAndUpdateMenus(JSONArray.parseArray(menuTree), 0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/update")
    @RequiresPermissions("!sys_menu_update")
    @ResponseBody
    public String update(@ModelAttribute Menu menu){
        try{
            menuService.update(menu);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    @RequestMapping("/menuDetail")
    @RequiresPermissions("sys_menu_list")
    public @ResponseBody
    Menu menuDetail(@RequestParam Integer id){
        try{
            return menuService.get(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
