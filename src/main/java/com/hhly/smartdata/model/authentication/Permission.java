package com.hhly.smartdata.model.authentication;

import java.util.Date;

public class Permission{
    private String permission;

    private String permissionName;

    private Integer functionId;

    private Date createTime;

    public String getPermission(){
        return permission;
    }

    public void setPermission(String permission){
        this.permission = permission == null ? null : permission.trim();
    }

    public String getPermissionName(){
        return permissionName;
    }

    public void setPermissionName(String permisionName){
        this.permissionName = permisionName == null ? null : permisionName.trim();
    }

    public Integer getFunctionId(){
        return functionId;
    }

    public void setFunctionId(Integer functionId){
        this.functionId = functionId;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
}