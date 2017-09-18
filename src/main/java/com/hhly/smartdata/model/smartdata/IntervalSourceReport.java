package com.hhly.smartdata.model.smartdata;

import java.math.BigDecimal;
import java.util.Date;

public class IntervalSourceReport{
    /**
     * 平台实时统计，各端实时统计
     */
    private Long id;

    /**
     * 统计日期(半小时)yyyy-MM-dd HH:30:00
     */
    private String statisticsTime;

    /**
     * 源端类型：1、PC  2.H5 3.IOS 4.android
     */
    private Byte sourceType;

    /**
     * 注册人数
     */
    private Integer registerPopulation;

    /**
     * 登录人数
     */
    private Integer loginPopulation;

    /**
     * 充值人数
     */
    private Integer rechargePopulation;

    /**
     * 充值次数
     */
    private Integer rechargeCount;

    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;

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

    public Byte getSourceType(){
        return sourceType;
    }

    public void setSourceType(Byte sourceType){
        this.sourceType = sourceType;
    }

    public Integer getRegisterPopulation(){
        return registerPopulation;
    }

    public void setRegisterPopulation(Integer registerPopulation){
        this.registerPopulation = registerPopulation;
    }

    public Integer getLoginPopulation(){
        return loginPopulation;
    }

    public void setLoginPopulation(Integer loginPopulation){
        this.loginPopulation = loginPopulation;
    }

    public Integer getRechargePopulation(){
        return rechargePopulation;
    }

    public void setRechargePopulation(Integer rechargePopulation){
        this.rechargePopulation = rechargePopulation;
    }

    public Integer getRechargeCount(){
        return rechargeCount;
    }

    public void setRechargeCount(Integer rechargeCount){
        this.rechargeCount = rechargeCount;
    }

    public BigDecimal getRechargeAmount(){
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount){
        this.rechargeAmount = rechargeAmount;
    }

    public Date getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(Date executeTime){
        this.executeTime = executeTime;
    }
}