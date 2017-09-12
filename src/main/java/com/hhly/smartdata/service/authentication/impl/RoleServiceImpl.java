package com.hhly.smartdata.service.authentication.impl;

import com.google.common.collect.Maps;
import com.hhly.smartdata.mapper.authentication.RoleRepository;
import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.RolePermission;
import com.hhly.smartdata.service.authentication.RoleService;
import com.hhly.smartdata.util.page.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRolesByUserId(int userId){
        return roleRepository.getRolesByUserId(userId);
    }

    @Override
    public List<String> getPerms(List<Integer> roleIds){
        return roleRepository.getPerms(roleIds);
    }

    public List<Role> search(Role role, Page page){
        return roleRepository.search(role, page);
    }

    public void delete(Integer roleId){
        List<Integer> roleIdList = new ArrayList<Integer>();
        roleIdList.add(roleId);
        /*删除用户角色关联表*/
        roleRepository.delUsersByRole(roleIdList);
        /* 删除角色权限关联表*/
        delPermsByRole(roleIdList);
        /*删除角色表*/
        roleRepository.delete(roleId);
    }

    public void insert(Role role){
        /*插入角色*/
        roleRepository.insert(role);
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
        roleRepository.delPermsByRole(roleLists);

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
        roleRepository.update(role);
        /*更新角色权限*/
        insertRolePermission(rolePermissionList);

    }

    public int delPermsByRole(List<Integer> roleIds){
        return roleRepository.delPermsByRole(roleIds);
    }

    public void insertRolePermission(List<RolePermission> rolePermissionList){
        for(RolePermission rolePermission : rolePermissionList){
            roleRepository.insertRolePermission(rolePermission);
        }
    }

    public Role get(Integer id){
        return roleRepository.get(id);
    }

    @Override
    public void allocRole(Integer userId, Integer[] roles){
        roleRepository.deleteUserRoles(userId);
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        for(Integer roleId : roles){
            params.put("roleId", roleId);
            roleRepository.insertUserRole(params);
        }
    }
}
