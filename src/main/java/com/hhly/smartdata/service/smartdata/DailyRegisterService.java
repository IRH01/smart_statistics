package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.smartdata.DailyRegisterReportMapper;
import com.hhly.smartdata.model.smartdata.DailyRegisterReport;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyRegisterService{

    @Autowired
    private DailyRegisterReportMapper dailyRegisterReportMapper;


    public JSONObject selectDailyRegisterListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        //查询注册来源统计列表数据
        PageHelper.startPage(pageNumber, pageSize);
        List<DailyRegisterReport> selectIntervalInterfaceToltalDataMap = dailyRegisterReportMapper.selectRegisterDataListByTime(startDate, endDate);
        PageInfo<DailyRegisterReport> pageInfo = new PageInfo<DailyRegisterReport>(selectIntervalInterfaceToltalDataMap);
        return JSONObject.fromObject(pageInfo);
    }

    public JSONObject selectYesterdayRegisterData(String startDate, String endDate) throws Exception{
        DailyRegisterReport dailyRegisterReport = dailyRegisterReportMapper.selectYesterdayRegisterData(startDate, endDate);
        return JSONObject.fromObject(dailyRegisterReport);

    }


}
