package com.hhly.generator.dao;

import com.hhly.generator.model.DailyLoignGameReport;

public interface DailyLoignGameReportMapper {
    int insert(DailyLoignGameReport record);

    int insertSelective(DailyLoignGameReport record);

    DailyLoignGameReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyLoignGameReport record);

    int updateByPrimaryKey(DailyLoignGameReport record);
}