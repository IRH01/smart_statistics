package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.RechargeRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface RechargeRecordMapper{

    RechargeRecord selectByPrimaryKey(String orderno) throws Exception;

    /**
     * 返回结果键值对
     * {<source,int></><userCount,Long>,<amountSum,bigDecimal>，<orderCount,Long>}
     */
    List<Map<String, Object>> selectByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectNewUserAndRechargeByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectOldUserAndRechargeByStartTimeAndEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    Integer findRechargeRecordByTime(@Param("endDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;

    List<Map<String, Object>> selectRechargeUserByTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectRechargeGroupBySourceType(@Param("startTime") String startTime, @Param("endTime") String endTime) throws Exception;

    List<Map<String, Object>> selectRechargeUserGroupByUserAndOsType(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectRechargeUserGroupByUser(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<Map<String, Object>> selectRechargeRecordByTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

}