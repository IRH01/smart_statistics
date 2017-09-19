package com.hhly.generator.dao;

import com.hhly.generator.model.IntervalSourceReport;

public interface IntervalSourceReportMapper {
    int insert(IntervalSourceReport record);

    int insertSelective(IntervalSourceReport record);

    IntervalSourceReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntervalSourceReport record);

    int updateByPrimaryKey(IntervalSourceReport record);
}