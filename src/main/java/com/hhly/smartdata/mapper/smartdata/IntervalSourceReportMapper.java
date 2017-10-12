package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IntervalSourceReportMapper{
    int insert(IntervalSourceReport record) throws Exception;

    IntervalSourceReport selectByPrimaryKey(Long id) throws Exception;

    Map<String,Object> selectIntervalSourceToltalData(Map<String,Object> map)throws Exception;

    List<IntervalSourceReport> selectIntervalSourceListData(Map<String,Object> map)throws Exception;

    List<IntervalSourceReport> selectIntervalSourceChartData(Map<String,Object> map)throws Exception;

    List<IntervalSourceReport> selectIntervalTerminalsSourceListData(Map<String,Object> map)throws Exception;

    List<IntervalSourceReport> selectIntervalTimeTerminalsSourceListData(Map<String,Object> map)throws Exception;

}