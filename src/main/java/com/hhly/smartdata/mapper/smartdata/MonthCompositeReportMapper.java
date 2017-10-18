package com.hhly.smartdata.mapper.smartdata;

import com.hhly.smartdata.dto.mouth.MonthCompositeReportResult;
import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.model.smartdata.MonthCompositeReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthCompositeReportMapper{
    int insert(MonthCompositeReport record) throws Exception;

    int insertSelective(MonthCompositeReport record) throws Exception;

    MonthCompositeReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MonthCompositeReport record) throws Exception;

    int updateByPrimaryKey(MonthCompositeReport record) throws Exception;

    List<MonthCompositeReportResult> searchByTime(TimeFilter filter) throws Exception;

    long searchByTimeCount(TimeFilter filter) throws Exception;

    void deleteByTime(String statisticsMonth) throws Exception;
}