package com.hhly.smartdata.dto.mouth;

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
    private String monthStart;
    /**
     * 查询结束时间
     */
    private String monthEnd;

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

    public String getMonthStart(){
        return monthStart;
    }

    public void setMonthStart(String monthStart){
        this.monthStart = monthStart;
    }

    public String getMonthEnd(){
        return monthEnd;
    }

    public void setMonthEnd(String monthEnd){
        this.monthEnd = monthEnd;
    }
}
