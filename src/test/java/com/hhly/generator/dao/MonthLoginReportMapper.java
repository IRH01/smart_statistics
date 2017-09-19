package com.hhly.generator.dao;

import com.hhly.generator.model.MonthLoginReport;

public interface MonthLoginReportMapper {
    int insert(MonthLoginReport record);

    int insertSelective(MonthLoginReport record);

    MonthLoginReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthLoginReport record);

    int updateByPrimaryKey(MonthLoginReport record);
}