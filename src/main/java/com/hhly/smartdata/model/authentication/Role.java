package com.hhly.smartdata.model.authentication;

import java.util.Date;

public class Role{
    private Integer id;

    private String roleName;

    private Date createTime;

    private Boolean owned;//是否被当前用户拥有

    private String permission;

    private String permissionIds;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getRoleName(){
        return roleName;
    }

    public void setRoleName(String roleName){
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public String getPermission(){
        return permission;
    }

    public void setPermission(String permission){
        this.permission = permission;
    }

    public String getPermissionIds(){
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds){
        this.permissionIds = permissionIds;
    }

    public Boolean getOwned(){
        return owned;
    }

    public void setOwned(Boolean owned){
        this.owned = owned;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        if(createTime != null ? !createTime.equals(role.createTime) : role.createTime != null) return false;
        if(id != null ? !id.equals(role.id) : role.id != null) return false;
        if(roleName != null ? !roleName.equals(role.roleName) : role.roleName != null) return false;
        if(owned != null ? !owned.equals(role.owned) : role.owned != null) return false;
        if(permission != null ? !permission.equals(role.permission) : role.permission != null) return false;
        if(permissionIds != null ? !permissionIds.equals(role.permissionIds) : role.permissionIds != null) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (owned != null ? owned.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        result = 31 * result + (permissionIds != null ? permissionIds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "Role [id=" + id + ", roleName=" + roleName + ", createTime="
                + createTime + ", owned=" + owned + ", permission="
                + permission + ", permissionIds=" + permissionIds + "]";
    }
}