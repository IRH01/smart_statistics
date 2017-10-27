package com.hhly.smartdata.mapper.smartdata;

import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.model.smartdata.MonthRegisterReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthRegisterReportMapper{

    int insert(MonthRegisterReport record) throws Exception;

    int insertSelective(MonthRegisterReport record) throws Exception;

    MonthRegisterReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MonthRegisterReport record) throws Exception;

    int updateByPrimaryKey(MonthRegisterReport record) throws Exception;

    List<MonthRegisterReport> searchByTime(TimeFilter filter) throws Exception;

    long searchByTimeCount(TimeFilter filter) throws Exception;

    MonthRegisterReport selectByMonth(String month) throws Exception;

    void deleteByTime(String statisticsMonth) throws Exception;
}