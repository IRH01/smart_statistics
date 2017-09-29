package com.hhly.smartdata.controller.authentication.view;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.Menu;
import com.hhly.smartdata.service.authentication.MenuService;
import com.hhly.smartdata.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Iritchie.ren on 2017/9/22.
 */
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
    public Result menuList(){
        List<Menu> menuList = null;
        try{
            menuList = menuService.getAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.success(menuList);
    }
}
