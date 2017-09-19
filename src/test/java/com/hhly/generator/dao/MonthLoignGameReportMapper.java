package com.hhly.generator.dao;

import com.hhly.generator.model.MonthLoignGameReport;

public interface MonthLoignGameReportMapper {
    int insert(MonthLoignGameReport record);

    int insertSelective(MonthLoignGameReport record);

    MonthLoignGameReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthLoignGameReport record);

    int updateByPrimaryKey(MonthLoignGameReport record);
}