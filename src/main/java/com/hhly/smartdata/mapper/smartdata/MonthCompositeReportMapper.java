package com.hhly.smartdata.mapper.smartdata;

import com.hhly.smartdata.model.smartdata.MonthCompositeReport;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthCompositeReportMapper{
    int insert(MonthCompositeReport record) throws Exception;

    int insertSelective(MonthCompositeReport record) throws Exception;

    MonthCompositeReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MonthCompositeReport record) throws Exception;

    int updateByPrimaryKey(MonthCompositeReport record) throws Exception;
}