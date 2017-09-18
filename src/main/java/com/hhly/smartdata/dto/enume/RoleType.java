package com.hhly.smartdata.dto.enume;

public enum RoleType{

    ROLECHANNEL(1, "渠道"),
    ROLECP(2, "cp"),
    ROLEADMIN(3, "超级管理员"),
    ROLEAINNER(4, "内部用户");

    private Integer code;
    private String desc;

    RoleType(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

    public static RoleType getEnum(Integer code){
        for(RoleType item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("枚举参数错误！");
    }
}
