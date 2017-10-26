package com.hhly.smartdata.controller.authentication.api;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.Menu;
import com.hhly.smartdata.service.authentication.MenuService;
import com.hhly.smartdata.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/menu")
public class MenuControllerApi extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menuList")
    public Result menuList() {
        List<Menu> menuList = null;
        try {
            menuList = menuService.getAll();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return Result.success(menuList);
    }

    @RequestMapping("/menuListByRole")
    public Result menuListByRole(HttpServletRequest req) {
        String ids = req.getParameter("roleIds");
        String[] idList = ids.split(",");
        List<Integer> roleIds = Lists.newArrayList();
        for (String s : idList) {
            Integer roleId = Integer.parseInt(s);
            roleIds.add(roleId);
        }
        List<Menu> menuList = null;
        try {
            menuList = menuService.getMenuListByRole(roleIds);
        } catch (Exception e) {
            LOGGER.error("异常！");
        }
        return Result.success(menuList);
    }

    @RequestMapping("/sort")
    @RequiresPermissions("sys_menu_sort")
    public Result sort(@RequestParam String menuTree) {
        Map<String, Integer> result = null;
        try {
            result = menuService.sortAndUpdateMenus(JSONArray.parseArray(menuTree), 0);
        } catch (Exception e) {
            LOGGER.error("异常！");
        }
        return Result.success(result);
    }

    @RequestMapping("/update")
    @RequiresPermissions("!sys_menu_update")
    public Result update(@ModelAttribute Menu menu) {
        try {
            menuService.update(menu);
        } catch (Exception e) {
            LOGGER.error("异常！");
        }
        return Result.success();
    }

    @RequestMapping("/menuDetail")
    @RequiresPermissions("sys_menu_list")
    public Result menuDetail(@RequestParam Integer id) {
        try {
            return Result.success(menuService.get(id));
        } catch (Exception e) {
            LOGGER.error("异常！");
        }
        return Result.fail();
    }
}
