package com.hhly.generator.dao;

import com.hhly.generator.model.MonthRegisterReport;

public interface MonthRegisterReportMapper {
    int insert(MonthRegisterReport record);

    int insertSelective(MonthRegisterReport record);

    MonthRegisterReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthRegisterReport record);

    int updateByPrimaryKey(MonthRegisterReport record);
}