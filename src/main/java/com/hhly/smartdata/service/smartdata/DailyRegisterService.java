package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.smartdata.DailyRegisterReportMapper;
import com.hhly.smartdata.model.smartdata.DailyRegisterReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyRegisterService{

    @Autowired
    private DailyRegisterReportMapper dailyRegisterReportMapper;


    public PageInfo<DailyRegisterReport> selectDailyRegisterListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        //查询注册来源统计列表数据
        PageHelper.startPage(pageNumber, pageSize);
        List<DailyRegisterReport> selectIntervalInterfaceToltalDataMap = dailyRegisterReportMapper.selectRegisterDataListByTime(startDate, endDate);
        return new PageInfo<>(selectIntervalInterfaceToltalDataMap);
    }

    public DailyRegisterReport selectYesterdayRegisterData(String startDate, String endDate) throws Exception{
        return dailyRegisterReportMapper.selectYesterdayRegisterData(startDate, endDate);

    }


}
