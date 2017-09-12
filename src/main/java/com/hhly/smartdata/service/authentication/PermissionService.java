package com.hhly.smartdata.service.authentication;

import com.google.common.collect.Sets;
import com.hhly.smartdata.mapper.authentication.MenuMapper;
import com.hhly.smartdata.mapper.authentication.PermissionMapper;
import com.hhly.smartdata.mapper.authentication.RoleMapper;
import com.hhly.smartdata.model.authentication.Menu;
import com.hhly.smartdata.model.authentication.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionService{
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;

    public List<Permission> searchPerms(Permission perm){
        return permissionMapper.searchPerms(perm);
    }

    public void delete(Permission condition){
        for(Permission perm : searchPerms(condition)){
            //删除对应菜单
            Menu menuCondition = new Menu();
            menuCondition.setPermission(perm.getPermission());
            menuMapper.delete(menuCondition);
            //删除对应角色权限分配
            roleMapper.delPerm(perm.getPermission());
            //删除权限
            permissionMapper.delete(perm);
        }
    }

    public void batchUpdatePerms(Integer funcId, List<Permission> perms){
        Set<String> existPerms = Sets.newHashSet();
        for(Permission perm : perms){
            existPerms.add(perm.getPermission());
        }
        //查询已存在权限
        Permission condition = new Permission();
        condition.setFunctionId(funcId);
        //循环对不存在的权限进行删除
        for(Permission perm : searchPerms(condition)){
            if(!existPerms.contains(perm.getPermission())){
                delete(perm);//删除关联内容
            }else{
                permissionMapper.delete(perm);//不删除关联内容
            }
        }

        //重新添加权限
        for(Permission permission : perms){
            permission.setFunctionId(funcId);
            save(permission);
        }
    }

    public void save(Permission permission){
        permissionMapper.insert(permission);
    }

    public List<Permission> queryByRole(List<Integer> roleIds){
        return permissionMapper.queryByRole(roleIds);
    }


    public List<Permission> getAll(){
        return permissionMapper.getAll();
    }
}
