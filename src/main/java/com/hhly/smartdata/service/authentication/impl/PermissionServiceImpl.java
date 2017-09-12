package com.hhly.smartdata.service.authentication.impl;

import com.google.common.collect.Sets;
import com.hhly.smartdata.mapper.authentication.MenuRepository;
import com.hhly.smartdata.mapper.authentication.PermissionRepository;
import com.hhly.smartdata.mapper.authentication.RoleRepository;
import com.hhly.smartdata.model.authentication.Menu;
import com.hhly.smartdata.model.authentication.Permission;
import com.hhly.smartdata.service.authentication.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService{
    @Resource
    private PermissionRepository permissionRepository;
    @Resource
    private MenuRepository menuRepository;
    @Resource
    private RoleRepository roleRepository;


    @Override
    public List<Permission> searchPerms(Permission perm){
        return permissionRepository.searchPerms(perm);
    }

    @Override
    public void delete(Permission condition){
        for(Permission perm : searchPerms(condition)){
            //删除对应菜单
            Menu menuCondition = new Menu();
            menuCondition.setPermission(perm.getPermission());
            menuRepository.delete(menuCondition);
            //删除对应角色权限分配
            roleRepository.delPerm(perm.getPermission());
            //删除权限
            permissionRepository.delete(perm);
        }
    }


    @Override
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
                permissionRepository.delete(perm);//不删除关联内容
            }
        }

        //重新添加权限
        for(Permission permission : perms){
            permission.setFunctionId(funcId);
            save(permission);
        }
    }

    @Override
    public void save(Permission permission){
        permissionRepository.insert(permission);
    }

    public List<Permission> queryByRole(List<Integer> roleIds){
        return permissionRepository.queryByRole(roleIds);
    }


    public List<Permission> getAll(){
        return permissionRepository.getAll();
    }

}
