package com.hhly.generator.model;

import java.math.BigDecimal;
import java.util.Date;

public class IntervalSourceReport {
    private Long id;

    private String statisticsTime;

    private Byte sourceType;

    private Integer registerPopulation;

    private Integer loginPopulation;

    private Integer rechargePopulation;

    private Integer rechargeCount;

    private BigDecimal rechargeAmount;

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

    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getRegisterPopulation() {
        return registerPopulation;
    }

    public void setRegisterPopulation(Integer registerPopulation) {
        this.registerPopulation = registerPopulation;
    }

    public Integer getLoginPopulation() {
        return loginPopulation;
    }

    public void setLoginPopulation(Integer loginPopulation) {
        this.loginPopulation = loginPopulation;
    }

    public Integer getRechargePopulation() {
        return rechargePopulation;
    }

    public void setRechargePopulation(Integer rechargePopulation) {
        this.rechargePopulation = rechargePopulation;
    }

    public Integer getRechargeCount() {
        return rechargeCount;
    }

    public void setRechargeCount(Integer rechargeCount) {
        this.rechargeCount = rechargeCount;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}