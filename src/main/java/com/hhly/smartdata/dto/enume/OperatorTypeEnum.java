package com.hhly.smartdata.dto.enume;

/**
 * Created by Iritchie.ren on 2017/9/25.
 */
public enum OperatorTypeEnum{

    REQUEST(Byte.parseByte("1"), "请求"),
    COMPLETE(Byte.parseByte("2"), "完成");

    private Byte code;
    private String desc;

    OperatorTypeEnum(Byte code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Byte getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

    public static OperatorTypeEnum getEnum(Byte code){
        for(OperatorTypeEnum item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("枚举参数错误！");
    }


    public static Byte getIndex(String name)
    {
        for (OperatorTypeEnum c : OperatorTypeEnum.values())
        {
            if (name.equals(c.getDesc()))
            {
                return c.code;
            }
        }
        return null;
    }


    public static String getName(String index){
        if(null != index){
            return null;
        }
        for (OperatorTypeEnum c : OperatorTypeEnum.values()){
            if (c.getCode()== Integer.parseInt(index)){
                return c.desc;
            }
        }
        return null;
    }
}
