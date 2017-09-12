package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.model.authentication.Permission;

import java.util.List;

public interface PermissionService{
    List<Permission> searchPerms(Permission perm);


    /**
     * 删除对应权限--同时删除关联资源
     *
     * @param condition 删除条件
     */
    void delete(Permission condition);

    /**
     * 根据funcId，调整对应关联的权限
     *
     * @param funcId 功能id
     * @param perms  新的权限列表(可能包含部分旧的权限)
     */
    void batchUpdatePerms(Integer funcId, List<Permission> perms);

    void save(Permission permission);

    List<Permission> queryByRole(List<Integer> roleIds);

    List<Permission> getAll();
}
