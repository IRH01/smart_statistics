package com.hhly.smartdata.controller.authentication.api;

import com.google.common.collect.Lists;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.*;
import com.hhly.smartdata.service.authentication.FunctionService;
import com.hhly.smartdata.service.authentication.PermissionService;
import com.hhly.smartdata.service.authentication.RoleService;
import com.hhly.smartdata.util.Result;
import com.hhly.smartdata.util.SysConstant;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/sys/role")
public class RoleControllerApi extends BaseController{
    @Autowired
    public RoleService roleService;
    @Autowired
    public PermissionService permissionService;
    @Autowired
    public FunctionService functionService;

    @RequestMapping("/{random}/tree")
    @RequiresPermissions(value = {"sys_role_add", "!sys_func_update"}, logical = Logical.OR)
    public Result tree(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        return Result.success(nodeList(user.getUserId()));
    }

    /*获取系统的功能权限树*/
    private List<Node> nodeList(int userId){
        List<Role> roleList = null;
        try{
            roleList = roleService.getRolesByUserId(userId);
        }catch(Exception e){
            e.printStackTrace();
        }
        List<Integer> idList = Lists.newArrayList();
        List<Permission> permissionList = null;
        List<Function> functionList = null;
        List<Node> nodeList = Lists.newArrayList();

        for(Role role : roleList){
            idList.add(role.getId());
        }

        if(idList.size() > 0){
            try{
                permissionList = permissionService.getAll();
            }catch(Exception e){
                e.printStackTrace();
            }
            try{
                functionList = functionService.getAll();
            }catch(Exception e){
                e.printStackTrace();
            }

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
