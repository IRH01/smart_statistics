package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.IntervalInterfaceReport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IntervalInterfaceReportMapper{
    int insert(IntervalInterfaceReport record) throws Exception;

    int insertSelective(IntervalInterfaceReport record) throws Exception;

    IntervalInterfaceReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(IntervalInterfaceReport record) throws Exception;

    int updateByPrimaryKey(IntervalInterfaceReport record) throws Exception;

    List<IntervalInterfaceReport> selectIntervalInterfaceToltalData(Map<String, Object> map) throws Exception;

    List<IntervalInterfaceReport> selectIntervalIntefaceChartData(Map<String, Object> map) throws Exception;
}



