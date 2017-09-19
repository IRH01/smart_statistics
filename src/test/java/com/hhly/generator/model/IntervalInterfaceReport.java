package com.hhly.generator.model;

import java.util.Date;

public class IntervalInterfaceReport {
    private Long id;

    private String statisticsTime;

    private String interfaceName;

    private Byte operateType;

    private Integer operateCount;

    private Date executeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatisticsTime() {
        return statisticsTime;
    }

    public void setStatisticsTime(String statisticsTime) {
        this.statisticsTime = statisticsTime;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Byte getOperateType() {
        return operateType;
    }

    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }

    public Integer getOperateCount() {
        return operateCount;
    }

    public void setOperateCount(Integer operateCount) {
        this.operateCount = operateCount;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}