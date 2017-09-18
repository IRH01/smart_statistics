package com.hhly.smartdata.mapper.smartdata;

import com.hhly.smartdata.model.smartdata.DailyLoginReport;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyLoginReportMapper{
    int insert(DailyLoginReport record);

    int insertSelective(DailyLoginReport record);

    DailyLoginReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyLoginReport record);

    int updateByPrimaryKey(DailyLoginReport record);
}