package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.DailyRegisterReport;

public interface DailyRegisterReportMapper{
    int insert(DailyRegisterReport record);

    int insertSelective(DailyRegisterReport record);

    DailyRegisterReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyRegisterReport record);

    int updateByPrimaryKey(DailyRegisterReport record);
}