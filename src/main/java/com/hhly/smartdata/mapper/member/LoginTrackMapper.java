package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.LoginTrack;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;
import java.util.Map;
@Repository
public interface LoginTrackMapper{
    LoginTrack selectByPrimaryKey(Long id) throws Exception;

    List<Map<String, Object>> selectYesterdayLoginUser() throws Exception;

    List<Map<String, Object>> selectFirstThirtyMinLoginUser(Map<String,Object> map) throws Exception;


}