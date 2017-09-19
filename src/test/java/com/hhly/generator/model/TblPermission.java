package com.hhly.generator.model;

import java.util.Date;

public class TblPermission {
    private String permission;

    private String name;

    private Double functionId;

    private Date createTime;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Double functionId) {
        this.functionId = functionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}