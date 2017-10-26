package com.hhly.smartdata.service.authentication;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hhly.smartdata.mapper.authentication.RoleMapper;
import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.RolePermission;
import com.hhly.smartdata.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getRolesByUserId(int userId) throws Exception {
        return roleMapper.getRolesByUserId(userId);
    }

    public List<String> getPerms(List<Integer> roleIds) throws Exception {
        return roleMapper.getPerms(roleIds);
    }

    public List<Role> search(Role role, Page page) throws Exception {
        return roleMapper.search(role, page);
    }

    public void delete(Integer roleId) throws Exception {
        List<Integer> roleIdList = Lists.newArrayList();
        roleIdList.add(roleId);
        /*删除用户角色关联表*/
        roleMapper.delUsersByRole(roleIdList);
        /* 删除角色权限关联表*/
        roleMapper.delPermsByRole(roleIdList);
        /*删除角色表*/
        roleMapper.delete(roleId);
    }

    public void insert(Role role) throws Exception {
        roleMapper.insert(role);
        List<RolePermission> rolePermissionList = Lists.newArrayList();
        if (role.getPermissionIds().length() > 0) {
            if (role.getPermissionIds().indexOf(",") > 0) {
                String[] permissions = role.getPermissionIds().split(",");
                for (String permission : permissions) {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setPermission(permission.trim());
                    rolePermission.setRoleId(role.getId());
                    rolePermissionList.add(rolePermission);
                }
            } else {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(role.getId());
                rolePermission.setPermission(role.getPermissionIds().trim());
                rolePermissionList.add(rolePermission);
            }
        }
        /*插入角色权限关联表*/
        for (RolePermission rolePermission : rolePermissionList) {
            roleMapper.insertRolePermission(rolePermission);
        }
    }

    public void update(Role role) throws Exception {
        List<RolePermission> rolePermissionList = Lists.newArrayList();

        List<Integer> roleLists = Lists.newArrayList();
        roleLists.add(role.getId());
        /*根据角色删除权限（角色权限关联表）*/
        roleMapper.delPermsByRole(roleLists);

        if (role.getPermissionIds().length() > 0) {
            if (role.getPermissionIds().indexOf(",") > 0) {
                String[] permissions = role.getPermissionIds().split(",");
                for (String permission : permissions) {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(role.getId());
                    rolePermission.setPermission(permission.trim());
                    rolePermissionList.add(rolePermission);
                }
            } else {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(role.getId());
                rolePermission.setPermission(role.getPermissionIds().trim());
                rolePermissionList.add(rolePermission);
            }
        }
        /*修改角色*/
        roleMapper.update(role);
        /*更新角色权限*/
        for (RolePermission rolePermission : rolePermissionList) {
            roleMapper.insertRolePermission(rolePermission);
        }
    }

    public Role get(Integer id) throws Exception {
        return roleMapper.get(id);
    }

    public void allocRole(Integer userId, Integer[] roles) throws Exception {
        roleMapper.deleteUserRoles(userId);
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        for (Integer roleId : roles) {
            params.put("roleId", roleId);
            roleMapper.insertUserRole(params);
        }
    }

}
