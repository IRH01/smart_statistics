package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.MonthRechargeReport;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthRechargeReportMapper{
    int insert(MonthRechargeReport record);

    int insertSelective(MonthRechargeReport record);

    MonthRechargeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthRechargeReport record);

    int updateByPrimaryKey(MonthRechargeReport record);
}