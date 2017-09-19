package com.hhly.generator.dao;

import com.hhly.generator.model.MonthCompositeReport;

public interface MonthCompositeReportMapper {
    int insert(MonthCompositeReport record);

    int insertSelective(MonthCompositeReport record);

    MonthCompositeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthCompositeReport record);

    int updateByPrimaryKey(MonthCompositeReport record);
}