package com.hhly.smartdata.dto.daily;

import java.util.List;

/**
 * @author hejh
 * @version V1.0
 * @Package com.hhly.smartdata.dto.daily
 * @Description: TODO
 * @date 2017-11-03 20:19
 */
public class DailyLoginDataResult{
    private String time;
    private List data;

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public List getData(){
        return data;
    }

    public void setData(List data){
        this.data = data;
    }
}
