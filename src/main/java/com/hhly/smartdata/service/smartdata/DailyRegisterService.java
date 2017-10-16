package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.smartdata.DailyRegisterReportMapper;
import com.hhly.smartdata.model.smartdata.DailyRegisterReport;
import com.hhly.smartdata.model.smartdata.IntervalInterfaceReport;
import com.hhly.smartdata.util.Result;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DailyRegisterService{

    @Autowired
    private DailyRegisterReportMapper dailyRegisterReportMapper;



    public JSONObject  selectDailyRegisterListData(String startDate, String endDate,int pageNumber,int pageSize) throws Exception{
        //查询注册来源统计列表数据
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        PageHelper.startPage(1, 20);
        List<DailyRegisterReport> selectIntervalInterfaceToltalDataMap = dailyRegisterReportMapper.selectRegisterDataListByTime(paramMap);
        PageInfo<DailyRegisterReport> pageInfo = new PageInfo<DailyRegisterReport>(selectIntervalInterfaceToltalDataMap);
        return JSONObject.fromObject(pageInfo);
    }

    public JSONObject  selectYesterdayRegisterData(String startDate, String endDate) throws Exception{
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        DailyRegisterReport dailyRegisterReport = dailyRegisterReportMapper.selectYesterdayRegisterData(paramMap);
        return JSONObject.fromObject(dailyRegisterReport);

    }


}
