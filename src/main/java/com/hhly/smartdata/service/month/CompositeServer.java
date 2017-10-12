package com.hhly.smartdata.service.month;

import com.hhly.smartdata.dto.mouth.TimeFilter;
import com.hhly.smartdata.dto.mouth.MonthCompositeReportResult;
import com.hhly.smartdata.mapper.smartdata.MonthCompositeReportMapper;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@Service
public class CompositeServer{

    @Autowired
    private MonthCompositeReportMapper monthCompositeReportMapper;

    public Pagination search(TimeFilter filter) throws Exception{
        Pagination pagination = new Pagination();
        List<MonthCompositeReportResult> monthCompositeReportResultList = monthCompositeReportMapper.searchByTime(filter);
        long count = filter.getPageNo() != null ? monthCompositeReportMapper.searchByTimeCount(filter) : monthCompositeReportResultList.size();
        pagination.setData(monthCompositeReportResultList);
        pagination.setTotal(count);
        return pagination;
    }
}
