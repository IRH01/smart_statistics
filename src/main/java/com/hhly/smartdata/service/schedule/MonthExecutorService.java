package com.hhly.smartdata.service.schedule;

import com.google.common.collect.Lists;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.mapper.member.SystemConfigMapper;
import com.hhly.smartdata.mapper.member.UserInfoMapper;
import com.hhly.smartdata.mapper.smartdata.DailyCompositeReportMapper;
import com.hhly.smartdata.mapper.smartdata.DailyLoginReportMapper;
import com.hhly.smartdata.mapper.smartdata.DailyRechargeReportMapper;
import com.hhly.smartdata.mapper.smartdata.DailyRegisterReportMapper;
import com.hhly.smartdata.mapper.smartdata.MonthCompositeReportMapper;
import com.hhly.smartdata.mapper.smartdata.MonthLoginReportMapper;
import com.hhly.smartdata.mapper.smartdata.MonthRechargeReportMapper;
import com.hhly.smartdata.mapper.smartdata.MonthRegisterReportMapper;
import com.hhly.smartdata.model.smartdata.MonthCompositeReport;
import com.hhly.smartdata.model.smartdata.MonthLoginReport;
import com.hhly.smartdata.model.smartdata.MonthRechargeReport;
import com.hhly.smartdata.model.smartdata.MonthRegisterReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.JsonUtil;
import com.hhly.smartdata.util.Result;
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

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    public Result compositeReport(Date date) throws Exception{
        Map<String, Object> lastMonthCompositeMap = this.dailyCompositeReportMapper.selectLastMonthComposite(DateUtil.getLastMonthFirstDayStr(date), DateUtil.getLastMonthEndDayStr(date));
        Long userCount = this.userInfoMapper.selectUserCount(DateUtil.getNowMonthZeroTime(date));
        MonthCompositeReport monthCompositeReport = new MonthCompositeReport();
        monthCompositeReport.setExecuteTime(date);
        monthCompositeReport.setStatisticsMonth(DateUtil.getLastMonthStr(date));
        if(lastMonthCompositeMap != null){
            monthCompositeReport.setVirtualExpCount(lastMonthCompositeMap.get("virtual_exp_count_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("virtual_exp_count_sum")).intValue());
            monthCompositeReport.setTotalRegisterPopulation(userCount == null ? 0 : userCount.intValue());
            monthCompositeReport.setRegisterPopulation(lastMonthCompositeMap.get("register_population_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("register_population_sum")).intValue());
            monthCompositeReport.setRegisterExpCount(lastMonthCompositeMap.get("register_exp_count_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("register_exp_count_sum")).intValue());
            monthCompositeReport.setRealExpCount(lastMonthCompositeMap.get("real_exp_count_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("real_exp_count_sum")).intValue());
            monthCompositeReport.setNewUserLoginCount(lastMonthCompositeMap.get("new_user_login_count_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("new_user_login_count_sum")).intValue());
            monthCompositeReport.setNewUserPlayCount(lastMonthCompositeMap.get("new_user_play_count_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("new_user_play_count_sum")).intValue());
            monthCompositeReport.setNewUserRechargePopulation(lastMonthCompositeMap.get("new_user_recharge_population_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("new_user_recharge_population_sum")).intValue());
            monthCompositeReport.setNewUserRechargeCount(lastMonthCompositeMap.get("new_user_recharge_count_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("new_user_recharge_count_sum")).intValue());
            monthCompositeReport.setNewUserRechargeAmount(lastMonthCompositeMap.get("new_user_recharge_amount_sum") == null ? new BigDecimal(0) : (BigDecimal) lastMonthCompositeMap.get("new_user_recharge_amount_sum"));
            monthCompositeReport.setOldUserRechargePopulation(lastMonthCompositeMap.get("old_user_recharge_population_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("old_user_recharge_population_sum")).intValue());
            monthCompositeReport.setOldUserRechargeAmount(lastMonthCompositeMap.get("old_user_recharge_amount_sum") == null ? new BigDecimal(0) : (BigDecimal) lastMonthCompositeMap.get("old_user_recharge_amount_sum"));
            monthCompositeReport.setOldUserRechargeCount(lastMonthCompositeMap.get("old_user_recharge_count_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("old_user_recharge_count_sum")).intValue());
            monthCompositeReport.setOldUserPlayCount(lastMonthCompositeMap.get("old_user_play_count_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("old_user_play_count_sum")).intValue());
            monthCompositeReport.setOldUserLoginCount(lastMonthCompositeMap.get("old_user_login_count_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("old_user_login_count_sum")).intValue());
            monthCompositeReport.setNextDayStayCount(lastMonthCompositeMap.get("next_day_stay_count_sum") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("next_day_stay_count_sum")).intValue());
        }

        //先删除，再插入
        this.monthCompositeReportMapper.deleteByTime(monthCompositeReport.getStatisticsMonth());
        this.monthCompositeReportMapper.insert(monthCompositeReport);
        return Result.success(monthCompositeReport);
    }

    public Result registerReport(Date date) throws Exception{
        Map<String, Object> lastMonthRegisterMap = this.dailyRegisterReportMapper.selectLastMonthRegister(DateUtil.getLastMonthFirstDayStr(date), DateUtil.getLastMonthEndDayStr(date));
        MonthRegisterReport monthRegisterReport = new MonthRegisterReport();
        monthRegisterReport.setExecuteTime(date);
        monthRegisterReport.setStatisticsMonth(DateUtil.getLastMonthStr(date));
        if(lastMonthRegisterMap != null){
            monthRegisterReport.setPcPopulation(lastMonthRegisterMap.get("pc_population_sum") == null ? 0 : ((BigDecimal) lastMonthRegisterMap.get("pc_population_sum")).intValue());
            monthRegisterReport.setPcPageView(lastMonthRegisterMap.get("pc_page_view_sum") == null ? 0L : ((BigDecimal) lastMonthRegisterMap.get("pc_page_view_sum")).longValue());
            monthRegisterReport.setPcUserView(lastMonthRegisterMap.get("pc_user_view_sum") == null ? 0 : ((BigDecimal) lastMonthRegisterMap.get("pc_user_view_sum")).intValue());
            monthRegisterReport.setH5UserView(lastMonthRegisterMap.get("h5_user_view_sum") == null ? 0 : ((BigDecimal) lastMonthRegisterMap.get("h5_user_view_sum")).intValue());
            monthRegisterReport.setH5Population(lastMonthRegisterMap.get("h5_population_sum") == null ? 0 : ((BigDecimal) lastMonthRegisterMap.get("h5_population_sum")).intValue());
            monthRegisterReport.setH5PageView(lastMonthRegisterMap.get("h5_page_view_sum") == null ? 0 : ((BigDecimal) lastMonthRegisterMap.get("h5_page_view_sum")).longValue());
            monthRegisterReport.setIosPopulation(lastMonthRegisterMap.get("ios_population_sum") == null ? 0 : ((BigDecimal) lastMonthRegisterMap.get("ios_population_sum")).intValue());
            monthRegisterReport.setIosInstallCount(lastMonthRegisterMap.get("ios_install_count_sum") == null ? 0 : ((BigDecimal) lastMonthRegisterMap.get("ios_install_count_sum")).intValue());
            monthRegisterReport.setAndroidPopulation(lastMonthRegisterMap.get("android_population_sum") == null ? 0 : ((BigDecimal) lastMonthRegisterMap.get("android_population_sum")).intValue());
            monthRegisterReport.setAndroidInstallCount(lastMonthRegisterMap.get("android_install_count_sum") == null ? 0 : ((BigDecimal) lastMonthRegisterMap.get("android_install_count_sum")).intValue());
        }

        //先删除，再插入
        this.monthRegisterReportMapper.deleteByTime(monthRegisterReport.getStatisticsMonth());
        this.monthRegisterReportMapper.insert(monthRegisterReport);
        return Result.success(monthRegisterReport);
    }

    public Result rechargeReport(Date date) throws Exception{
        String monthStr = DateUtil.getLastMonthStr(date);
        List<Map<String, Object>> lastMonthRechargeList = this.dailyRechargeReportMapper.selectLastMonthRecharge(DateUtil.getLastMonthFirstDayStr(date), DateUtil.getLastMonthEndDayStr(date));
        List<MonthRechargeReport> monthRechargeReportList = Lists.newArrayList();
        for(SourceTypeEnum item : SourceTypeEnum.values()){
            MonthRechargeReport monthRechargeReport = new MonthRechargeReport();
            monthRechargeReport.setStatisticsMonth(monthStr);
            monthRechargeReport.setExecuteTime(date);
            for(Map<String, Object> map : lastMonthRechargeList){
                Byte sourceType = ((Integer) map.get("source_type")).byteValue();
                if(sourceType.equals(item.getCode())){
                    monthRechargeReport.setSourceType(((Integer) map.get("source_type")).byteValue());
                    monthRechargeReport.setRechargePopulation(((BigDecimal) map.get("recharge_population_sum")).intValue());
                    monthRechargeReport.setRechargeAmount((BigDecimal) map.get("recharge_amount_sum"));
                    monthRechargeReport.setRechargeCount(((BigDecimal) map.get("recharge_count_sum")).intValue());
                    monthRechargeReport.setNewRechargePopulation(((BigDecimal) map.get("new_recharge_population_sum")).intValue());
                    monthRechargeReport.setOldRechargePopulation(((BigDecimal) map.get("old_recharge_population_sum")).intValue());
                    break;
                }
                //先删除，再插入
            }

            this.monthRechargeReportMapper.deleteByTimeAndSourceType(monthRechargeReport.getStatisticsMonth(), monthRechargeReport.getSourceType());
            this.monthRechargeReportMapper.insert(monthRechargeReport);
            monthRechargeReportList.add(monthRechargeReport);
        }
        return Result.success(monthRechargeReportList);
    }

    public Result loginSourceReport(Date date) throws Exception{
        String monthStr = DateUtil.getLastMonthStr(date);
        List<Map<String, Object>> lastMonthLoginList = this.dailyLoginReportMapper.selectLastMonthLogin(DateUtil.getLastMonthFirstDayStr(date), DateUtil.getLastMonthEndDayStr(date));

        List<MonthLoginReport> monthRechargeReportList = Lists.newArrayList();

        String configValue = systemConfigMapper.getConfigValueByKey("hhly:playOne:systemConfig:common:platformCode");
        Map<String, String> platformIdMap = JsonUtil.jsonStr2Map(configValue);

        for(String platformCode : platformIdMap.keySet()){
            for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){
                MonthLoginReport monthLoginReport = new MonthLoginReport();
                monthLoginReport.setExecuteTime(date);
                monthLoginReport.setStatisticsMonth(monthStr);
                for(Map<String, Object> map : lastMonthLoginList){
                    String loginPlatformCode = (String) map.get("platform_code");
                    Byte sourceType = null;
                    try{
                        sourceType = ((Integer) map.get("source_type")).byteValue();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    if(loginPlatformCode.equals(platformCode) && sourceType.equals(sourceTypeEnum.getCode())){
                        monthLoginReport.setPlatformName("待输入");
                        monthLoginReport.setSourceType(((Integer) map.get("source_type")).byteValue());
                        monthLoginReport.setPlatformName((String) map.get("platform_name"));
                        monthLoginReport.setPlayPopulation(((BigDecimal) map.get("play_population_sum")).intValue());
                        monthLoginReport.setLoginPopulation(((BigDecimal) map.get("login_population_sum")).intValue());
                        break;
                    }
                }
                //先删除，再插入
                this.monthLoginReportMapper.deleteByTime(monthLoginReport.getStatisticsMonth());
                this.monthLoginReportMapper.insert(monthLoginReport);
                monthRechargeReportList.add(monthLoginReport);
            }
        }
        return Result.success(monthRechargeReportList);
    }
}
