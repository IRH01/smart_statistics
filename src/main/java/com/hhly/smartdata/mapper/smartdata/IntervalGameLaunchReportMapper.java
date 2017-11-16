package com.hhly.smartdata.mapper.smartdata;


import com.hhly.smartdata.dto.current.IntervalGameLaunchTimeListReport;
import com.hhly.smartdata.model.smartdata.IntervalGameLaunchReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervalGameLaunchReportMapper{
    int insert(IntervalGameLaunchReport record) throws Exception;

    int insertSelective(IntervalGameLaunchReport record) throws Exception;

    IntervalGameLaunchReport selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(IntervalGameLaunchReport record) throws Exception;

    int updateByPrimaryKey(IntervalGameLaunchReport record) throws Exception;

    void deleteByTimeAndSourceTypeAndPlatformCode(@Param("statisticsTime") String statisticsTime, @Param("sourceType") Byte sourceType, @Param("platformCode") String platformCode);

    /**
     * 根据平台编码获取游戏启动次数
     *
     * @param platformCode
     * @param sourceType
     * @return
     * @throws Exception
     * @author hejh
     * @date 2017-11-01 20:39
     */
    List<IntervalGameLaunchTimeListReport> selectIntervalGameLaunchTimeListData(@Param("platformCode") String platformCode, @Param("sourceType") String sourceType) throws Exception;

}