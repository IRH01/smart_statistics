package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.IntervalInterfaceReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervalInterfaceReportMapper{
    int insert(IntervalInterfaceReport record) throws Exception;

    int insertSelective(IntervalInterfaceReport record) throws Exception;

    IntervalInterfaceReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(IntervalInterfaceReport record) throws Exception;

    int updateByPrimaryKey(IntervalInterfaceReport record) throws Exception;

    List<IntervalInterfaceReport> selectIntervalInterfaceTotalData(@Param("startDate") String startDate, @Param("endDate") String endDate) throws Exception;

    List<IntervalInterfaceReport> selectIntervalInterfaceChartData(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("interfaceCode") String interfaceCode) throws Exception;

    void deleteByTimeAndOperateTypeAndInterfaceCode(@Param("statisticsTime") String statisticsTime, @Param("operateType") Byte operateType, @Param("interfaceCode") Integer interfaceCode) throws Exception;
}



