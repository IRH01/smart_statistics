package com.hhly.smartdata.model.smartdata;

import java.math.BigDecimal;
import java.util.Date;

public class MonthCompositeReport{
    /**
     * 月报表，综合报表
     */
    private Long id;

    /**
     * 统计日期(月)yyyy-MM
     */
    private String statisticsDay;

    /**
     * 注册人数
     */
    private Integer registerPopulation;

    /**
     * 注册体验量
     */
    private Integer registerExpCount;

    /**
     * 真体验
     */
    private Integer realExpCount;

    /**
     * 假体验
     */
    private Integer virtualExpCount;

    /**
     * 新用户充值人数
     */
    private Integer newUserRechargePopulation;

    /**
     * 新用户充值次数
     */
    private Integer newUserRechargeCount;

    /**
     * 新用户充值金额
     */
    private BigDecimal newUserRechargeAmount;

    /**
     * 新用户登录人数
     */
    private Integer newUserLoginCount;

    /**
     * 新用户玩游戏数
     */
    private Integer newUserPlayCount;

    /**
     * 老用户充值人数
     */
    private Integer oldUserRechargePopulation;

    /**
     * 老用户充值次数
     */
    private Integer oldUserRechargeCount;

    /**
     * 老用户充值金额
     */
    private BigDecimal oldUserRechargeAmount;

    /**
     * 老用户登录人数
     */
    private Integer oldUserLoginCount;

    /**
     * 老用户玩游戏数
     */
    private Integer oldUserPlayCount;

    /**
     * 次日留存
     */
    private Integer nextDayStayCount;

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

    public Integer getRegisterPopulation(){
        return registerPopulation;
    }

    public void setRegisterPopulation(Integer registerPopulation){
        this.registerPopulation = registerPopulation;
    }

    public Integer getRegisterExpCount(){
        return registerExpCount;
    }

    public void setRegisterExpCount(Integer registerExpCount){
        this.registerExpCount = registerExpCount;
    }

    public Integer getRealExpCount(){
        return realExpCount;
    }

    public void setRealExpCount(Integer realExpCount){
        this.realExpCount = realExpCount;
    }

    public Integer getVirtualExpCount(){
        return virtualExpCount;
    }

    public void setVirtualExpCount(Integer virtualExpCount){
        this.virtualExpCount = virtualExpCount;
    }

    public Integer getNewUserRechargePopulation(){
        return newUserRechargePopulation;
    }

    public void setNewUserRechargePopulation(Integer newUserRechargePopulation){
        this.newUserRechargePopulation = newUserRechargePopulation;
    }

    public Integer getNewUserRechargeCount(){
        return newUserRechargeCount;
    }

    public void setNewUserRechargeCount(Integer newUserRechargeCount){
        this.newUserRechargeCount = newUserRechargeCount;
    }

    public BigDecimal getNewUserRechargeAmount(){
        return newUserRechargeAmount;
    }

    public void setNewUserRechargeAmount(BigDecimal newUserRechargeAmount){
        this.newUserRechargeAmount = newUserRechargeAmount;
    }

    public Integer getNewUserLoginCount(){
        return newUserLoginCount;
    }

    public void setNewUserLoginCount(Integer newUserLoginCount){
        this.newUserLoginCount = newUserLoginCount;
    }

    public Integer getNewUserPlayCount(){
        return newUserPlayCount;
    }

    public void setNewUserPlayCount(Integer newUserPlayCount){
        this.newUserPlayCount = newUserPlayCount;
    }

    public Integer getOldUserRechargePopulation(){
        return oldUserRechargePopulation;
    }

    public void setOldUserRechargePopulation(Integer oldUserRechargePopulation){
        this.oldUserRechargePopulation = oldUserRechargePopulation;
    }

    public Integer getOldUserRechargeCount(){
        return oldUserRechargeCount;
    }

    public void setOldUserRechargeCount(Integer oldUserRechargeCount){
        this.oldUserRechargeCount = oldUserRechargeCount;
    }

    public BigDecimal getOldUserRechargeAmount(){
        return oldUserRechargeAmount;
    }

    public void setOldUserRechargeAmount(BigDecimal oldUserRechargeAmount){
        this.oldUserRechargeAmount = oldUserRechargeAmount;
    }

    public Integer getOldUserLoginCount(){
        return oldUserLoginCount;
    }

    public void setOldUserLoginCount(Integer oldUserLoginCount){
        this.oldUserLoginCount = oldUserLoginCount;
    }

    public Integer getOldUserPlayCount(){
        return oldUserPlayCount;
    }

    public void setOldUserPlayCount(Integer oldUserPlayCount){
        this.oldUserPlayCount = oldUserPlayCount;
    }

    public Integer getNextDayStayCount(){
        return nextDayStayCount;
    }

    public void setNextDayStayCount(Integer nextDayStayCount){
        this.nextDayStayCount = nextDayStayCount;
    }

    public Date getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(Date executeTime){
        this.executeTime = executeTime;
    }
}