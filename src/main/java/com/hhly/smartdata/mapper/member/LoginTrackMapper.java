package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.LoginTrack;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;

import java.util.List;

import java.util.List;
import java.util.Map;

public interface LoginTrackMapper{
    LoginTrack selectByPrimaryKey(Long id) throws Exception;

    List<Map<String, Object>> selectYesterdayLoginUser() throws Exception;

    // 查询 各端 注册人数、充值人数、充值金额、充值次数
    List<IntervalSourceReport> findIntervalSourceReportByTime(IntervalSourceReport record);
}