package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.model.smartdata.MonthLoginReport;
import org.apache.ibatis.annotations.Param;
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

    List<MonthLoginReport> searchByTime(@Param("startTime") String startTime, @Param("endTime") String endTime) throws Exception;

    Map<String, Object> selectByMonth(String month) throws Exception;

    void deleteByTimeAndPlatformCodeAndSourceType(@Param("statisticsMonth") String statisticsMonth, @Param("platformCode") String platformCode, @Param("sourceType") Byte sourceType);
}