package com.hhly.generator.dao;

import com.hhly.generator.model.DailyCompositeReport;

public interface DailyCompositeReportMapper {
    int insert(DailyCompositeReport record);

    int insertSelective(DailyCompositeReport record);

    DailyCompositeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyCompositeReport record);

    int updateByPrimaryKey(DailyCompositeReport record);
}