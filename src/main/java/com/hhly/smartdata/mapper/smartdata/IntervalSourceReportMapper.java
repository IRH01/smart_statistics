package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface IntervalSourceReportMapper{
    int insert(IntervalSourceReport record) throws Exception;

    IntervalSourceReport selectByPrimaryKey(Long id) throws Exception;

    List<IntervalSourceReport> selectIntervalSourceListData(@Param("startDate") String startDate, @Param("endDate") String endDate) throws Exception;

    List<HashMap<String, Object>> selectIntervalSourceChartData(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("sourceType") String sourceType) throws Exception;

    List<IntervalSourceReport> selectIntervalTerminalsSourceListData(@Param("startDate") String startDate, @Param("endDate") String endDate) throws Exception;

    List<IntervalSourceReport> selectIntervalTimeTerminalsSourceListData(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("sourceType") String sourceType) throws Exception;

    void deleteByTimeSourceType(@Param("statisticsTime") String statisticsTime, @Param("sourceType") Byte sourceType) throws Exception;
}