package com.hhly.smartdata.service.schedule;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hhly.smartdata.dto.enume.PlatformIdEnum;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.mapper.member.LoginTrackMapper;
import com.hhly.smartdata.mapper.member.RechargeRecordMapper;
import com.hhly.smartdata.mapper.member.UserInfoMapper;
import com.hhly.smartdata.mapper.smartdata.DailyCompositeReportMapper;
import com.hhly.smartdata.mapper.smartdata.DailyLoginReportMapper;
import com.hhly.smartdata.mapper.smartdata.DailyRechargeReportMapper;
import com.hhly.smartdata.mapper.source.DataGameStartMapper;
import com.hhly.smartdata.model.smartdata.DailyCompositeReport;
import com.hhly.smartdata.model.smartdata.DailyLoginReport;
import com.hhly.smartdata.model.smartdata.DailyRechargeReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Iritchie.ren on 2017/9/25.
 */
@Service
public class DailyExecutorService{

    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private LoginTrackMapper loginTrackMapper;

    @Autowired
    private DailyRechargeReportMapper dailyRechargeReportMapper;

    @Autowired
    private DataGameStartMapper dataGameStartMapper;

    @Autowired
    private DailyLoginReportMapper dailyLoginReportMapper;

    @Autowired
    private DailyCompositeReportMapper dailyCompositeReportMapper;

    public Result compositeReport() throws Exception{
        // 昨日注册用户列表
        List<String> yesterdayRegisterUserList = this.userInfoMapper.selectYesterdayRegisterUser();
        // 昨日游戏启动用户列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayLaunchGameUserList = this.dataGameStartMapper.selectYesterdayLaunchGameUser();
        Set<String> yesterdayLaunchGameUserSet = Sets.newHashSet();
        for(Map<String, Object> map : yesterdayLaunchGameUserList){
            yesterdayLaunchGameUserSet.add((String) map.get("userId"));
        }
        // 昨日产生消费记录的用户列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayRechargeUserList = this.rechargeRecordMapper.selectYesterdayRechargeUser();
        Map<String, Map<String, Object>> yesterdayRechargeUserMap = Maps.newHashMap();
        for(Map<String, Object> map : yesterdayRechargeUserList){
            yesterdayRechargeUserMap.put((String) map.get("userId"), map);
        }
        // 昨日登录用户列表
        List<Map<String, Object>> yesterdayLoginUserList = this.loginTrackMapper.selectYesterdayLoginUser();
        Set<String> yesterdayLoginUserSet = Sets.newHashSet();
        for(Map<String, Object> map : yesterdayLoginUserList){
            yesterdayLoginUserSet.add((String) map.get("userId"));
        }
        // 当天以前的老用户注册列表
        List<String> oldRegisterUserList = this.userInfoMapper.selectOldRegisterUser();
        // 前日注册用户列表
        List<String> beforeYesterdayRegisterUserList = this.userInfoMapper.selectBeforeYesterdayRegisterUser();

        Date now = new Date();
        DailyCompositeReport dailyCompositeReport = new DailyCompositeReport();
        dailyCompositeReport.setStatisticsDay(DateUtil.getYesterdayStr(now));
        dailyCompositeReport.setExecuteTime(now);

        //注册人数
        dailyCompositeReport.setRegisterPopulation(yesterdayRegisterUserList.size());
        //新用户统计
        Integer registerExpCount = 0;
        Integer realExpCount = 0;
        Integer virtualExpCount = 0;
        Integer newUserRechargeCount = 0;
        Integer newUserRechargePopulation = 0;
        BigDecimal newUserRechargeAmount = new BigDecimal(0.0000);
        Integer newUserPlayCount = 0;
        Integer newUserLoginCount = 0;
        for(String item : yesterdayRegisterUserList){
            if(yesterdayLaunchGameUserSet.contains(item)){
                registerExpCount++;
            }

            if(yesterdayLaunchGameUserSet.contains(item) && yesterdayRechargeUserMap.containsKey(item)){
                realExpCount++;
            }else{
                virtualExpCount++;
            }

            if(yesterdayRechargeUserMap.containsKey(item)){
                newUserRechargePopulation++;
                Map<String, Object> map = yesterdayRechargeUserMap.get(item);
                newUserRechargeAmount = newUserRechargeAmount.add((BigDecimal) map.get("applyAmountSum"));
                newUserRechargeCount += ((Long) map.get("orderCount")).intValue();
            }

            if(yesterdayLoginUserSet.contains(item) && yesterdayLaunchGameUserSet.contains(item)){
                newUserPlayCount++;
            }

            if(yesterdayLoginUserSet.contains(item)){
                newUserLoginCount++;
            }

        }
        dailyCompositeReport.setRegisterExpCount(registerExpCount);
        dailyCompositeReport.setRealExpCount(realExpCount);
        dailyCompositeReport.setVirtualExpCount(virtualExpCount);
        dailyCompositeReport.setNewUserRechargePopulation(newUserRechargePopulation);
        dailyCompositeReport.setNewUserRechargeCount(newUserRechargeCount);
        dailyCompositeReport.setNewUserRechargeAmount(newUserRechargeAmount);
        dailyCompositeReport.setNewUserPlayCount(newUserPlayCount);
        dailyCompositeReport.setNewUserLoginCount(newUserLoginCount);
        //老用户统计
        Integer oldUserRechargeCount = 0;
        Integer oldUserRechargePopulation = 0;
        BigDecimal oldUserRechargeAmount = new BigDecimal(0.0000);
        Integer oldUserPlayCount = 0;
        Integer oldUserLoginCount = 0;
        for(String item : oldRegisterUserList){
            if(yesterdayRechargeUserMap.containsKey(item)){
                oldUserRechargePopulation++;
                Map<String, Object> map = yesterdayRechargeUserMap.get(item);
                oldUserRechargeCount += ((Long) map.get("orderCount")).intValue();
                oldUserRechargeAmount = oldUserRechargeAmount.add((BigDecimal) map.get("applyAmountSum"));
            }

            if(yesterdayLoginUserSet.contains(item) && yesterdayLaunchGameUserSet.contains(item)){
                oldUserPlayCount++;
            }

            if(yesterdayLoginUserSet.contains(item)){
                oldUserLoginCount++;
            }
        }
        dailyCompositeReport.setOldUserRechargePopulation(oldUserRechargePopulation);
        dailyCompositeReport.setOldUserRechargeCount(oldUserRechargeCount);
        dailyCompositeReport.setOldUserRechargeAmount(oldUserRechargeAmount);
        dailyCompositeReport.setOldUserPlayCount(oldUserPlayCount);
        dailyCompositeReport.setOldUserLoginCount(oldUserLoginCount);
        // 次日留存
        Integer nextDayStayCount = 0;
        for(String item : beforeYesterdayRegisterUserList){
            if(yesterdayLoginUserSet.contains(item)){
                nextDayStayCount++;
            }
        }
        dailyCompositeReport.setNextDayStayCount(nextDayStayCount);

        this.dailyCompositeReportMapper.insert(dailyCompositeReport);
        return Result.success(dailyCompositeReport);
    }

    public Result rechargeStatistic() throws Exception{
        List<Map<String, Object>> yesterdayList = this.rechargeRecordMapper.selectYesterday();
        List<Map<String, Object>> yesterdayNewUserList = this.rechargeRecordMapper.selectYesterdayNewUser();
        List<Map<String, Object>> yesterdayOldUserList = this.rechargeRecordMapper.selectYesterdayOldUser();
        List<DailyRechargeReport> dailyRechargeReportList = Lists.newArrayList();
        Date now = new Date();
        //根据当天日期计算昨天的日期
        String statisticsDay = DateUtil.getYesterdayStr(now);

        for(SourceTypeEnum item : SourceTypeEnum.values()){
            DailyRechargeReport dailyRechargeReport = new DailyRechargeReport();
            dailyRechargeReport.setSourceType(item.getCode());
            dailyRechargeReport.setStatisticsDay(statisticsDay);
            dailyRechargeReport.setExecuteTime(now);
            for(Map<String, Object> map : yesterdayList){
                dailyRechargeReport.setRechargePopulation((map.get("userCount") == null ? 0 : ((Long) map.get("userCount")).intValue()));
                dailyRechargeReport.setRechargeAmount(map.get("amountSum") == null ? new BigDecimal(0) : (BigDecimal) map.get("amountSum"));
                dailyRechargeReport.setRechargeCount((map.get("orderCount") == null ? 0 : ((Long) map.get("orderCount")).intValue()));
            }

            for(Map<String, Object> map : yesterdayNewUserList){
                dailyRechargeReport.setNewRechargePopulation((map.get("userCount") == null ? 0 : ((Long) map.get("userCount")).intValue()));
            }

            for(Map<String, Object> map : yesterdayOldUserList){
                dailyRechargeReport.setOldRechargePopulation((map.get("userCount") == null ? 0 : ((Long) map.get("userCount")).intValue()));
            }
            this.dailyRechargeReportMapper.insert(dailyRechargeReport);
            dailyRechargeReportList.add(dailyRechargeReport);
        }

        return Result.success(dailyRechargeReportList);
    }

    public Result loginStatistic() throws Exception{
        // 昨天注册的账号列表
        List<String> yesterdayRegisterUserList = this.userInfoMapper.selectYesterdayRegisterUser();
        //昨天登录的账号列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayLoginUserList = this.loginTrackMapper.selectYesterdayLoginUser();
        Set<String> yesterdayLoginUserSet = Sets.newHashSet();
        for(Map<String, Object> map : yesterdayLoginUserList){
            yesterdayLoginUserSet.add((String) map.get("userId"));
        }
        // 昨天启动游戏的账号列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayLaunchGameUserList = this.dataGameStartMapper.selectYesterdayLaunchGameUser();
        Set<String> yesterdayLaunchGameUserSet = Sets.newHashSet();
        for(Map<String, Object> map : yesterdayLaunchGameUserList){
            yesterdayLaunchGameUserSet.add((String) map.get("userId"));
        }
        //
        Date now = new Date();
        String statisticsDayStr = DateUtil.getYesterdayStr(now);
        //#（终端+平台）维度进行统计
        List<DailyLoginReport> dailyLoginReportList = Lists.newArrayList();
        //#2 平台
        for(PlatformIdEnum platformIdEnum : PlatformIdEnum.values()){
            //#1 终端
            for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){

                DailyLoginReport dailyLoginReport = new DailyLoginReport();
                dailyLoginReport.setExecuteTime(now);
                dailyLoginReport.setPlatformId(platformIdEnum.getCode());
                dailyLoginReport.setPlatformName(platformIdEnum.getDesc());
                dailyLoginReport.setSourceType(sourceTypeEnum.getCode());
                dailyLoginReport.setStatisticsDay(statisticsDayStr);
                //当日注册&当日登录人数；当日注册&当日玩游戏人数
                Integer loginPopulation = 0;
                Integer playPopulation = 0;
                for(String item : yesterdayRegisterUserList){
                    if(yesterdayLoginUserSet.contains(item)){
                        loginPopulation++;
                    }

                    if(yesterdayLaunchGameUserSet.contains(item)){
                        playPopulation++;
                    }
                }
                dailyLoginReport.setLoginPopulation(loginPopulation);
                dailyLoginReport.setPlayPopulation(playPopulation);
                this.dailyLoginReportMapper.insert(dailyLoginReport);
                dailyLoginReportList.add(dailyLoginReport);
            }
        }

        return Result.success(dailyLoginReportList);
    }

    public Result keepRecordAnalyze(){

        return null;
    }
}
