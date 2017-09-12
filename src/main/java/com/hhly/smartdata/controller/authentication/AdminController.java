package com.hhly.smartdata.controller.authentication;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.hhly.smartdata.model.authentication.Admin;
import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.AdminService;
import com.hhly.smartdata.service.authentication.RoleService;
import com.hhly.smartdata.service.authentication.UserService;
import com.hhly.smartdata.service.game.operative.ChannelPlatformService;
import com.hhly.smartdata.util.page.Page;
import com.hhly.smartdata.util.page.PageUtil;

@Controller
@RequestMapping("/admin/admin")
public class AdminController {
	private final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private AdminService adminService;
    @Resource 
    private ChannelPlatformService channelPlatformService;

    @RequestMapping("/list")
    @RequiresPermissions("admin_admin_list")
    public ModelAndView list(@ModelAttribute Admin condition,@ModelAttribute Page page){
        PageUtil.startPage(page);
        Map<String,Object> model = Maps.newHashMap();
        model.put("condition",condition);
        model.put("adminList", adminService.searchAdmins(condition,page));
        model.put("typeMap",Admin.Type.map());
        model.put("statusMap", User.statusMap);
        return new ModelAndView("admin/admin/list.main",model);
    }

    /**
     * 启用用户
     */
    @RequestMapping("/on")
    @RequiresPermissions("admin_admin_on")
    public String on(@ModelAttribute User user){
        user.setUserStatus(User.ON);
        userService.update(user);
        return "redirect:list.do";
    }

    /**
     * 禁用用户
     */
    @RequestMapping("/off")
    @RequiresPermissions("admin_admin_off")
    public String off(@ModelAttribute User user) {
        user.setUserStatus(User.OFF);
        userService.update(user);
        return "redirect:list.do";
    }

    /**
     * 初始化密码
     */
    @RequestMapping("/initPwd")
    @RequiresPermissions("admin_admin_initPwd")
    public @ResponseBody String initPwd(@ModelAttribute User user) {
        user.setPassword(new Md5Hash("123456").toString());
        userService.update(user);
        return "SUCCESS";
    }

    /**
     * 分配角色
     */
    @RequestMapping("/showRoles")
    @RequiresPermissions("admin_admin_allocRole")
    public ModelAndView showRoles(@RequestParam Integer userId) {
        List<Role> hasRoles = roleService.getRolesByUserId(userId);
        List<Role> allRoles = roleService.search(null,new Page(0, 0));//null 即为查询全部
        for(Role role:allRoles){
            if(hasRoles.contains(role)){
                role.setOwned(true);
            }else{
                role.setOwned(false);
            }
        }
        Map<String, Object> model = Maps.newHashMap();
        model.put("roleList", allRoles);
        model.put("userId", userId);
        return new ModelAndView("admin/admin/alloc_role.main", model);
    }
    /**
     * 分配角色
     */
    @RequestMapping("/allocRole")
    @RequiresPermissions("admin_admin_allocRole")
    public String allocRole(@RequestParam Integer userId,@RequestParam Integer[] role) {
        roleService.allocRole(userId,role);
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
        return new ModelAndView("admin/admin/add.main",model);
    }

    /**
     * 保存用户
     */
    @RequestMapping("/save")
    @RequiresPermissions("admin_admin_add")
    public String save(@ModelAttribute Admin admin) {
        admin.setPassword(new Md5Hash(admin.getPassword()).toString());
        admin.setUserStatus(User.ON);
        admin.setUserType(User.USER_ADMIN);
        adminService.save(admin);
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
        model.put("admin",adminService.get(id));
        return new ModelAndView("admin/admin/edit.main", model);
    }

    /**
     * 保存用户
     */
    @RequestMapping("/modify")
    @RequiresPermissions("admin_admin_edit")
    public String modify(@ModelAttribute Admin admin) {
        adminService.update(admin);
        return "redirect:list.do";
    }
    
    /**
     * 删除用户
     */
    @RequestMapping("/del")
    public @ResponseBody String deleteUser(@RequestParam Integer userId) {
        if(0 < adminService.deleteByUserId(userId)){
        	return "SUCCESS";
        }
        return "FAIL";
    }
    
    @RequestMapping("/{random}/tree")
    @RequiresPermissions(value={"admin_admin_add","admin_admin_edit"},logical=Logical.OR)
    public @ResponseBody List<Node> tree(HttpServletRequest request ,HttpServletResponse response){
       Integer userId = null;
       try {
    	   userId = Integer.valueOf(request.getParameter("userId"));
		} catch (Exception e) {
			LOGGER.info("获取userId失败:" + e);
		}
       return  channelPlatformService.getTreeNode(userId);
    }
}
