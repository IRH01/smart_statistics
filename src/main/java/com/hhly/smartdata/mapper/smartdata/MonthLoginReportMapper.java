package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.model.smartdata.MonthLoginReport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MonthLoginReportMapper{
    int insert(MonthLoginReport record) throws Exception;

    int insertSelective(MonthLoginReport record) throws Exception;

    MonthLoginReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MonthLoginReport record) throws Exception;

    int updateByPrimaryKey(MonthLoginReport record) throws Exception;

    List<MonthLoginReport> searchByTime(TimeFilter filter) throws Exception;

    long searchByTimeCount(TimeFilter filter) throws Exception;

    Map<String,Object> selectByMonth(String month) throws Exception;

    void deleteByTime(String statisticsMonth) throws Exception;

}