package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.DailyRechargeReport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DailyRechargeReportMapper{
    int insert(DailyRechargeReport record) throws Exception;

    int insertSelective(DailyRechargeReport record) throws Exception;

    DailyRechargeReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DailyRechargeReport record) throws Exception;

    int updateByPrimaryKey(DailyRechargeReport record) throws Exception;

    List<Map<String, Object>> selectLastMonthRecharge() throws Exception;
}