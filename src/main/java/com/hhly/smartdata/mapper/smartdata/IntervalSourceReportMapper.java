package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervalSourceReportMapper{
    int insert(IntervalSourceReport record) throws Exception;

    IntervalSourceReport selectByPrimaryKey(Long id) throws Exception;

}