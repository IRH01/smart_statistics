package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.IntervalGameLaunchReport;

public interface IntervalGameLaunchReportMapper{
    int insert(IntervalGameLaunchReport record);

    int insertSelective(IntervalGameLaunchReport record);

    IntervalGameLaunchReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntervalGameLaunchReport record);

    int updateByPrimaryKey(IntervalGameLaunchReport record);
}