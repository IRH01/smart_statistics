package com.hhly.smartdata.service.schedule;

import com.google.common.collect.Lists;
import com.hhly.smartdata.mapper.smartdata.*;
import com.hhly.smartdata.model.smartdata.MonthCompositeReport;
import com.hhly.smartdata.model.smartdata.MonthLoginReport;
import com.hhly.smartdata.model.smartdata.MonthRechargeReport;
import com.hhly.smartdata.model.smartdata.MonthRegisterReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Iritchie.ren on 2017/9/26.
 */
@Service
public class MonthExecutorService{

    @Autowired
    private DailyCompositeReportMapper dailyCompositeReportMapper;

    @Autowired
    private DailyRegisterReportMapper dailyRegisterReportMapper;

    @Autowired
    private DailyRechargeReportMapper dailyRechargeReportMapper;

    @Autowired
    private DailyLoginReportMapper dailyLoginReportMapper;

    @Autowired
    private MonthCompositeReportMapper monthCompositeReportMapper;

    @Autowired
    private MonthRegisterReportMapper monthRegisterReportMapper;

    @Autowired
    private MonthRechargeReportMapper monthRechargeReportMapper;

    @Autowired
    private MonthLoginReportMapper monthLoginReportMapper;

    public Result compositeReport() throws Exception{
        Date now = new Date();
        Map<String, Object> lastMonthCompositeMap = this.dailyCompositeReportMapper.selectLastMonthComposite();
        if(lastMonthCompositeMap == null){
            return Result.fail("没有需要统计的数据");
        }
        MonthCompositeReport monthCompositeReport = new MonthCompositeReport();

        monthCompositeReport.setExecuteTime(now);
        monthCompositeReport.setStatisticsMonth(DateUtil.getLastMonthStr(now));

        monthCompositeReport.setNewUserLoginCount(((BigDecimal) lastMonthCompositeMap.get("new_user_login_count_sum")).intValue());
        monthCompositeReport.setNewUserPlayCount(((BigDecimal) lastMonthCompositeMap.get("new_user_play_count_sum")).intValue());
        monthCompositeReport.setVirtualExpCount(((BigDecimal) lastMonthCompositeMap.get("virtual_exp_count_sum")).intValue());
        monthCompositeReport.setRegisterPopulation(((BigDecimal) lastMonthCompositeMap.get("register_population_sum")).intValue());
        monthCompositeReport.setRegisterExpCount(((BigDecimal) lastMonthCompositeMap.get("register_exp_count_sum")).intValue());
        monthCompositeReport.setRealExpCount(((BigDecimal) lastMonthCompositeMap.get("real_exp_count_sum")).intValue());
        monthCompositeReport.setOldUserRechargePopulation(((BigDecimal) lastMonthCompositeMap.get("old_user_recharge_population_sum")).intValue());
        monthCompositeReport.setOldUserRechargeAmount((BigDecimal) lastMonthCompositeMap.get("old_user_recharge_amount_sum"));
        monthCompositeReport.setOldUserRechargeCount(((BigDecimal) lastMonthCompositeMap.get("old_user_recharge_count_sum")).intValue());
        monthCompositeReport.setOldUserPlayCount(((BigDecimal) lastMonthCompositeMap.get("old_user_play_count_sum")).intValue());
        monthCompositeReport.setOldUserLoginCount(((BigDecimal) lastMonthCompositeMap.get("old_user_login_count_sum")).intValue());
        monthCompositeReport.setNextDayStayCount(((BigDecimal) lastMonthCompositeMap.get("next_day_stay_count_sum")).intValue());
        monthCompositeReport.setNewUserRechargePopulation(((BigDecimal) lastMonthCompositeMap.get("new_user_recharge_population_sum")).intValue());
        monthCompositeReport.setNewUserRechargeCount(((BigDecimal) lastMonthCompositeMap.get("new_user_recharge_count_sum")).intValue());
        monthCompositeReport.setNewUserRechargeAmount((BigDecimal) lastMonthCompositeMap.get("new_user_recharge_amount"));
        this.monthCompositeReportMapper.insert(monthCompositeReport);
        return Result.success(monthCompositeReport);
    }

    public Result registerReport() throws Exception{
        Map<String, Object> lastMonthRegisterMap = this.dailyRegisterReportMapper.selectLastMonthRegister();
        if(lastMonthRegisterMap == null){
            return Result.fail("没有需要统计的数据");
        }

        Date now = new Date();
        MonthRegisterReport monthRegisterReport = new MonthRegisterReport();
        monthRegisterReport.setExecuteTime(now);
        monthRegisterReport.setStatisticsMonth(DateUtil.getLastMonthStr(now));
        monthRegisterReport.setAndroidInstallCount(((BigDecimal) lastMonthRegisterMap.get("android_install_count_sum")).intValue());
        monthRegisterReport.setPcUserView(((BigDecimal) lastMonthRegisterMap.get("pc_user_view_sum")).intValue());
        monthRegisterReport.setIosPopulation(((BigDecimal) lastMonthRegisterMap.get("ios_population_sum")).intValue());
        monthRegisterReport.setIosInstallCount(((BigDecimal) lastMonthRegisterMap.get("ios_install_count_sum")).intValue());
        monthRegisterReport.setH5UserView(((BigDecimal) lastMonthRegisterMap.get("h5_user_view_sum")).intValue());
        monthRegisterReport.setH5Population(((BigDecimal) lastMonthRegisterMap.get("h5_population_sum")).intValue());
        monthRegisterReport.setH5PageView(((BigDecimal) lastMonthRegisterMap.get("h5_page_view_sum")).longValue());
        monthRegisterReport.setAndroidPopulation(((BigDecimal) lastMonthRegisterMap.get("android_population_sum")).intValue());

        this.monthRegisterReportMapper.insert(monthRegisterReport);
        return Result.success(monthRegisterReport);
    }

    public Result rechargeReport() throws Exception{
        List<Map<String, Object>> lastMonthRechargeList = this.dailyRechargeReportMapper.selectLastMonthRecharge();
        if(CollectionUtils.isEmpty(lastMonthRechargeList)){
            return Result.fail("没有需要统计的数据");
        }

        Date now = new Date();
        String monthStr = DateUtil.getLastMonthStr(now);
        List<MonthRechargeReport> monthRechargeReportList = Lists.newArrayList();
        for(Map<String, Object> map : lastMonthRechargeList){
            MonthRechargeReport monthRechargeReport = new MonthRechargeReport();
            monthRechargeReport.setStatisticsMonth(monthStr);
            monthRechargeReport.setExecuteTime(now);
            monthRechargeReport.setSourceType(((Integer) map.get("source_type")).byteValue());
            monthRechargeReport.setRechargePopulation(((BigDecimal) map.get("recharge_population_sum")).intValue());
            monthRechargeReport.setRechargeAmount((BigDecimal) map.get("recharge_amount_sum"));
            monthRechargeReport.setRechargeCount(((BigDecimal) map.get("recharge_count_sum")).intValue());
            monthRechargeReport.setNewRechargePopulation(((BigDecimal) map.get("new_recharge_population_sum")).intValue());
            monthRechargeReport.setOldRechargePopulation(((BigDecimal) map.get("old_recharge_population_sum")).intValue());
            this.monthRechargeReportMapper.insert(monthRechargeReport);
            monthRechargeReportList.add(monthRechargeReport);
        }

        return Result.success(monthRechargeReportList);
    }

    public Result loginSourceReport() throws Exception{
        List<Map<String, Object>> lastMonthLoginList = this.dailyLoginReportMapper.selectLastMonthLogin();
        if(CollectionUtils.isEmpty(lastMonthLoginList)){
            return Result.fail("没有需要统计的数据");
        }

        Date now = new Date();
        String monthStr = DateUtil.getLastMonthStr(now);
        List<MonthLoginReport> monthRechargeReportList = Lists.newArrayList();
        for(Map<String, Object> map : lastMonthLoginList){
            MonthLoginReport monthLoginReport = new MonthLoginReport();
            monthLoginReport.setExecuteTime(now);
            monthLoginReport.setStatisticsMonth(monthStr);
            monthLoginReport.setPlatformId((Integer) map.get("platform_id"));
            monthLoginReport.setSourceType(((Integer) map.get("source_type")).byteValue());
            monthLoginReport.setPlatformName((String) map.get("platform_name"));
            monthLoginReport.setPlayPopulation(((BigDecimal) map.get("play_population_sum")).intValue());
            monthLoginReport.setLoginPopulation(((BigDecimal) map.get("login_population_sum")).intValue());
            this.monthLoginReportMapper.insert(monthLoginReport);
            monthRechargeReportList.add(monthLoginReport);
        }
        return Result.success(monthRechargeReportList);
    }
}
