package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.MonthRegisterReport;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthRegisterReportMapper{
    int insert(MonthRegisterReport record) throws Exception;

    int insertSelective(MonthRegisterReport record) throws Exception;

    MonthRegisterReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MonthRegisterReport record) throws Exception;

    int updateByPrimaryKey(MonthRegisterReport record) throws Exception;
}