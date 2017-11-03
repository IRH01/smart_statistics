package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface UserInfoMapper{

    UserInfo selectByPrimaryKey(String userId) throws Exception;

    Integer findUserInfoByTime(@Param("endDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;

    List<Map<String, Object>> selectYesterdayRegisterUserIdAndTerminal() throws Exception;

    List<Map<String, Object>> selectRegisterUserIdAndTerminalByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<String> selectOldRegisterUser() throws Exception;

    List<String> selectOldRegisterUserByTime(Date time) throws Exception;

    List<Map<String, Object>> selectBeforeHowManyDayRegisterUser(int days) throws Exception;

    List<Map<String, Object>> selectRegisterUserByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<String> selectUserIdByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectFirstThirtyMinRegister(@Param("endDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;

    Long selectUserCount(Date time) throws Exception;
}