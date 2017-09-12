package com.hhly.smartdata.model.ybf;


/**
 * 行为统计信息
 *
 * @author WH
 */
public class ActCountInfo{

    /**
     * 数量（点击量、IP数量、停留时长等）
     */
    private double count;
    /**
     * 平均量
     */
    private double avgCount;
    /**
     * 计算平均量的单位值
     */
    private long unitValue;
    /**
     * 资讯类型Id
     */
    private String infoTypeId;
    /**
     * 资讯类型名称
     */
    private String infoTypeName;
    /**
     * 百分比
     */
    private double percent;

    public double getCount(){
        return count;
    }

    public void setCount(double count){
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

    /**
     * 获取单个用户的点击量
     *
     * @return
     */
    public double getAvgCount(){
        return avgCount;
    }

    public void setAvgCount(double avgCount){
        this.avgCount = avgCount;
    }

    public long getUnitValue(){
        return unitValue;
    }

    public void setUnitValue(long unitValue){
        this.unitValue = unitValue;
    }
}
