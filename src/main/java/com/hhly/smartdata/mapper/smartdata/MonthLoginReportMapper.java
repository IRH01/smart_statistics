package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.dto.mouth.LoginStatisticsFilter;
import com.hhly.smartdata.dto.mouth.TimeFilter;
import com.hhly.smartdata.model.smartdata.MonthLoginReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthLoginReportMapper{
    int insert(MonthLoginReport record) throws Exception;

    int insertSelective(MonthLoginReport record) throws Exception;

    MonthLoginReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MonthLoginReport record) throws Exception;

    int updateByPrimaryKey(MonthLoginReport record) throws Exception;

    List<MonthLoginReport> searchByTime(LoginStatisticsFilter filter) throws Exception;

    long searchByTimeCount(LoginStatisticsFilter filter) throws Exception;
}