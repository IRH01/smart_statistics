package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.DailyKeepRecordReport;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyKeepRecordReportMapper{
    int insert(DailyKeepRecordReport record) throws Exception;

    int insertSelective(DailyKeepRecordReport record) throws Exception;

    DailyKeepRecordReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DailyKeepRecordReport record) throws Exception;

    int updateByPrimaryKey(DailyKeepRecordReport record) throws Exception;
}