package com.hhly.generator.dao;

import com.hhly.generator.model.DailyLoginReport;

public interface DailyLoginReportMapper {
    int insert(DailyLoginReport record);

    int insertSelective(DailyLoginReport record);

    DailyLoginReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyLoginReport record);

    int updateByPrimaryKey(DailyLoginReport record);
}