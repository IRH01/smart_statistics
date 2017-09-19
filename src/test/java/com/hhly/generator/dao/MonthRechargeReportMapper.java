package com.hhly.generator.dao;

import com.hhly.generator.model.MonthRechargeReport;

public interface MonthRechargeReportMapper {
    int insert(MonthRechargeReport record);

    int insertSelective(MonthRechargeReport record);

    MonthRechargeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthRechargeReport record);

    int updateByPrimaryKey(MonthRechargeReport record);
}