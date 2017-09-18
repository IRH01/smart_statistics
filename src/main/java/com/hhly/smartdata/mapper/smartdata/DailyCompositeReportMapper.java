package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.DailyCompositeReport;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyCompositeReportMapper{
    int insert(DailyCompositeReport record);

    int insertSelective(DailyCompositeReport record);

    DailyCompositeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyCompositeReport record);

    int updateByPrimaryKey(DailyCompositeReport record);
}