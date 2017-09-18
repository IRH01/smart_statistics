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
    private String statisticsTime;

    /**
     * 游戏编码
     */
    private String gameCode;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 老用户充值次数
     */
    private Integer launchCount;

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
}