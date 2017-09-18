package com.hhly.smartdata.model.smartdata;

import java.util.Date;

public class DailyLoginReport{
    /**
     * 日报表，充值来源统计报表
     */
    private Long id;

    /**
     * 游戏编码
     */
    private String gameCode;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 统计日期(日)yyyy-MM-dd
     */
    private String statisticsDay;

    /**
     * 源端类型：1、PC  2.H5 3.IOS 4.android
     */
    private Byte sourceType;

    /**
     * 登录人数
     */
    private Integer loginPopulation;

    /**
     * 玩游戏人数
     */
    private Integer playPopulation;

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

    public String getGameCode(){
        return gameCode;
    }

    public void setGameCode(String gameCode){
        this.gameCode = gameCode;
    }

    public String getGameName(){
        return gameName;
    }

    public void setGameName(String gameName){
        this.gameName = gameName;
    }

    public String getStatisticsDay(){
        return statisticsDay;
    }

    public void setStatisticsDay(String statisticsDay){
        this.statisticsDay = statisticsDay;
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