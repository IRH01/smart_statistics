package com.hhly.smartdata.model.authentication;

/**
 * Created by xiang_xiang on 2015-03-20.
 */
public class RolePermission{
    private Integer id;
    private Integer roleId;
    private String permission;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getRoleId(){
        return roleId;
    }

    public void setRoleId(Integer roleId){
        this.roleId = roleId;
    }

    public String getPermission(){
        return permission;
    }

    public void setPermission(String permission){
        this.permission = permission;
    }
}
