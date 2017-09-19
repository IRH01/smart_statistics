package com.hhly.generator.dao;

import com.hhly.generator.model.DailyRegisterReport;

public interface DailyRegisterReportMapper {
    int insert(DailyRegisterReport record);

    int insertSelective(DailyRegisterReport record);

    DailyRegisterReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyRegisterReport record);

    int updateByPrimaryKey(DailyRegisterReport record);
}