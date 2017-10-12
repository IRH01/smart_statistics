package com.hhly.smartdata.service.month;

import com.hhly.smartdata.dto.mouth.TimeFilter;
import com.hhly.smartdata.mapper.smartdata.MonthRegisterReportMapper;
import com.hhly.smartdata.model.smartdata.MonthRegisterReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Iritchie.ren on 2017/10/12.
 */
@Service
public class RegisterStatisticsService{

    @Autowired
    private MonthRegisterReportMapper monthRegisterReportMapper;

    public Pagination search(TimeFilter filter) throws Exception{
        Pagination pagination = new Pagination();
        List<MonthRegisterReport> monthRegisterReportList = this.monthRegisterReportMapper.searchByTime(filter);
        long count = filter.getPageNo() != null ? this.monthRegisterReportMapper.searchByTimeCount(filter) : monthRegisterReportList.size();
        pagination.setData(monthRegisterReportList);
        pagination.setTotal(count);
        return pagination;
    }

    public long getLastTotalRegister() throws Exception{
        Date now = new Date();
        String month = DateUtil.getLastMonthStr(now);
        MonthRegisterReport monthRegisterReport = this.monthRegisterReportMapper.selectByMonth(month);
        long total = 0L;
        if(monthRegisterReport != null){
            total = monthRegisterReport.getPcPopulation() + monthRegisterReport.getH5Population() + monthRegisterReport.getIosPopulation() + monthRegisterReport.getAndroidPopulation();
        }
        return total;
    }
}
