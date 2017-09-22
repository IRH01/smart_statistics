package com.hhly.smartdata.controller.authentication;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
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
public class MenuController extends BaseController{

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
    public String menuList(){
        List<Menu> menuList = menuService.getAll();
        return JSONArray.toJSONString(menuList);
    }

//    @RequestMapping("/menuList")
//    @ResponseBody
//    public Result menuList(){
//        List<Menu> menuList = menuService.getAll();
//        return Result.success(menuList);
//    }

    @RequestMapping("/menuListByRole")
    @ResponseBody
    public String menuListByRole(HttpServletRequest req){
        String ids = req.getParameter("roleIds");
        String[] idList = ids.split(",");
        List<Integer> roleIds = Lists.newArrayList();
        for(String s : idList){
            Integer roleId = Integer.parseInt(s);
            roleIds.add(roleId);
        }
        List<Menu> menuList = menuService.getMenuListByRole(roleIds);
        return JSONArray.toJSONString(menuList);
    }

    @RequestMapping("/sort")
    @RequiresPermissions("sys_menu_sort")
    @ResponseBody
    public Map<String, Integer> sort(@RequestParam String menuTree){
        Map<String, Integer> result = menuService.sortAndUpdateMenus(JSONArray.parseArray(menuTree), 0);
        return result;
    }

    @RequestMapping("/update")
    @RequiresPermissions("!sys_menu_update")
    @ResponseBody
    public String update(@ModelAttribute Menu menu){
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
