package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.MonthRegisterReport;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthRegisterReportMapper{
    int insert(MonthRegisterReport record);

    int insertSelective(MonthRegisterReport record);

    MonthRegisterReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthRegisterReport record);

    int updateByPrimaryKey(MonthRegisterReport record);
}