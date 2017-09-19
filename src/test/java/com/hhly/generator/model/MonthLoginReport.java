package com.hhly.generator.model;

import java.util.Date;

public class MonthLoginReport {
    private Long id;

    private String gameCode;

    private String gameName;

    private String statisticsDay;

    private Byte sourceType;

    private Integer loginPopulation;

    private Integer playPopulation;

    private Date executeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatisticsDay() {
        return statisticsDay;
    }

    public void setStatisticsDay(String statisticsDay) {
        this.statisticsDay = statisticsDay;
    }

    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getLoginPopulation() {
        return loginPopulation;
    }

    public void setLoginPopulation(Integer loginPopulation) {
        this.loginPopulation = loginPopulation;
    }

    public Integer getPlayPopulation() {
        return playPopulation;
    }

    public void setPlayPopulation(Integer playPopulation) {
        this.playPopulation = playPopulation;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}