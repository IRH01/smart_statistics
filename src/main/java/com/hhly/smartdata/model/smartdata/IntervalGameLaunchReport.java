package com.hhly.smartdata.model.smartdata;

import java.util.Date;

public class IntervalGameLaunchReport{
    /**
     * 平台实时统计，游戏启动统计
     */
    private Long id;

    /**
     * 统计时间(半小时)yyyy-MM-dd HH:30:00
     */
    private String statisticsTime = "";

    /**
     * 统计间隔时间。单位分钟
     */
    private Integer intervalTime = 0;

    /**
     * 源端类型：1、PC 2.android 3.IOS 4.H5
     */
    private Byte sourceType = 0;

    /**
     * 平台id或游戏id
     */
    private Integer platformId = 0;

    /**
     * 平台名称或游戏名称
     */
    private String platformName = "";

    /**
     * 老用户充值次数
     */
    private Integer launchCount = 0;

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

    public Integer getIntervalTime(){
        return intervalTime;
    }

    public void setIntervalTime(Integer intervalTime){
        this.intervalTime = intervalTime;
    }

    public Integer getPlatformId(){
        return platformId;
    }

    public void setPlatformId(Integer platformId){
        this.platformId = platformId;
    }

    public String getPlatformName(){
        return platformName;
    }

    public void setPlatformName(String platformName){
        this.platformName = platformName;
    }

    public Integer getLaunchCount(){
        return launchCount;
    }

    public void setLaunchCount(Integer launchCount){
        this.launchCount = launchCount;
    }

    public Date getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(Date executeTime){
        this.executeTime = executeTime;
    }

    public Byte getSourceType(){
        return sourceType;
    }

    public void setSourceType(Byte sourceType){
        this.sourceType = sourceType;
    }
}