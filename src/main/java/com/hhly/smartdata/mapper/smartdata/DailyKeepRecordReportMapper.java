package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.dto.daily.DailyKeepRecordReportResult;
import com.hhly.smartdata.model.smartdata.DailyKeepRecordReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyKeepRecordReportMapper {
    int insert(DailyKeepRecordReport record) throws Exception;

    int insertSelective(DailyKeepRecordReport record) throws Exception;

    DailyKeepRecordReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DailyKeepRecordReport record) throws Exception;

    int updateByPrimaryKey(DailyKeepRecordReport record) throws Exception;

    List<DailyKeepRecordReportResult> selectDailyKeepRecordListData(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("sourceType") Integer sourceType) throws Exception;

    Float selectAverageOneKeep(@Param("startDay") String startDay, @Param("endDay") String endDay) throws Exception;

    void deleteByTime(String statisticsDay) throws Exception;
}