package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.RolePermission;
import com.hhly.smartdata.util.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {

    List<Role> search(@Param("filter") Role role, @Param("page") Page page) throws Exception;

    int delete(Integer id) throws Exception;

    int insert(Role record) throws Exception;

    Role get(Integer id) throws Exception;

    List<Role> getRolesByUserId(Integer userId) throws Exception;

    int update(Role record) throws Exception;

    List<String> getPerms(List<Integer> roleIds) throws Exception;

    int delPermsByRole(List<Integer> roleIds) throws Exception;

    int delPerm(String perm) throws Exception;

    void insertRolePermission(RolePermission rolePermission) throws Exception;

    void delUsersByRole(List<Integer> roleIds) throws Exception;

    void insertUserRole(Map<String, Object> params) throws Exception;

    void deleteUserRoles(int userId) throws Exception;

    Role getChannelRole(String roleName) throws Exception;

}