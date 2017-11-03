package com.hhly.smartdata.dto.current;

/**
 * @author hejh
 * @version V1.0
 * @Package com.hhly.smartdata.model.smartdata
 * @Description: 游戏启动次数
 * @date 2017-11-01 20:20
 */
public class IntervalGameLaunchTimeListReport{
    /**
     * 统计时间
     */
    private String statisticTime;

    /**
     * 运行次数
     */
    private int launchCount;

    public String getStatisticTime(){
        return statisticTime;
    }

    public void setStatisticTime(String statisticTime){
        this.statisticTime = statisticTime;
    }

    public int getLaunchCount(){
        return launchCount;
    }

    public void setLaunchCount(int launchCount){
        this.launchCount = launchCount;
    }
}
