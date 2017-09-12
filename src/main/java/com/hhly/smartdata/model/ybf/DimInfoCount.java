package com.hhly.smartdata.model.ybf;

/**
 * 资讯数量信息
 *
 * @author WH
 */
public class DimInfoCount{
    /**
     * 资讯数量
     */
    private Integer count;
    /**
     * 资讯类型Id
     */
    private String infoTypeId;
    /**
     * 资讯类型名称
     */
    private String infoTypeName;
    /**
     * 资讯占总资讯数量的百分比
     */
    private double percent;

    public Integer getCount(){
        return count;
    }

    public void setCount(Integer count){
        this.count = count;
    }

    public String getInfoTypeId(){
        return infoTypeId;
    }

    public void setInfoTypeId(String infoTypeId){
        this.infoTypeId = infoTypeId;
    }

    public String getInfoTypeName(){
        return infoTypeName;
    }

    public void setInfoTypeName(String infoTypeName){
        this.infoTypeName = infoTypeName;
    }

    public double getPercent(){
        return percent;
    }

    public void setPercent(double percent){
        this.percent = percent;
    }
}
