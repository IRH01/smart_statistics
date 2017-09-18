package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalSourceReportMapper{
    int insert(IntervalSourceReport record);

    int insertSelective(IntervalSourceReport record);

    IntervalSourceReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntervalSourceReport record);

    int updateByPrimaryKey(IntervalSourceReport record);
}