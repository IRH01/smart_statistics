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

    List<Map<String, Object>> selectUserViewByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectUserViewAndPageViewGroupBySourceType(@Param("startTime") String startTime, @Param("endTime") String endTime) throws Exception;

    List<Map<String, Object>> selectYesterdayUserViewAndPageViewList(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectTodayGameStartCountGroupByUser(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectTodayGameStartCountGroupByUserAndOsType(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<String> selectUserViewAndPageViewByTime(@Param("startTime") String startTime, @Param("endTime") String endTime) throws Exception;

    List<Map<String, Object>> selectUserViewAndPageViewByTimeGroupBySource(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Map<String, Object>> selectPageViewByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;
}