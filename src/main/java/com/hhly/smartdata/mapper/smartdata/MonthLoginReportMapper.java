package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.MonthLoginReport;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthLoginReportMapper{
    int insert(MonthLoginReport record);

    int insertSelective(MonthLoginReport record);

    MonthLoginReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthLoginReport record);

    int updateByPrimaryKey(MonthLoginReport record);
}