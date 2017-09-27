package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervalSourceReportMapper{
    int insert(IntervalSourceReport record) throws Exception;

    // 查询 各端 注册人数、充值人数、充值金额、充值次数
    List<IntervalSourceReport>  findIntervalSourceReportByTime(IntervalSourceReport record) throws Exception;

    // 启动表各端登陆人数
    List<IntervalSourceReport>  findDataPlatformStartBySourceTypeAndTime(IntervalSourceReport record) throws Exception;

    // UV表各端登陆人数
    List<IntervalSourceReport>  findDataViewBySourceTypeAndTime(IntervalSourceReport record) throws Exception;

    int insertSelective(IntervalSourceReport record) throws Exception;

    IntervalSourceReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(IntervalSourceReport record) throws Exception;

    int updateByPrimaryKey(IntervalSourceReport record) throws Exception;
}