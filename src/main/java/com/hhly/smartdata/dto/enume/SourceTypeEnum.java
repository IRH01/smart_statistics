package com.hhly.smartdata.dto.enume;

/**
 * Created by Iritchie.ren on 2017/9/25.
 */
public enum SourceTypeEnum{

    PC_WEB(Byte.parseByte("1"), "pc"),
    ANDROID(Byte.parseByte("2"), "android"),
    IPHONE(Byte.parseByte("3"), "iphone"),
    H5(Byte.parseByte("4"), "h5");

    private Byte code;
    private String desc;

    SourceTypeEnum(Byte code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Byte getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

    public static SourceTypeEnum getEnum(Byte code){
        for(SourceTypeEnum item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("枚举参数错误！");
    }
}
