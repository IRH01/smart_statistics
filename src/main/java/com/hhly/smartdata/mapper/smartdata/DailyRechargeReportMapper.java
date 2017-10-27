package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.dto.daily.DailyRechargeReportResult;
import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.model.smartdata.DailyRechargeReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DailyRechargeReportMapper {
    int insert(DailyRechargeReport record) throws Exception;

    int insertSelective(DailyRechargeReport record) throws Exception;

    DailyRechargeReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DailyRechargeReport record) throws Exception;

    int updateByPrimaryKey(DailyRechargeReport record) throws Exception;

    List<Map<String, Object>> selectLastMonthRecharge(@Param("lastMonthFirstDayStr") String lastMonthFirstDayStr, @Param("lastMonthEndDayStr") String lastMonthEndDayStr) throws Exception;

    List<DailyRechargeReportResult> searchByTime(TimeFilter filter) throws Exception;

    long searchByTimeCount(TimeFilter filter) throws Exception;

    List<DailyRechargeReport> selectByMonth(String daily) throws Exception;

    void deleteByTimeAndSourceType(@Param("statisticsDay") String statisticsDay, @Param("sourceType") Byte sourceType) throws Exception;
}