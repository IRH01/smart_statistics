package com.hhly.smartdata.service.authentication;

import java.util.List;

import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.RolePermission;
import com.hhly.smartdata.util.page.Page;

public interface RoleService {

    List<Role> getRolesByUserId(int userId);

    List<String> getPerms(List<Integer> roleIds);

    List<Role> search(Role role,Page page);

    void delete(Integer  roleId);

    void insert(Role role );

    void update(Role role );

    int delPermsByRole(List<Integer> roleIds );

    void insertRolePermission(List<RolePermission> rolePermissionList);

    Role get(Integer id);

    void allocRole(Integer userId,Integer[] roles);



}
