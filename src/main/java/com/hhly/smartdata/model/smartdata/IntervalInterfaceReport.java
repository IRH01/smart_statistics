package com.hhly.smartdata.model.smartdata;

import java.util.Date;

public class IntervalInterfaceReport{
    /**
     * 平台实时统计，接口统计
     */
    private Long id;

    /**
     * 统计日期(半小时)yyyy-MM-dd HH:30:00
     */
    private String statisticsTime;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 操作类型类型：1、请求，2、执行完成
     */
    private Byte operateType;

    /**
     * 操作次数
     */
    private Integer operateCount;

    /**
     * 统计执行日期
     */
    private Date executeTime;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getStatisticsTime(){
        return statisticsTime;
    }

    public void setStatisticsTime(String statisticsTime){
        this.statisticsTime = statisticsTime;
    }

    public String getInterfaceName(){
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName){
        this.interfaceName = interfaceName;
    }

    public Byte getOperateType(){
        return operateType;
    }

    public void setOperateType(Byte operateType){
        this.operateType = operateType;
    }

    public Integer getOperateCount(){
        return operateCount;
    }

    public void setOperateCount(Integer operateCount){
        this.operateCount = operateCount;
    }

    public Date getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(Date executeTime){
        this.executeTime = executeTime;
    }
}