package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.DailyRechargeReport;

public interface DailyRechargeReportMapper{
    int insert(DailyRechargeReport record);

    int insertSelective(DailyRechargeReport record);

    DailyRechargeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyRechargeReport record);

    int updateByPrimaryKey(DailyRechargeReport record);
}