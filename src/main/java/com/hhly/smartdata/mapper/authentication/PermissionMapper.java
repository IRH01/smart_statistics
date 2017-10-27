package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {

    List<Permission> searchPerms(Permission perm) throws Exception;

    List<Permission> queryByRole(List<Integer> roleIds) throws Exception;

    int delete(Permission perm) throws Exception;

    int insert(Permission record) throws Exception;

    Permission get(String permission) throws Exception;

    int update(Permission record) throws Exception;

    List<Permission> getAll() throws Exception;

}