package com.hhly.smartdata.model.smartdata;

import java.math.BigDecimal;
import java.util.Date;

public class MonthRechargeReport{
    /**
     * 月报表，充值来源统计报表
     */
    private Long id;

    /**
     * 统计日期(月)yyyy-MM
     */
    private String statisticsMonth = "";

    /**
     * 源端类型：1、PC 2.android 3.IOS 4.H5
     */
    private Byte sourceType = 0;

    /**
     * PC-充值人数
     */
    private Integer rechargePopulation = 0;

    /**
     * PC-充值金额
     */
    private BigDecimal rechargeAmount = new BigDecimal(0.00);

    /**
     * PC-充值次数
     */
    private Integer rechargeCount = 0;

    /**
     * PC-新用户充值人数
     */
    private Integer newRechargePopulation = 0;

    /**
     * PC-老用户充值人数
     */
    private Integer oldRechargePopulation = 0;

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
        return rechargeAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
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