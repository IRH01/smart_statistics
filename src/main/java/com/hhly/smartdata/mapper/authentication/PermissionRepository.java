package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionRepository extends BaseRepository{


    public List<Permission> searchPerms(Permission perm){
        return template.selectList("basePermission.searchPerms", perm);
    }

    public List<Permission> queryByRole(List<Integer> roleIds){
        return template.selectList("basePermission.queryByRole", roleIds);
    }

    public int delete(Permission perm){
        return template.delete("basePermission.delete", perm);
    }

    public int insert(Permission record){
        return template.insert("basePermission.insert", record);
    }

    public Permission get(String permission){
        return template.selectOne("basePermission.get", permission);
    }

    public int update(Permission record){
        return template.update("basePermission.update", record);
    }

    public List<Permission> getAll(){
        return template.selectList("basePermission.getAll");
    }

}