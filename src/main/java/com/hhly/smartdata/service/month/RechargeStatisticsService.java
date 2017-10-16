package com.hhly.smartdata.service.month;

import com.hhly.smartdata.dto.mouth.MonthRechargeReportResult;
import com.hhly.smartdata.dto.mouth.TimeFilter;
import com.hhly.smartdata.mapper.smartdata.MonthRechargeReportMapper;
import com.hhly.smartdata.model.smartdata.MonthRechargeReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@Service
public class RechargeStatisticsService{

    @Autowired
    private MonthRechargeReportMapper monthRechargeReportMapper;

    public Pagination search(TimeFilter filter) throws Exception{
        Pagination pagination = new Pagination();
        List<MonthRechargeReportResult> monthRechargeReportResultList = this.monthRechargeReportMapper.searchByTime(filter);
        long count = filter.getPageNo() != null ? (this.monthRechargeReportMapper.searchByTimeCount(filter) / 4) : monthRechargeReportResultList.size();
        pagination.setData(monthRechargeReportResultList);
        pagination.setTotal(count);
        return pagination;
    }

    public BigDecimal getLastTotalRecharge() throws Exception{
        Date now = new Date();
        String month = DateUtil.getLastMonthStr(now);
        List<MonthRechargeReport> monthRechargeReportList = this.monthRechargeReportMapper.selectByMonth(month);
        BigDecimal total = new BigDecimal(0.00);
        if(monthRechargeReportList != null){
            for(MonthRechargeReport item : monthRechargeReportList){
                total = total.add(item.getRechargeAmount());
            }
        }
        return total;
    }
}
