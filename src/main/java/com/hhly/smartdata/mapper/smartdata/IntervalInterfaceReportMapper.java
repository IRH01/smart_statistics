package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.IntervalInterfaceReport;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalInterfaceReportMapper{
    int insert(IntervalInterfaceReport record);

    int insertSelective(IntervalInterfaceReport record);

    IntervalInterfaceReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntervalInterfaceReport record);

    int updateByPrimaryKey(IntervalInterfaceReport record);
}