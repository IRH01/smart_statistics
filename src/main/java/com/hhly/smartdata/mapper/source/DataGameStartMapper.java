package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataGameStart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface DataGameStartMapper{
    int insert(DataGameStart record) throws Exception;

    int insertSelective(DataGameStart record) throws Exception;

    DataGameStart selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataGameStart record) throws Exception;

    int updateByPrimaryKey(DataGameStart record) throws Exception;

    List<Map<String, Object>> selectLaunchGameUserByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectPlatformAllGameStartCount(@Param("endDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;

    List<Map<String, Object>> selectGameStartGroupBySourceType(@Param("startTime") String startTime, @Param("endTime") String endTime) throws Exception;

    List<Map<String, Object>> selectYesterdayGameStartList(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectTodayGameStartCountGroupByUser(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectTodayGameStartCountGroupByUserAndOsType(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<String> selectGameStartByTime(@Param("startTime") String startTime, @Param("endTime") String endTime) throws Exception;

    List<Map<String,Object>> selectGameStartByTimeGroupBySource(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

}