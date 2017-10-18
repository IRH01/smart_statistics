package com.hhly.smartdata.service.daily;

import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.mapper.smartdata.DailyLoginReportMapper;
import com.hhly.smartdata.model.smartdata.DailyLoginReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Iritchie.ren on 2017/10/17.
 */
@Service
public class DailyLoginStatisticsService{

    @Autowired
    private DailyLoginReportMapper dailyLoginReportMapper;

    public Pagination search(TimeFilter filter) throws Exception{
        Pagination pagination = new Pagination();
        List<DailyLoginReport> dailyLoginReportList = dailyLoginReportMapper.searchByTime(filter);
        long count = filter.getPageNo() != null ? dailyLoginReportMapper.searchByTimeCount(filter) / 12 : dailyLoginReportList.size();
        pagination.setData(dailyLoginReportList);
        pagination.setTotal(count);
        return pagination;
    }

    public Map<String, Object> getLastTotalRegister() throws Exception{
        Date now = new Date();
        String month = DateUtil.getYesterdayStr(now);
        Map<String, Object> dailyLoginReportMap = this.dailyLoginReportMapper.selectByMonth(month);
        return dailyLoginReportMap;
    }
}
