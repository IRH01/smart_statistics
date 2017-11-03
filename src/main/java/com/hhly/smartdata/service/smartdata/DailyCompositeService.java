package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.dto.daily.DailyCompositeReportResult;
import com.hhly.smartdata.mapper.smartdata.DailyCompositeReportMapper;
import com.hhly.smartdata.model.smartdata.DailyCompositeReport;
import com.hhly.smartdata.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyCompositeService{

    @Autowired
    private DailyCompositeReportMapper dailyCompositeReportMapper;


    public PageInfo<DailyCompositeReportResult> selectDailyCompositeListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        PageHelper.startPage(pageNumber, pageSize);
        List<DailyCompositeReportResult> dailyCompositeReportList = dailyCompositeReportMapper.selectDailyCompositeListData(startDate, endDate);
        for(DailyCompositeReportResult item : dailyCompositeReportList){
            //根据日期字符串，获取昨天天的日期字符串。
            String yesterdayStr = DateUtil.getYesterdayStr(item.getStatisticsDay());
            DailyCompositeReport yesterdayCompositeReport = this.dailyCompositeReportMapper.selectByDaily(yesterdayStr);
            //计算次日留存率
            item.setNextDayKeepRate(item.getNextDayStayCount(), yesterdayCompositeReport == null ? 0 : yesterdayCompositeReport.getRegisterPopulation());
        }
        return new PageInfo<>(dailyCompositeReportList);
    }

}
