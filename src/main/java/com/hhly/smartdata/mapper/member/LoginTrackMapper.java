package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.LoginTrack;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LoginTrackMapper {
    LoginTrack selectByPrimaryKey(Long id) throws Exception;

    List<Map<String, Object>> selectYesterdayLoginUser() throws Exception;

    List<Map<String, Object>> selectFirstThirtyMinLoginUser(@Param("startDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;
}