package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.RolePermission;
import com.hhly.smartdata.util.page.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper{

    List<Role> search(Role role, Page page);

    int delete(Integer id);

    int insert(Role record);

    Role get(Integer id);

    List<Role> getRolesByUserId(Integer userId);

    int update(Role record);

    List<String> getPerms(List<Integer> roleIds);

    int delPermsByRole(List<Integer> roleIds);

    int delPerm(String perm);

    void insertRolePermission(RolePermission rolePermission);

    void delUsersByRole(List<Integer> roleIds);

    void insertUserRole(Map<String, Object> params);

    void deleteUserRoles(int userId);

    Role getChannelRole(String roleName);

}