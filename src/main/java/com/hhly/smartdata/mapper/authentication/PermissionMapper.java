package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper{


    List<Permission> searchPerms(Permission perm);

    List<Permission> queryByRole(List<Integer> roleIds);

    int delete(Permission perm);

    int insert(Permission record);

    Permission get(String permission);

    int update(Permission record);

    List<Permission> getAll();

}