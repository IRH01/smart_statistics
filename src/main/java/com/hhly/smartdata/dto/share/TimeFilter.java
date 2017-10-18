package com.hhly.smartdata.dto.share;

/**
 * Created by Iritchie.ren on 2017/10/11.
 */
public class TimeFilter{
    /**
     * 页码
     */
    private Integer pageNo;
    /**
     * 页大小
     */
    private Integer pageSize;
    /**
     * 查询开始时间
     */
    private String timeStart;
    /**
     * 查询结束时间
     */
    private String timeEnd;

    public Integer getPageNo(){
        return pageNo;
    }

    public void setPageNo(Integer pageNo){
        this.pageNo = pageNo;
    }

    public Integer getPageSize(){
        return pageSize;
    }

    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }

    public String getTimeStart(){
        return timeStart;
    }

    public void setTimeStart(String timeStart){
        this.timeStart = timeStart;
    }

    public String getTimeEnd(){
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd){
        this.timeEnd = timeEnd;
    }
}
