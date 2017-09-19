package com.hhly.generator.model;

import java.math.BigDecimal;
import java.util.Date;

public class MonthRechargeReport {
    private Long id;

    private String statisticsDay;

    private Byte sourceType;

    private Integer rechargePopulation;

    private BigDecimal rechargeAmount;

    private Integer rechargeCount;

    private Integer newRechargePopulation;

    private Integer oldRechargePopulation;

    private Date executeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getRechargePopulation() {
        return rechargePopulation;
    }

    public void setRechargePopulation(Integer rechargePopulation) {
        this.rechargePopulation = rechargePopulation;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Integer getRechargeCount() {
        return rechargeCount;
    }

    public void setRechargeCount(Integer rechargeCount) {
        this.rechargeCount = rechargeCount;
    }

    public Integer getNewRechargePopulation() {
        return newRechargePopulation;
    }

    public void setNewRechargePopulation(Integer newRechargePopulation) {
        this.newRechargePopulation = newRechargePopulation;
    }

    public Integer getOldRechargePopulation() {
        return oldRechargePopulation;
    }

    public void setOldRechargePopulation(Integer oldRechargePopulation) {
        this.oldRechargePopulation = oldRechargePopulation;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}