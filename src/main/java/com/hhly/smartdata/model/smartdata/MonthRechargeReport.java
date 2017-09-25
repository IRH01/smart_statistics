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
    private String statisticsMonth;

    /**
     * 源端类型：1、PC 2.android 3.IOS 4.H5
     */
    private Byte sourceType;

    /**
     * PC-充值人数
     */
    private Integer rechargePopulation;

    /**
     * PC-充值金额
     */
    private BigDecimal rechargeAmount;

    /**
     * PC-充值次数
     */
    private Integer rechargeCount;

    /**
     * PC-新用户充值人数
     */
    private Integer newRechargePopulation;

    /**
     * PC-老用户充值人数
     */
    private Integer oldRechargePopulation;

    /**
     * 统计执行日期
     */
    private Date executeTime;

    /**
     * 国家简码
     */
    private String countryCode;

    /**
     * 货币单位
     */
    private String currencyUnit;

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

    public String getCountryCode(){
        return countryCode;
    }

    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }

    public String getCurrencyUnit(){
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit){
        this.currencyUnit = currencyUnit;
    }
}