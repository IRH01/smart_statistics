package com.hhly.smartdata.mapper.smartdata;

import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.model.smartdata.DailyLoginReport;
import org.apache.ibatis.annotations.Param;
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

    List<Map<String, Object>> selectLastMonthLogin(@Param("lastMonthFirstDayStr") String lastMonthFirstDayStr, @Param("lastMonthEndDayStr") String lastMonthEndDayStr) throws Exception;

    List<DailyLoginReport> searchByTime(TimeFilter filter) throws Exception;

    long searchByTimeCount(TimeFilter filter) throws Exception;

    Map<String, Object> selectByMonth(String daily) throws Exception;

    void deleteByTimeAndPlatformCodeAndSourceType(@Param("statisticsDay") String statisticsDay, @Param("platformCode") String platformCode, @Param("sourceType") Byte sourceType) throws Exception;
}