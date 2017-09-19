package com.hhly.generator.model;

import java.util.Date;

public class TblMenu {
    private Double id;

    private String permission;

    private Double parentId;

    private String name;

    private String url;

    private Double menuIndex;

    private Date createTime;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Double getParentId() {
        return parentId;
    }

    public void setParentId(Double parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(Double menuIndex) {
        this.menuIndex = menuIndex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}