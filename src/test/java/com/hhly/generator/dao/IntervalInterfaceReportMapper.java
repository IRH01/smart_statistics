package com.hhly.generator.dao;

import com.hhly.generator.model.IntervalInterfaceReport;

public interface IntervalInterfaceReportMapper {
    int insert(IntervalInterfaceReport record);

    int insertSelective(IntervalInterfaceReport record);

    IntervalInterfaceReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntervalInterfaceReport record);

    int updateByPrimaryKey(IntervalInterfaceReport record);
}