package com.hhly.generator.model;

import java.util.Date;

public class IntervalGameLaunchReport {
    private Long id;

    private String statisticsTime;

    private String gameCode;

    private String gameName;

    private Integer launchCount;

    private Date executeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatisticsTime() {
        return statisticsTime;
    }

    public void setStatisticsTime(String statisticsTime) {
        this.statisticsTime = statisticsTime;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getLaunchCount() {
        return launchCount;
    }

    public void setLaunchCount(Integer launchCount) {
        this.launchCount = launchCount;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}