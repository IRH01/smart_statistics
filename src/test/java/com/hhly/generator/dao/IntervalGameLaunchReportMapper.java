package com.hhly.generator.dao;

import com.hhly.generator.model.IntervalGameLaunchReport;

public interface IntervalGameLaunchReportMapper {
    int insert(IntervalGameLaunchReport record);

    int insertSelective(IntervalGameLaunchReport record);

    IntervalGameLaunchReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntervalGameLaunchReport record);

    int updateByPrimaryKey(IntervalGameLaunchReport record);
}