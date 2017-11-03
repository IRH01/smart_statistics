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

    List<Map<String, Object>> selectYesterdayLoginUser() throws Exception;

    List<Map<String, Object>> selectLoginUserGroupByUserAndOsType(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectLoginUserByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectFirstThirtyMinLoginUser(@Param("endDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;
}