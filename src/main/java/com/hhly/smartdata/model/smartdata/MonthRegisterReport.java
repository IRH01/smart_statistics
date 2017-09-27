package com.hhly.smartdata.model.smartdata;

import java.util.Date;

public class MonthRegisterReport{
    /**
     * 月报表，注册来源统计报表
     */
    private Long id;

    /**
     * 统计日期(月)yyyy-MM
     */
    private String statisticsMonth = "";

    /**
     * pc页面浏览数pv
     */
    private Long pcPageView = 0L;

    /**
     * pc用户访问数uv
     */
    private Integer pcUserView = 0;

    /**
     * h5用户数
     */
    private Integer h5Population = 0;

    /**
     * h5 pv
     */
    private Long h5PageView = 0L;

    /**
     * h5 uv
     */
    private Integer h5UserView = 0;

    /**
     * ios访问量
     */
    private Integer iosPopulation = 0;

    /**
     * ios安装量
     */
    private Integer iosInstallCount = 0;

    /**
     * android访问量
     */
    private Integer androidPopulation = 0;

    /**
     * android安装量
     */
    private Integer androidInstallCount = 0;

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

    public Long getPcPageView(){
        return pcPageView;
    }

    public void setPcPageView(Long pcPageView){
        this.pcPageView = pcPageView;
    }

    public Integer getPcUserView(){
        return pcUserView;
    }

    public void setPcUserView(Integer pcUserView){
        this.pcUserView = pcUserView;
    }

    public Integer getH5Population(){
        return h5Population;
    }

    public void setH5Population(Integer h5Population){
        this.h5Population = h5Population;
    }

    public Long getH5PageView(){
        return h5PageView;
    }

    public void setH5PageView(Long h5PageView){
        this.h5PageView = h5PageView;
    }

    public Integer getH5UserView(){
        return h5UserView;
    }

    public void setH5UserView(Integer h5UserView){
        this.h5UserView = h5UserView;
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