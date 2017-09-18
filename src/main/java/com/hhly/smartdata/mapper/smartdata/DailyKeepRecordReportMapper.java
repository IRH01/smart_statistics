package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.DailyKeepRecordReport;

public interface DailyKeepRecordReportMapper{
    int insert(DailyKeepRecordReport record);

    int insertSelective(DailyKeepRecordReport record);

    DailyKeepRecordReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyKeepRecordReport record);

    int updateByPrimaryKey(DailyKeepRecordReport record);
}