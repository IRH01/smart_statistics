package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.hhly.smartdata.dto.daily.DailyKeepRecordReportResult;
import com.hhly.smartdata.mapper.smartdata.DailyKeepRecordReportMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DailyKeepRecordService{

    @Autowired
    private DailyKeepRecordReportMapper dailyKeepRecordReportMapper;

    public JSONObject selectDailyKeepRecordListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        PageHelper.startPage(pageNumber, pageSize);
        List<DailyKeepRecordReportResult> dailyKeepRecordList = dailyKeepRecordReportMapper.selectDailyKeepRecordListData(paramMap);
        PageInfo<DailyKeepRecordReportResult> pageInfo = new PageInfo<DailyKeepRecordReportResult>(dailyKeepRecordList);
        return JSONObject.fromObject(pageInfo);
    }
}
