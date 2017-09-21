package com.hhly.smartdata.controller.authentication;

import com.alibaba.fastjson.JSON;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.Permission;
import com.hhly.smartdata.service.authentication.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/sys/perm")
public class PermissionController extends BaseController{
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/searchPerms")
    @RequiresPermissions("sys_perm_search")
    @ResponseBody
    public List<Permission> searchPerms(@ModelAttribute Permission condition, HttpServletRequest req){
        List<Permission> perms = permissionService.searchPerms(condition);
        return perms;
    }

    @RequestMapping("/updatePerms")
    @RequiresPermissions("!sys_perm_update")
    @ResponseBody
    public String updatePerms(@RequestParam Integer funcId, @RequestParam String permsJson){
        List<Permission> perms = JSON.parseArray(permsJson, Permission.class);
        permissionService.batchUpdatePerms(funcId, perms);
        return "SUCCESS";
    }

}
