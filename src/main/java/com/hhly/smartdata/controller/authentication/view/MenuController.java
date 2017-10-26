package com.hhly.smartdata.controller.authentication.view;

import com.hhly.smartdata.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/9/22.
 */
@Controller
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {

    /**
     * 菜单列表查询
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys_menu_list")
    public String list() {
        return "system/menu/list";
    }
}
