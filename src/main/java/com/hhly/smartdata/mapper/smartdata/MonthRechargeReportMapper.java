package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.dto.mouth.MonthRechargeReportResult;
import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.model.smartdata.MonthRechargeReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthRechargeReportMapper {
    int insert(MonthRechargeReport record) throws Exception;

    int insertSelective(MonthRechargeReport record) throws Exception;

    MonthRechargeReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MonthRechargeReport record) throws Exception;

    int updateByPrimaryKey(MonthRechargeReport record) throws Exception;

    List<MonthRechargeReportResult> searchByTime(TimeFilter filter) throws Exception;

    long searchByTimeCount(TimeFilter filter) throws Exception;

    List<MonthRechargeReport> selectByMonth(String month) throws Exception;

    void deleteByTimeAndSourceType(@Param("statisticsMonth") String statisticsMonth, @Param("sourceType") Byte sourceType) throws Exception;
}