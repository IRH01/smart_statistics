package com.hhly.smartdata.model.authentication;

import com.google.common.collect.Maps;

import java.util.Map;

public class Admin extends User{
    private Integer id;
    private String adminName;
    private String email;
    private String tel;
    private Integer departId;
    private String jobNo;
    private Integer roleType;
    private String channelIds;


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getAdminName(){
        return adminName;
    }

    public void setAdminName(String adminName){
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email == null ? null : email.trim();
    }

    public String getTel(){
        return tel;
    }

    public void setTel(String tel){
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getDepartId(){
        return departId;
    }

    public void setDepartId(Integer departId){
        this.departId = departId;
    }

    public String getJobNo(){
        return jobNo;
    }

    public void setJobNo(String jobNo){
        this.jobNo = jobNo == null ? null : jobNo.trim();
    }

    public Integer getRoleType(){
        return roleType;
    }

    public void setRoleType(Integer roleType){
        this.roleType = roleType;
    }

    public String getChannelIds(){
        return channelIds;
    }

    public void setChannelIds(String channelIds){
        this.channelIds = channelIds;
    }

    @Override
    public String toString(){
        return "Admin:{" +
                "id:" + id +
                ", adminName:'" + adminName + '\'' +
                ", email:'" + email + '\'' +
                ", tel:'" + tel + '\'' +
                ", departId:" + departId +
                ", jobNo:'" + jobNo + '\'' +
                ", roleType:" + roleType +
                "}, " + super.toString();
    }

    public static enum Type{
        ADMIN(2, "管理员"),
        COMMERCE(3, "商务"),
        YUNYING(4, "运营人员"),
        OTHER(1, "其他人员");

        private static final Map<Integer, String> map = Maps.newLinkedHashMap();
        private Integer value;
        private String name;

        //常量
        Type(int value, String name){
            this.value = value;
            this.name = name;
        }

        public synchronized static Map<Integer, String> map(){
            if(map.size() > 0) return map;
            for(Type type1 : Type.values()){
                map.put(type1.getValue(), type1.getName());
            }
            return map;
        }

        public Integer getValue(){
            return value;
        }

        public void setValue(Integer value){
            this.value = value;
        }

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}