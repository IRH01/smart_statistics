package com.hhly.smartdata.mapper.member;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface PlatformGoldConsumeMapper{

    List<Map<String, Object>> selectYesterdayConsumeUser() throws Exception;

    List<Map<String, Object>> selectConsumeUserByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

}