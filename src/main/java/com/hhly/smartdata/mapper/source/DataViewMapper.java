package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface DataViewMapper{
    int insert(DataView record) throws Exception;

    int insertSelective(DataView record) throws Exception;

    DataView selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataView record) throws Exception;

    int updateByPrimaryKey(DataView record) throws Exception;

    List<Map<String, Object>> selectUserViewAndPageViewByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectFirstThirtyMinUserViewAndPageView(@Param("endDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;

    List<Map<String, Object>> selectYesterdayUserViewAndPageViewList(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;
}