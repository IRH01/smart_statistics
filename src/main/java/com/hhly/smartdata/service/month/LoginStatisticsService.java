package com.hhly.smartdata.service.month;

import com.hhly.smartdata.dto.mouth.LoginStatisticsFilter;
import com.hhly.smartdata.mapper.smartdata.MonthLoginReportMapper;
import com.hhly.smartdata.model.smartdata.MonthLoginReport;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@Service
public class LoginStatisticsService{

    @Autowired
    private MonthLoginReportMapper monthLoginReportMapper;

    public Pagination search(LoginStatisticsFilter filter) throws Exception{
        Pagination pagination = new Pagination();
        List<MonthLoginReport> monthCompositeReportResultList = monthLoginReportMapper.searchByTime(filter);
        long count = filter.getPageNo() != null ? monthLoginReportMapper.searchByTimeCount(filter) : monthCompositeReportResultList.size();
        pagination.setData(monthCompositeReportResultList);
        pagination.setTotal(count);
        return pagination;
    }
}
