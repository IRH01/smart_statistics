package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.RechargeRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RechargeRecordMapper {

    RechargeRecord selectByPrimaryKey(String orderno) throws Exception;

    /**
     * 返回结果键值对
     * {<source,int></><userCount,Long>,<amountSum,bigDecimal>，<orderCount,Long>}
     */
    List<Map<String, Object>> selectYesterday() throws Exception;

    /**
     * 返回结果键值对
     * {<userCount,Long>,<amountSum,bigDecimal>，<orderCount,Long>}
     */
    List<Map<String, Object>> selectToday() throws Exception;

    List<Map<String, Object>> selectYesterdayNewUser() throws Exception;

    List<Map<String, Object>> selectYesterdayOldUser() throws Exception;

    Integer findRechargeRecordByTime(@Param("startDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;

    List<Map<String, Object>> selectYesterdayRechargeUser() throws Exception;

    List<Map<String, Object>> selectFirstThirtyMinRechargeUser(@Param("startDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;
}