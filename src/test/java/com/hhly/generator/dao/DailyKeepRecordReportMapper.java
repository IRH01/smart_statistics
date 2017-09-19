package com.hhly.generator.dao;

import com.hhly.generator.model.DailyKeepRecordReport;

public interface DailyKeepRecordReportMapper {
    int insert(DailyKeepRecordReport record);

    int insertSelective(DailyKeepRecordReport record);

    DailyKeepRecordReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyKeepRecordReport record);

    int updateByPrimaryKey(DailyKeepRecordReport record);
}