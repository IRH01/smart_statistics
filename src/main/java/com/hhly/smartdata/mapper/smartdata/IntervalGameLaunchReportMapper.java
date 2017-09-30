package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.model.smartdata.IntervalGameLaunchReport;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalGameLaunchReportMapper{
    int insert(IntervalGameLaunchReport record) throws Exception;

    int insertSelective(IntervalGameLaunchReport record) throws Exception;

    IntervalGameLaunchReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(IntervalGameLaunchReport record) throws Exception;

    int updateByPrimaryKey(IntervalGameLaunchReport record) throws Exception;


}