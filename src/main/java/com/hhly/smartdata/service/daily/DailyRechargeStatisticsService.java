package com.hhly.smartdata.service.daily;

import com.hhly.smartdata.dto.daily.DailyRechargeReportResult;
import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.mapper.smartdata.DailyRechargeReportMapper;
import com.hhly.smartdata.model.smartdata.DailyRechargeReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Iritchie.ren on 2017/10/16.
 */
@Service
public class DailyRechargeStatisticsService{

    @Autowired
    private DailyRechargeReportMapper dailyRechargeReportMapper;

    public Pagination search(TimeFilter filter) throws Exception{
        Pagination pagination = new Pagination();
        List<DailyRechargeReportResult> dailyRechargeReportResultList = this.dailyRechargeReportMapper.searchByTime(filter);
        long count = filter.getPageNo() != null ? (this.dailyRechargeReportMapper.searchByTimeCount(filter) / 4) : dailyRechargeReportResultList.size();
        pagination.setData(dailyRechargeReportResultList);
        pagination.setTotal(count);
        return pagination;
    }

    public BigDecimal getLastTotalRecharge() throws Exception{
        Date now = new Date();
        String month = DateUtil.getYesterdayStr(now);
        List<DailyRechargeReport> monthRechargeReportList = this.dailyRechargeReportMapper.selectByMonth(month);
        BigDecimal total = new BigDecimal(0.00);
        if(monthRechargeReportList != null){
            for(DailyRechargeReport item : monthRechargeReportList){
                total = total.add(item.getRechargeAmount());
            }
        }
        return total;
    }
}
