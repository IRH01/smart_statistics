package com.hhly.smartdata.model.authentication;

import java.util.Date;

public class Permission{
    private String permission;

    private String name;

    private Integer functionId;

    private Date createTime;

    public String getPermission(){
        return permission;
    }

    public void setPermission(String permission){
        this.permission = permission == null ? null : permission.trim();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name == null ? null : name.trim();
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