package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.dto.daily.DailyKeepRecordReportResult;
import com.hhly.smartdata.mapper.smartdata.DailyKeepRecordReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyKeepRecordService{

    @Autowired
    private DailyKeepRecordReportMapper dailyKeepRecordReportMapper;

    public PageInfo<DailyKeepRecordReportResult> selectDailyKeepRecordListData(String startDate, String endDate, int pageNumber, int pageSize,Integer  sourceType) throws Exception{
        PageHelper.startPage(pageNumber, pageSize);
        List<DailyKeepRecordReportResult> dailyKeepRecordList = dailyKeepRecordReportMapper.selectDailyKeepRecordListData(startDate, endDate, sourceType);
        return new PageInfo<>(dailyKeepRecordList);
    }
}
