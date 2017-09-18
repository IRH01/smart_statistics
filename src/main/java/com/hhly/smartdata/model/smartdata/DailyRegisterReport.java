package com.hhly.smartdata.model.smartdata;

import java.util.Date;

public class DailyRegisterReport{
    /**
     * 日报表，注册来源统计报表
     */
    private Long id;

    /**
     * 统计日期(日)yyyy-MM-dd
     */
    private String statisticsDay;

    /**
     * pc页面浏览数pv
     */
    private Integer pcPageView;

    /**
     * pc用户访问数uv
     */
    private Integer pcUserView;

    /**
     * h5用户数
     */
    private Integer hfivePopulation;

    /**
     * h5 pv
     */
    private Integer hfivePageView;

    /**
     * h5 uv
     */
    private Integer hfiveUserView;

    /**
     * ios访问量
     */
    private Integer iosPopulation;

    /**
     * ios安装量
     */
    private Integer iosInstallCount;

    /**
     * android访问量
     */
    private Integer androidPopulation;

    /**
     * android安装量
     */
    private Integer androidInstallCount;

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

    public Integer getPcPageView(){
        return pcPageView;
    }

    public void setPcPageView(Integer pcPageView){
        this.pcPageView = pcPageView;
    }

    public Integer getPcUserView(){
        return pcUserView;
    }

    public void setPcUserView(Integer pcUserView){
        this.pcUserView = pcUserView;
    }

    public Integer getHfivePopulation(){
        return hfivePopulation;
    }

    public void setHfivePopulation(Integer hfivePopulation){
        this.hfivePopulation = hfivePopulation;
    }

    public Integer getHfivePageView(){
        return hfivePageView;
    }

    public void setHfivePageView(Integer hfivePageView){
        this.hfivePageView = hfivePageView;
    }

    public Integer getHfiveUserView(){
        return hfiveUserView;
    }

    public void setHfiveUserView(Integer hfiveUserView){
        this.hfiveUserView = hfiveUserView;
    }

    public Integer getIosPopulation(){
        return iosPopulation;
    }

    public void setIosPopulation(Integer iosPopulation){
        this.iosPopulation = iosPopulation;
    }

    public Integer getIosInstallCount(){
        return iosInstallCount;
    }

    public void setIosInstallCount(Integer iosInstallCount){
        this.iosInstallCount = iosInstallCount;
    }

    public Integer getAndroidPopulation(){
        return androidPopulation;
    }

    public void setAndroidPopulation(Integer androidPopulation){
        this.androidPopulation = androidPopulation;
    }

    public Integer getAndroidInstallCount(){
        return androidInstallCount;
    }

    public void setAndroidInstallCount(Integer androidInstallCount){
        this.androidInstallCount = androidInstallCount;
    }

    public Date getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(Date executeTime){
        this.executeTime = executeTime;
    }
}