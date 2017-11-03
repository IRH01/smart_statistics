package com.hhly.smartdata.dto.current;

public class IntervalGameLaunchListReport{
    /**
     * 统计时间(半小时)yyyy-MM-dd HH:30:00
     */
    private String statisticsTime = "";

    /**
     * 一比分启动次数
     */
    private Integer ybflaunchCount = 0;
    /**
     * 乐盈电竞启动次数
     */
    private Integer lydjlaunchCount = 0;

    /**
     * 撩妹德州启动次数
     */
    private Integer lmdzlaunchCount = 0;

    public String getStatisticsTime(){
        return statisticsTime;
    }

    public void setStatisticsTime(String statisticsTime){
        this.statisticsTime = statisticsTime;
    }

    public Integer getYbflaunchCount(){
        return ybflaunchCount;
    }

    public void setYbflaunchCount(Integer ybflaunchCount){
        this.ybflaunchCount = ybflaunchCount;
    }

    public Integer getLydjlaunchCount(){
        return lydjlaunchCount;
    }

    public void setLydjlaunchCount(Integer lydjlaunchCount){
        this.lydjlaunchCount = lydjlaunchCount;
    }

    public Integer getLmdzlaunchCount(){
        return lmdzlaunchCount;
    }

    public void setLmdzlaunchCount(Integer lmdzlaunchCount){
        this.lmdzlaunchCount = lmdzlaunchCount;
    }
}