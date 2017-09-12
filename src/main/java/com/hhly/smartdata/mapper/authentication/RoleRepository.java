package com.hhly.smartdata.mapper.authentication;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.RolePermission;
import com.hhly.smartdata.util.page.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RoleRepository extends BaseRepository{

    public List<Role> search(Role role, Page page){
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<Role> list = template.selectList("baseRole.search", role);
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        page.setTotalPage(pageInfo.getPages());
        page.setTotalRecord(pageInfo.getTotal());
        return list;
    }

    public int delete(Integer id){
        return template.delete("baseRole.delete", id);
    }

    public int insert(Role record){
        return template.insert("baseRole.insert", record);
    }

    public Role get(Integer id){
        return template.selectOne("baseRole.get", id);
    }

    public List<Role> getRolesByUserId(Integer userId){
        return template.selectList("baseRole.getRolesByUserId", userId);
    }

    public int update(Role record){
        return template.update("baseRole.update", record);
    }

    public List<String> getPerms(List<Integer> roleIds){
        return template.selectList("baseRole.getPerms", roleIds);
    }

    public int delPermsByRole(List<Integer> roleIds){
        return template.delete("baseRole.delPermsByRole", roleIds);
    }

    public int delPerm(String perm){
        return template.delete("baseRole.delPerm", perm);
    }

    public void insertRolePermission(RolePermission rolePermission){
        template.insert("baseRole.insertRolePermission", rolePermission);
    }

    public void delUsersByRole(List<Integer> roleIds){
        template.delete("baseRole.delUsersByRole", roleIds);
    }

    public void insertUserRole(Map<String, Object> params){
        template.insert("baseRole.insertUserRole", params);
    }

    public void deleteUserRoles(int userId){
        template.delete("baseRole.deleteUserRoles", userId);
    }

    public Role getChannelRole(String roleName){
        return template.selectOne("baseRole.getChannelRole", roleName);
    }

}