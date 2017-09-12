package com.hhly.smartdata.model.authentication;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable{

    private static final long serialVersionUID = -8614419898853239774L;

    private Integer id;

    private String permission;

    @JSONField(name = "pId")
    private Integer parentId;

    private String name;

    private String url;

    private Integer index;

    private Date createTime;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    @Override
    public String toString(){
        return "Menu [id=" + id + ", permission=" + permission + ", parentId="
                + parentId + ", name=" + name + ", url=" + url + ", index="
                + index + ", createTime=" + createTime + "]";
    }

    public String getPermission(){
        return permission;
    }

    public void setPermission(String permission){
        this.permission = permission == null ? null : permission.trim();
    }

    public Integer getParentId(){
        return parentId;
    }

    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name == null ? null : name.trim();
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url == null ? null : url.trim();
    }

    public Integer getIndex(){
        return index;
    }

    public void setIndex(Integer index){
        this.index = index;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
}