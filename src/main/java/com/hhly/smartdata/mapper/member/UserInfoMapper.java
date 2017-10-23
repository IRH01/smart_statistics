package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface UserInfoMapper{

    UserInfo selectByPrimaryKey(String userId) throws Exception;

    Integer findUserInfoByTime(Map<String,Object> map) throws Exception;

    List<Map<String, Object>> selectYesterdayRegisterUserIdAndTerminal() throws Exception;

    List<String> selectOldRegisterUser() throws Exception;

    List<String> selectBeforeHowManyDayRegisterUser(int days);

    List<Map<String, Object>> selectFirstThirtyMinRegister(Map<String,Object> map) throws Exception;

    Long selectUserCount(Date time) throws Exception;
}