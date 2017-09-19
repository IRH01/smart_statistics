package com.hhly.generator.model;

public class TblRolePermission {
    private Double id;

    private Double roleId;

    private String permission;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getRoleId() {
        return roleId;
    }

    public void setRoleId(Double roleId) {
        this.roleId = roleId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}