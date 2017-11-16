package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.DailyRegisterReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DailyRegisterReportMapper {
    int insert(DailyRegisterReport record) throws Exception;

    int insertSelective(DailyRegisterReport record) throws Exception;

    DailyRegisterReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DailyRegisterReport record) throws Exception;

    int updateByPrimaryKey(DailyRegisterReport record) throws Exception;

    List<DailyRegisterReport> selectRegisterDataListByTime(@Param("startDate") String startDate, @Param("endDate") String endDate) throws Exception;

    DailyRegisterReport selectYesterdayRegisterData(@Param("startDate") String startDate, @Param("endDate") String endDate) throws Exception;

    void deleteByTime(String statisticsDay) throws Exception;
}