package com.hhly.smartdata.controller.authentication.view;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.Admin;
import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.AdminService;
import com.hhly.smartdata.service.authentication.RoleService;
import com.hhly.smartdata.service.authentication.UserService;
import com.hhly.smartdata.util.page.Page;
import com.hhly.smartdata.util.page.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by Iritchie.ren on 2017/9/22.
 */
@Controller
@RequestMapping("/admin/admin")
public class AdminController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminService adminService;

    @RequestMapping("/list")
    @RequiresPermissions("admin_admin_list")
    public ModelAndView list(@ModelAttribute Admin condition, @ModelAttribute Page page) {
        PageUtil.startPage(page);
        Map<String, Object> model = Maps.newHashMap();
        model.put("condition", condition);
        try {
            model.put("adminList", adminService.searchAdmins(condition, page));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        model.put("typeMap", Admin.Type.map());
        model.put("statusMap", User.statusMap);
        return new ModelAndView("system/admin/list", model);
    }

    /**
     * 启用用户
     */
    @RequestMapping("/on")
    @RequiresPermissions("admin_admin_on")
    public String on(@ModelAttribute User user) {
        user.setUserStatus(User.ON);
        try {
            userService.update(user);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return "redirect:list.do";
    }

    /**
     * 禁用用户
     */
    @RequestMapping("/off")
    @RequiresPermissions("admin_admin_off")
    public String off(@ModelAttribute User user) {
        user.setUserStatus(User.OFF);
        try {
            userService.update(user);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return "redirect:list.do";
    }

    /**
     * 分配角色
     */
    @RequestMapping("/showRoles")
    @RequiresPermissions("admin_admin_allocRole")
    public ModelAndView showRoles(@RequestParam Integer userId) {
        List<Role> hasRoles = null;
        try {
            hasRoles = roleService.getRolesByUserId(userId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        List<Role> allRoles = null;//null 即为查询全部
        try {
            allRoles = roleService.search(null, new Page(0, 0));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        for (Role role : allRoles) {
            if (hasRoles.contains(role)) {
                role.setOwned(true);
            } else {
                role.setOwned(false);
            }
        }
        Map<String, Object> model = Maps.newHashMap();
        model.put("roleList", allRoles);
        model.put("userId", userId);
        return new ModelAndView("system/admin/alloc_role", model);
    }

    /**
     * 分配角色
     */
    @RequestMapping("/allocRole")
    @RequiresPermissions("admin_admin_allocRole")
    public String allocRole(@RequestParam Integer userId, @RequestParam Integer[] role) {
        try {
            roleService.allocRole(userId, role);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return "redirect:list.do";
    }

    /**
     * 新增用户
     */
    @RequestMapping("/add")
    @RequiresPermissions("admin_admin_add")
    public ModelAndView add() {
        Map<String, Object> model = Maps.newHashMap();
        model.put("typeMap", Admin.Type.map());
        return new ModelAndView("system/admin/add", model);
    }

    /**
     * 保存用户
     */
    @RequestMapping("/save")
    @RequiresPermissions("admin_admin_add")
    public String save(@ModelAttribute Admin admin) {
        admin.setPasswd(new Md5Hash(admin.getPasswd()).toString());
        admin.setUserStatus(User.ON);
        admin.setUserType(User.USER_ADMIN);
        try {
            adminService.save(admin);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return "redirect:list.do";
    }

    /**
     * 编辑
     */
    @RequestMapping("/edit")
    @RequiresPermissions("admin_admin_edit")
    public ModelAndView edit(@RequestParam Integer id) {
        Map<String, Object> model = Maps.newHashMap();
        model.put("typeMap", Admin.Type.map());
        try {
            model.put("admin", adminService.get(id));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return new ModelAndView("system/admin/edit", model);
    }

    /**
     * 保存用户
     */
    @RequestMapping("/modify")
    @RequiresPermissions("admin_admin_edit")
    public String modify(@ModelAttribute Admin admin) {
        try {
            adminService.update(admin);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return "redirect:list.do";
    }
}
