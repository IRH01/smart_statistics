package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper{

    UserInfo selectByPrimaryKey(String userId) throws Exception;

    Integer findUserInfoByTime() throws Exception;

    List<String> selectYesterdayRegisterUser() throws Exception;

    List<String> selectOldRegisterUser() throws Exception;

    List<String> selectBeforeYesterdayRegisterUser() throws Exception;
}