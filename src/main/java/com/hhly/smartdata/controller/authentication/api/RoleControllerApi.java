package com.hhly.smartdata.controller.authentication.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hhly.smartdata.model.authentication.*;
import com.hhly.smartdata.service.authentication.FunctionService;
import com.hhly.smartdata.service.authentication.PermissionService;
import com.hhly.smartdata.service.authentication.RoleService;
import com.hhly.smartdata.util.Result;
import com.hhly.smartdata.util.SysConstant;
import com.hhly.smartdata.util.page.Page;
import com.hhly.smartdata.util.page.PageUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/role")
public class RoleControllerApi extends BaseControllerApi{
    @Autowired
    public RoleService roleService;
    @Autowired
    public PermissionService permissionService;
    @Autowired
    public FunctionService functionService;

    @RequestMapping("/list")
    @RequiresPermissions("sys_role_list")
    public ModelAndView list(@ModelAttribute Role role, @ModelAttribute Page page){
        PageUtil.startPage(page);
        List<Role> roleList = roleService.search(role, page);
        Map<String, Object> model = Maps.newHashMap();
        model.put("roleList", roleList);
        model.put("role", role);
        return new ModelAndView("/system/role/list", model);
    }

    @RequestMapping("/{roleID}/delete")
    @RequiresPermissions("sys_role_delete")
    public String delete(@PathVariable("roleID") Integer roleId){
        roleService.delete(roleId);
        return "redirect:../list.do";
    }

    @RequestMapping("/add")
    @RequiresPermissions("sys_role_add")
    public ModelAndView add(){
        Map<String, Object> model = Maps.newHashMap();
        return new ModelAndView("/system/role/add", model);
    }

    @RequestMapping("/{roleID}/preupdate")
    @RequiresPermissions("sys_role_update")
    public ModelAndView preUpdate(@PathVariable("roleID") Integer roleId, HttpServletRequest request){
        Map<String, Object> model = Maps.newHashMap();
        Role role = roleService.get(roleId);
        String permissions = "";
        String permissionIds = "";

        List<Integer> roleIdList = Lists.newArrayList();
        roleIdList.add(role.getId());
       /*拼接获取用户对应的权限*/
        List<Permission> permissionList = permissionService.queryByRole(roleIdList);
        for(Permission permission : permissionList){
            permissions += permission.getName() + ",";
            permissionIds += permission.getPermission() + ",";

        }
       /*截取字符串*/
        if(permissions.lastIndexOf(",") > 0){
            role.setPermission(permissions.substring(0, permissions.lastIndexOf(",")));
        }else{
            role.setPermission(permissions);
        }

        if(permissionIds.lastIndexOf(",") > 0){
            role.setPermissionIds(permissionIds.substring(0, permissionIds.lastIndexOf(",")));
        }else{
            role.setPermissionIds(permissionIds);
        }

        model.put("role", role);
        return new ModelAndView("/sys/role/edit.main", model);
    }

    @RequestMapping("/save")
    @RequiresPermissions("sys_role_add")
    public String save(@ModelAttribute Role role){
        if(role.getId() != null && role.getId().intValue() > 0){
            roleService.update(role);
            return "redirect:list.do";
        }else{
            role.setCreateTime(new Date());
            roleService.insert(role);
            return "redirect:list.do";
        }
    }


    @RequestMapping("/{random}/tree")
    @RequiresPermissions(value = {"sys_role_add", "!sys_func_update"}, logical = Logical.OR)
    @ResponseBody
    public Result tree(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        return Result.success(nodeList(user.getUserId()));
    }

    /*获取系统的功能权限树*/
    private List<Node> nodeList(int userId){
        List<Role> roleList = roleService.getRolesByUserId(userId);
        List<Integer> idList = Lists.newArrayList();
        List<Permission> permissionList;
        List<Function> functionList;
        List<Node> nodeList = Lists.newArrayList();

        for(Role role : roleList){
            idList.add(role.getId());
        }

        if(idList.size() > 0){
            permissionList = permissionService.getAll();
            functionList = functionService.getAll();

            if(functionList.size() > 0){
                for(Function function : functionList){
                    Node node = new Node();
                    node.setLeaf(false);
                    if(function.getParentId() == 0){
                        node.setId(function.getId().toString());
                        node.setName(function.getName());
                        node.setpId("0");
                    }else{
                        node.setId(function.getId().toString());
                        node.setName(function.getName());
                        node.setpId(function.getParentId().toString());
                    }
                    nodeList.add(node);
                }
            }
            if(permissionList.size() > 0){
                for(Permission permission : permissionList){
                    Node node = new Node();
                    node.setLeaf(true);
                    node.setId(permission.getPermission());
                    node.setpId(permission.getFunctionId().toString());
                    node.setName(permission.getName());
                    nodeList.add(node);
                }
            }
        }else{
            return null;
        }
        return nodeList;
    }
}
