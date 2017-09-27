package com.hhly.smartdata.dto.enume;

/**
 * Created by Iritchie.ren on 2017/9/25.
 */
public enum InterfaceTypeEnum{

    REGISTER(Byte.parseByte("1"), "注册"),
    RECHARGE(Byte.parseByte("2"), "充值");

    private Byte code;
    private String desc;

    InterfaceTypeEnum(Byte code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Byte getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

    public static InterfaceTypeEnum getEnum(Byte code){
        for(InterfaceTypeEnum item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("枚举参数错误！");
    }
}
