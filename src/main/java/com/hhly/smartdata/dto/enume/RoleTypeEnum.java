package com.hhly.smartdata.dto.enume;

public enum RoleTypeEnum{

    ROLECHANNEL(1, "渠道"),
    ROLECP(2, "cp"),
    ROLEADMIN(3, "超级管理员"),
    ROLEAINNER(4, "内部用户");

    private Integer code;
    private String desc;

    RoleTypeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

    public static RoleTypeEnum getEnum(Integer code){
        for(RoleTypeEnum item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("枚举参数错误！");
    }
}
