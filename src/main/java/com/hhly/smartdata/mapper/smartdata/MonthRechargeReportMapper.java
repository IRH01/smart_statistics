package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.MonthRechargeReport;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthRechargeReportMapper{
    int insert(MonthRechargeReport record) throws Exception;

    int insertSelective(MonthRechargeReport record) throws Exception;

    MonthRechargeReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MonthRechargeReport record) throws Exception;

    int updateByPrimaryKey(MonthRechargeReport record) throws Exception;
}