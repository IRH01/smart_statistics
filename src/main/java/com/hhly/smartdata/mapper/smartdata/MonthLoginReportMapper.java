package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.MonthLoginReport;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthLoginReportMapper{
    int insert(MonthLoginReport record) throws Exception;

    int insertSelective(MonthLoginReport record) throws Exception;

    MonthLoginReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MonthLoginReport record) throws Exception;

    int updateByPrimaryKey(MonthLoginReport record) throws Exception;
}