package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.DailyCompositeReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DailyCompositeReportMapper{
    int insert(DailyCompositeReport record) throws Exception;

    int insertSelective(DailyCompositeReport record) throws Exception;

    DailyCompositeReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DailyCompositeReport record) throws Exception;

    int updateByPrimaryKey(DailyCompositeReport record) throws Exception;

    Map<String, Object> selectLastMonthComposite(@Param("lastMonthFirstDayStr") String lastMonthFirstDayStr, @Param("lastMonthEndDayStr") String lastMonthEndDayStr) throws Exception;

    List<DailyCompositeReport> selectDailyCompositeListData(Map<String, Object> map) throws Exception;

    void deleteByDaily(String daily) throws Exception;
}