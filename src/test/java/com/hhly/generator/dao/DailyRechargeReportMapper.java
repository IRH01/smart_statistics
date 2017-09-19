package com.hhly.generator.dao;

import com.hhly.generator.model.DailyRechargeReport;

public interface DailyRechargeReportMapper {
    int insert(DailyRechargeReport record);

    int insertSelective(DailyRechargeReport record);

    DailyRechargeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyRechargeReport record);

    int updateByPrimaryKey(DailyRechargeReport record);
}