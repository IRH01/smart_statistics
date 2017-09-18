package com.hhly.smartdata.model.smartdata;

import java.math.BigDecimal;
import java.util.Date;

public class DailyRechargeReport{
    /**
     * 日报表，充值来源统计报表
     */
    private Long id;

    /**
     * 统计日期(日)yyyy-MM-dd
     */
    private String statisticsDay;

    /**
     * 源端类型：1、PC  2.H5 3.IOS 4.android
     */
    private Byte sourceType;

    /**
     * 充值人数
     */
    private Integer rechargePopulation;

    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;

    /**
     * 充值次数
     */
    private Integer rechargeCount;

    /**
     * 新用户充值人数
     */
    private Integer newRechargePopulation;

    /**
     * 老用户充值人数
     */
    private Integer oldRechargePopulation;

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

    public Integer getRechargePopulation(){
        return rechargePopulation;
    }

    public void setRechargePopulation(Integer rechargePopulation){
        this.rechargePopulation = rechargePopulation;
    }

    public BigDecimal getRechargeAmount(){
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount){
        this.rechargeAmount = rechargeAmount;
    }

    public Integer getRechargeCount(){
        return rechargeCount;
    }

    public void setRechargeCount(Integer rechargeCount){
        this.rechargeCount = rechargeCount;
    }

    public Integer getNewRechargePopulation(){
        return newRechargePopulation;
    }

    public void setNewRechargePopulation(Integer newRechargePopulation){
        this.newRechargePopulation = newRechargePopulation;
    }

    public Integer getOldRechargePopulation(){
        return oldRechargePopulation;
    }

    public void setOldRechargePopulation(Integer oldRechargePopulation){
        this.oldRechargePopulation = oldRechargePopulation;
    }

    public Date getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(Date executeTime){
        this.executeTime = executeTime;
    }
}