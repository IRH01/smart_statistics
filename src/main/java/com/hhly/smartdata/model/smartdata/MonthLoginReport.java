package com.hhly.smartdata.model.smartdata;

import java.util.Date;

public class MonthLoginReport{
    /**
     * 日报表，游戏登录统计报表
     */
    private Long id;

    /**
     * 统计日期(日)yyyy-MM
     */
    private String statisticsMonth = "";

    /**
     * 平台id或者游戏id
     */
    private String platformCode = "";

    /**
     * 平台或游戏名称
     */
    private String platformName = "";

    /**
     * 源端类型：1、PC  2.H5 3.IOS 4.android
     */
    private Byte sourceType = 0;

    /**
     * 登录人数
     */
    private Integer loginPopulation = 0;

    /**
     * 玩游戏人数
     */
    private Integer playPopulation = 0;

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

    public String getStatisticsMonth(){
        return statisticsMonth;
    }

    public void setStatisticsMonth(String statisticsMonth){
        this.statisticsMonth = statisticsMonth;
    }

    public String getPlatformCode(){
        return platformCode;
    }

    public void setPlatformCode(String platformCode){
        this.platformCode = platformCode;
    }

    public String getPlatformName(){
        return platformName;
    }

    public void setPlatformName(String platformName){
        this.platformName = platformName;
    }

    public Byte getSourceType(){
        return sourceType;
    }

    public void setSourceType(Byte sourceType){
        this.sourceType = sourceType;
    }

    public Integer getLoginPopulation(){
        return loginPopulation;
    }

    public void setLoginPopulation(Integer loginPopulation){
        this.loginPopulation = loginPopulation;
    }

    public Integer getPlayPopulation(){
        return playPopulation;
    }

    public void setPlayPopulation(Integer playPopulation){
        this.playPopulation = playPopulation;
    }

    public Date getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(Date executeTime){
        this.executeTime = executeTime;
    }
}