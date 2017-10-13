package com.hhly.smartdata.service.month;

import com.hhly.smartdata.dto.mouth.LoginStatisticsFilter;
import com.hhly.smartdata.dto.mouth.MonthRechargeReportResult;
import com.hhly.smartdata.dto.mouth.TimeFilter;
import com.hhly.smartdata.mapper.smartdata.MonthRechargeReportMapper;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
