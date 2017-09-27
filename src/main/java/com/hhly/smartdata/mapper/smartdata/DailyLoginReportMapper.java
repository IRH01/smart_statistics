package com.hhly.smartdata.mapper.smartdata;

import com.hhly.smartdata.model.smartdata.DailyLoginReport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DailyLoginReportMapper{
    int insert(DailyLoginReport record) throws Exception;

    int insertSelective(DailyLoginReport record) throws Exception;

    DailyLoginReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DailyLoginReport record) throws Exception;

    int updateByPrimaryKey(DailyLoginReport record) throws Exception;

    List<Map<String, Object>> selectLastMonthLogin() throws Exception;
}