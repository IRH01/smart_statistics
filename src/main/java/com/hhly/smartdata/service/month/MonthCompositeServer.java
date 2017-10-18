package com.hhly.smartdata.service.month;

import com.hhly.smartdata.dto.mouth.MonthCompositeReportResult;
import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.mapper.smartdata.DailyKeepRecordReportMapper;
import com.hhly.smartdata.mapper.smartdata.MonthCompositeReportMapper;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@Service
public class MonthCompositeServer{

    @Autowired
    private MonthCompositeReportMapper monthCompositeReportMapper;

    @Autowired
    private DailyKeepRecordReportMapper dailyKeepRecordReportMapper;

    public Pagination search(TimeFilter filter) throws Exception{
        Pagination pagination = new Pagination();
        List<MonthCompositeReportResult> monthCompositeReportResultList = monthCompositeReportMapper.searchByTime(filter);
        for(MonthCompositeReportResult item : monthCompositeReportResultList){
            String startDay = DateUtil.getMonthFirstDayStr(item.getStatisticsMonth());
            String endDay = DateUtil.getMonthEndDayStr(item.getStatisticsMonth());
            Float avg = this.dailyKeepRecordReportMapper.selectAverageOneKeep(startDay, endDay);
            item.setNextDayKeepRate(avg == null ? 0F : avg);
        }
        long count = filter.getPageNo() != null ? monthCompositeReportMapper.searchByTimeCount(filter) : monthCompositeReportResultList.size();
        pagination.setData(monthCompositeReportResultList);
        pagination.setTotal(count);
        return pagination;
    }
}
