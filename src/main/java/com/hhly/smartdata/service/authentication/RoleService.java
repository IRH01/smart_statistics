package com.hhly.smartdata.service.authentication;

import com.google.common.collect.Maps;
import com.hhly.smartdata.mapper.authentication.RoleMapper;
import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.RolePermission;
import com.hhly.smartdata.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleService{

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getRolesByUserId(int userId){
        return roleMapper.getRolesByUserId(userId);
    }

    public List<String> getPerms(List<Integer> roleIds){
        return roleMapper.getPerms(roleIds);
    }

    public List<Role> search(Role role, Page page){
        return roleMapper.search(role, page);
    }

    public void delete(Integer roleId){
        List<Integer> roleIdList = new ArrayList<Integer>();
        roleIdList.add(roleId);
        /*删除用户角色关联表*/
        roleMapper.delUsersByRole(roleIdList);
        /* 删除角色权限关联表*/
        delPermsByRole(roleIdList);
        /*删除角色表*/
        roleMapper.delete(roleId);
    }

    public void insert(Role role){
        roleMapper.insert(role);
        List<RolePermission> rolePermissionList = new ArrayList<RolePermission>();
        if(role.getPermissionIds().length() > 0){
            if(role.getPermissionIds().indexOf(",") > 0){
                String[] permissions = role.getPermissionIds().split(",");
                for(String permission : permissions){
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(role.getId());
                    rolePermission.setPermission(permission.trim());
                    rolePermissionList.add(rolePermission);
                }
            }else{
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(role.getId());
                rolePermission.setPermission(role.getPermissionIds().trim());
                rolePermissionList.add(rolePermission);
            }
        }
        /*插入角色权限关联表*/
        insertRolePermission(rolePermissionList);
    }

    public void update(Role role){
        List<RolePermission> rolePermissionList = new ArrayList<RolePermission>();

        List<Integer> roleLists = new ArrayList<Integer>();
        roleLists.add(role.getId());
        /*根据角色删除权限（角色权限关联表）*/
        roleMapper.delPermsByRole(roleLists);

        if(role.getPermissionIds().length() > 0){
            if(role.getPermissionIds().indexOf(",") > 0){
                String[] permissions = role.getPermissionIds().split(",");
                for(String permission : permissions){
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(role.getId());
                    rolePermission.setPermission(permission.trim());
                    rolePermissionList.add(rolePermission);
                }
            }else{
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(role.getId());
                rolePermission.setPermission(role.getPermissionIds().trim());
                rolePermissionList.add(rolePermission);
            }
        }
        /*修改角色*/
        roleMapper.update(role);
        /*更新角色权限*/
        insertRolePermission(rolePermissionList);

    }

    public int delPermsByRole(List<Integer> roleIds){
        return roleMapper.delPermsByRole(roleIds);
    }

    public void insertRolePermission(List<RolePermission> rolePermissionList){
        for(RolePermission rolePermission : rolePermissionList){
            roleMapper.insertRolePermission(rolePermission);
        }
    }

    public Role get(Integer id){
        return roleMapper.get(id);
    }

    public void allocRole(Integer userId, Integer[] roles){
        roleMapper.deleteUserRoles(userId);
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        for(Integer roleId : roles){
            params.put("roleId", roleId);
            roleMapper.insertUserRole(params);
        }
    }

}
