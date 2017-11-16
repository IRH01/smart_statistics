package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.LoginTrack;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface LoginTrackMapper{
    LoginTrack selectByPrimaryKey(Long id) throws Exception;

    List<Map<String, Object>> selectLoginUserGroupByUserAndOsType(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectLoginUserGroupByUser(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectLoginUserPopulationByTimeGroupBySource(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectLoginUserGroupBySourceType(@Param("startTime") String startTime, @Param("endTime") String endTime) throws Exception;

    List<String> selectLoginUserPopulationByTime(@Param("startTime") String startTime, @Param("endTime") String endTime) throws Exception;

}