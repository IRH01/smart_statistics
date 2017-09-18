package com.hhly.smartdata.mapper.smartdata;

import com.hhly.smartdata.model.smartdata.MonthCompositeReport;

public interface MonthCompositeReportMapper{
    int insert(MonthCompositeReport record);

    int insertSelective(MonthCompositeReport record);

    MonthCompositeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthCompositeReport record);

    int updateByPrimaryKey(MonthCompositeReport record);
}