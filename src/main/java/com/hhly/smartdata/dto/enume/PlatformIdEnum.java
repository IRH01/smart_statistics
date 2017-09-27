package com.hhly.smartdata.dto.enume;

/**
 * Created by Iritchie.ren on 2017/9/25.
 */
public enum PlatformIdEnum{

    LIAO_MEI_DE_ZHOU(1, "撩妹德州"),
    LE_YING_DIAN_JING(2, "乐盈电竞"),
    YI_BI_FEN(3, "一比分");

    private Integer code;
    private String desc;

    PlatformIdEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

    public static PlatformIdEnum getEnum(Integer code){
        for(PlatformIdEnum item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("枚举参数错误！");
    }
}
