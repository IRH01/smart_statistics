package com.hhly.smartdata.controller.authentication.view;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.Permission;
import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.service.authentication.FunctionService;
import com.hhly.smartdata.service.authentication.PermissionService;
import com.hhly.smartdata.service.authentication.RoleService;
import com.hhly.smartdata.util.page.Page;
import com.hhly.smartdata.util.page.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Iritchie.ren on 2017/9/22.
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

    @Autowired
    public RoleService roleService;
    @Autowired
    public PermissionService permissionService;
    @Autowired
    public FunctionService functionService;

    @RequestMapping("/list")
    @RequiresPermissions("sys_role_list")
    public ModelAndView list(@ModelAttribute Role role, @ModelAttribute Page page) {
        PageUtil.startPage(page);
        List<Role> roleList = null;
        try {
            roleList = roleService.search(role, page);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        Map<String, Object> model = Maps.newHashMap();
        model.put("roleList", roleList);
        model.put("role", role);
        return new ModelAndView("/system/role/list", model);
    }

    @RequestMapping("/{roleID}/delete")
    @RequiresPermissions("sys_role_delete")
    public String delete(@PathVariable("roleID") Integer roleId) {
        try {
            roleService.delete(roleId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return "redirect:../list.do";
    }

    @RequestMapping("/add")
    @RequiresPermissions("sys_role_add")
    public ModelAndView add() {
        Map<String, Object> model = Maps.newHashMap();
        return new ModelAndView("/system/role/add", model);
    }

    @RequestMapping("/{roleID}/preupdate")
    @RequiresPermissions("sys_role_update")
    public ModelAndView preUpdate(@PathVariable("roleID") Integer roleId, HttpServletRequest request) {
        Map<String, Object> model = Maps.newHashMap();
        Role role = null;
        try {
            role = roleService.get(roleId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        String permissions = "";
        String permissionIds = "";

        List<Integer> roleIdList = Lists.newArrayList();
        roleIdList.add(role.getId());
       /*拼接获取用户对应的权限*/
        List<Permission> permissionList = null;
        try {
            permissionList = permissionService.queryByRole(roleIdList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        for (Permission permission : permissionList) {
            permissions += permission.getPermissionName() + ",";
            permissionIds += permission.getPermission() + ",";

        }
       /*截取字符串*/
        if (permissions.lastIndexOf(",") > 0) {
            role.setPermission(permissions.substring(0, permissions.lastIndexOf(",")));
        } else {
            role.setPermission(permissions);
        }

        if (permissionIds.lastIndexOf(",") > 0) {
            role.setPermissionIds(permissionIds.substring(0, permissionIds.lastIndexOf(",")));
        } else {
            role.setPermissionIds(permissionIds);
        }

        model.put("role", role);
        return new ModelAndView("/sys/role/edit.main", model);
    }

    @RequestMapping("/save")
    @RequiresPermissions("sys_role_add")
    public String save(@ModelAttribute Role role) {
        if (role.getId() != null && role.getId().intValue() > 0) {
            try {
                roleService.update(role);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            return "redirect:list.do";
        } else {
            role.setCreateTime(new Date());
            try {
                roleService.insert(role);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            return "redirect:list.do";
        }
    }
}
