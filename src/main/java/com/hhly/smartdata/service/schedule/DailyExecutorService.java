package com.hhly.smartdata.service.schedule;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hhly.smartdata.dto.enume.KeepRecordEnum;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.mapper.member.LoginTrackMapper;
import com.hhly.smartdata.mapper.member.PlatformGoldConsumeMapper;
import com.hhly.smartdata.mapper.member.RechargeRecordMapper;
import com.hhly.smartdata.mapper.member.SystemConfigMapper;
import com.hhly.smartdata.mapper.member.UserInfoMapper;
import com.hhly.smartdata.mapper.smartdata.DailyCompositeReportMapper;
import com.hhly.smartdata.mapper.smartdata.DailyKeepRecordReportMapper;
import com.hhly.smartdata.mapper.smartdata.DailyLoginReportMapper;
import com.hhly.smartdata.mapper.smartdata.DailyRechargeReportMapper;
import com.hhly.smartdata.mapper.smartdata.DailyRegisterReportMapper;
import com.hhly.smartdata.mapper.source.DataGameStartMapper;
import com.hhly.smartdata.mapper.source.DataInstallsMapper;
import com.hhly.smartdata.mapper.source.DataViewMapper;
import com.hhly.smartdata.model.smartdata.DailyCompositeReport;
import com.hhly.smartdata.model.smartdata.DailyKeepRecordReport;
import com.hhly.smartdata.model.smartdata.DailyLoginReport;
import com.hhly.smartdata.model.smartdata.DailyRechargeReport;
import com.hhly.smartdata.model.smartdata.DailyRegisterReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.JsonUtil;
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

    @Autowired
    private DataViewMapper dataViewMapper;

    @Autowired
    private DataInstallsMapper dataInstallsMapper;

    @Autowired
    private DailyRegisterReportMapper dailyRegisterReportMapper;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Autowired
    private PlatformGoldConsumeMapper platformGoldConsumeMapper;

    @Autowired
    private DailyKeepRecordReportMapper dailyKeepRecordReportMapper;

    public Result compositeReport(Date date) throws Exception{
        // 昨日注册用户列表
        List<String> yesterdayRegisterUserList = this.userInfoMapper.selectUserIdByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));

        // 昨日游戏启动用户列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayLaunchGameUserList = this.dataGameStartMapper.selectLaunchGameUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        Set<String> yesterdayLaunchGameUserSet = Sets.newHashSet();
        for(Map<String, Object> map : yesterdayLaunchGameUserList){
            yesterdayLaunchGameUserSet.add((String) map.get("userId"));
        }
        // 昨日产生消费记录的用户列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayRechargeUserList = this.platformGoldConsumeMapper.selectConsumeUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        Map<String, Map<String, Object>> yesterdayRechargeUserMap = Maps.newHashMap();
        for(Map<String, Object> map : yesterdayRechargeUserList){
            yesterdayRechargeUserMap.put((String) map.get("userId"), map);
        }
        // 昨日登录用户列表
        List<Map<String, Object>> yesterdayLoginUserList = this.loginTrackMapper.selectLoginUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        Set<String> yesterdayLoginUserSet = Sets.newHashSet();
        for(Map<String, Object> map : yesterdayLoginUserList){
            yesterdayLoginUserSet.add((String) map.get("userId"));
        }
        // 当天以前的老用户注册列表
        List<String> oldRegisterUserList = this.userInfoMapper.selectOldRegisterUserByTime(DateUtil.offsetDayStartTime(date, 0));
        // 前日注册用户列表
        List<String> beforeYesterdayRegisterUserList = this.userInfoMapper.selectUserIdByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -2), DateUtil.offsetDayEndTime(date, -2));

        String yesterdayStr = DateUtil.getYesterdayStr(date);
        DailyCompositeReport dailyCompositeReport = new DailyCompositeReport();
        dailyCompositeReport.setStatisticsDay(yesterdayStr);
        dailyCompositeReport.setExecuteTime(date);

        Long userCount = this.userInfoMapper.selectUserCount(DateUtil.getNowZeroTime(date));
        dailyCompositeReport.setTotalRegisterPopulation(userCount == null ? 0 : userCount.intValue());
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

        //先删除，防止重复记录
        this.dailyCompositeReportMapper.deleteByDaily(yesterdayStr);
        this.dailyCompositeReportMapper.insert(dailyCompositeReport);
        return Result.success(dailyCompositeReport);
    }

    public Result rechargeStatistic(Date date) throws Exception{
        //根据当天日期计算昨天的日期
        String yesterdayStr = DateUtil.getYesterdayStr(date);
        //查询昨天的充值记录
        List<Map<String, Object>> yesterdayList = this.rechargeRecordMapper.selectByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        //查询昨天的新用户
        List<Map<String, Object>> yesterdayNewUserList = this.rechargeRecordMapper.selectNewUserAndRechargeByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        //查询昨天以前的老用户
        List<Map<String, Object>> yesterdayOldUserList = this.rechargeRecordMapper.selectOldUserAndRechargeByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        List<DailyRechargeReport> dailyRechargeReportList = Lists.newArrayList();

        for(SourceTypeEnum item : SourceTypeEnum.values()){
            DailyRechargeReport dailyRechargeReport = new DailyRechargeReport();
            dailyRechargeReport.setSourceType(item.getCode());
            dailyRechargeReport.setStatisticsDay(yesterdayStr);
            dailyRechargeReport.setExecuteTime(date);
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

            //先删除记录，再执行插入
            this.dailyRechargeReportMapper.deleteByTimeAndSourceType(dailyRechargeReport.getStatisticsDay(), dailyRechargeReport.getSourceType());
            this.dailyRechargeReportMapper.insert(dailyRechargeReport);
            dailyRechargeReportList.add(dailyRechargeReport);
        }

        return Result.success(dailyRechargeReportList);
    }

    public Result loginStatistic(Date date) throws Exception{
        //
        String statisticsDayStr = DateUtil.getYesterdayStr(date);
        // 昨天注册的账号列表
        List<String> yesterdayRegisterUserList = this.userInfoMapper.selectUserIdByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        //昨天登录的账号列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayLoginUserList = this.loginTrackMapper.selectLoginUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        Set<String> yesterdayLoginUserSet = Sets.newHashSet();
        for(Map<String, Object> map : yesterdayLoginUserList){
            yesterdayLoginUserSet.add((String) map.get("userId"));
        }
        // 昨天启动游戏的账号列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayLaunchGameUserList = this.dataGameStartMapper.selectLaunchGameUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        Set<String> yesterdayLaunchGameUserSet = Sets.newHashSet();
        for(Map<String, Object> map : yesterdayLaunchGameUserList){
            yesterdayLaunchGameUserSet.add((String) map.get("userId"));
        }

        //#（终端+平台）维度进行统计
        List<DailyLoginReport> dailyLoginReportList = Lists.newArrayList();

        String configValue = systemConfigMapper.getConfigValueByKey("hhly:playOne:systemConfig:common:platformCode");
        Map<String, String> PlatformIdMap = JsonUtil.jsonStr2Map(configValue);

        //#2 平台
        for(String platformCode : PlatformIdMap.keySet()){
            //#1 终端
            for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){
                DailyLoginReport dailyLoginReport = new DailyLoginReport();
                dailyLoginReport.setExecuteTime(date);
                dailyLoginReport.setPlatformCode(platformCode);
                dailyLoginReport.setPlatformName(PlatformIdMap.get(platformCode));
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

                //先删除，再插入
                this.dailyLoginReportMapper.deleteByTimeAndPlatformCodeAndSourceType(dailyLoginReport.getStatisticsDay(), dailyLoginReport.getPlatformCode(), dailyLoginReport.getSourceType());
                this.dailyLoginReportMapper.insert(dailyLoginReport);
                dailyLoginReportList.add(dailyLoginReport);
            }
        }

        return Result.success(dailyLoginReportList);
    }

    public Result registerStatistic(Date date) throws Exception{
        String statisticsDayStr = DateUtil.getYesterdayStr(date);
        // 前一日各端注册用户数
        List<Map<String, Object>> yesterdayRegisterUserIdAndTerminalList = userInfoMapper.selectRegisterUserIdAndTerminalByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        // 前一日pv和uv
        List<Map<String, Object>> yesterdayUserViewAndPageViewList = dataViewMapper.selectUserViewAndPageViewByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        // 前一日app安装量
        List<Map<String, Object>> yesterdayInstallsList = dataInstallsMapper.selectInstallsByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));

        List<DailyRegisterReport> dailyRegisterReportList = Lists.newArrayList();

        DailyRegisterReport dailyRegisterReport = new DailyRegisterReport();
        for(Map<String, Object> registerMap : yesterdayRegisterUserIdAndTerminalList){
            switch(Integer.valueOf(registerMap.get("osType") + "")){
                case 1:
                    dailyRegisterReport.setPcPopulation(registerMap.get("UserCount") == null ? 0 : Integer.valueOf(registerMap.get("UserCount") + ""));
                    break;
                case 2:
                    dailyRegisterReport.setAndroidPopulation(registerMap.get("UserCount") == null ? 0 : Integer.valueOf(registerMap.get("UserCount") + ""));
                    break;
                case 3:
                    dailyRegisterReport.setIosPopulation(registerMap.get("UserCount") == null ? 0 : Integer.valueOf(registerMap.get("UserCount") + ""));
                    break;
                case 4:
                    dailyRegisterReport.setH5Population(registerMap.get("UserCount") == null ? 0 : Integer.valueOf(registerMap.get("UserCount") + ""));
                    break;
            }
        }

        for(Map<String, Object> userViewMap : yesterdayUserViewAndPageViewList){

            switch(Integer.valueOf(userViewMap.get("platformTerminal") + "")){
                case 1:
                    dailyRegisterReport.setPcPageView(userViewMap.get("pageCount") == null ? 0 : Long.valueOf(userViewMap.get("pageCount") + ""));
                    dailyRegisterReport.setPcUserView(userViewMap.get("userCount") == null ? 0 : Integer.valueOf(userViewMap.get("userCount") + ""));
                    break;
                case 4:
                    dailyRegisterReport.setH5Population(userViewMap.get("pageCount") == null ? 0 : Integer.valueOf(userViewMap.get("pageCount") + ""));
                    dailyRegisterReport.setH5UserView(userViewMap.get("pageCount") == null ? 0 : Integer.valueOf(userViewMap.get("pageCount") + ""));
                    break;
            }

        }

        for(Map<String, Object> installsMap : yesterdayInstallsList){
            switch(Integer.valueOf(installsMap.get("platformTerminal") + "")){
                case 2:
                    dailyRegisterReport.setAndroidInstallCount(Integer.valueOf(installsMap.get("uniqueCount") + ""));
                    break;
                case 3:
                    dailyRegisterReport.setIosInstallCount(Integer.valueOf(installsMap.get("uniqueCount") + ""));
                    break;
            }

        }
        dailyRegisterReport.setExecuteTime(date);
        dailyRegisterReport.setStatisticsDay(statisticsDayStr);

        //先删除，再插入
        dailyRegisterReportMapper.deleteByTime(dailyRegisterReport.getStatisticsDay());
        dailyRegisterReportMapper.insert(dailyRegisterReport);
        dailyRegisterReportList.add(dailyRegisterReport);

        return Result.success(dailyRegisterReportList);
    }

    public Result keepRecordAnalyzeReport(Date date) throws Exception{
        // 昨日登录用户列表
        List<Map<String, Object>> yesterdayLoginUserList = this.loginTrackMapper.selectLoginUserGroupByUserAndOsType(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        //
        // 1天前注册用户列表，为了计算当日注册数
        List<Map<String, Object>> beforeDayRegisterUserList_0 = this.userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        // 2天前注册用户列表，前日注册用户列表,为了计算1日留存（次留）
        List<Map<String, Object>> beforeDayRegisterUserList_1 = this.userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -2), DateUtil.offsetDayEndTime(date, -2));
        // 3天前注册用户列表，为了计算2日留存（次留）
        List<Map<String, Object>> beforeDayRegisterUserList_2 = this.userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -3), DateUtil.offsetDayEndTime(date, -3));
        // 4天前注册用户列表，为了计算3日留存（次留）
        List<Map<String, Object>> beforeDayRegisterUserList_3 = this.userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -4), DateUtil.offsetDayEndTime(date, -4));
        // 5天前注册用户列表，为了计算4日留存（次留）
        List<Map<String, Object>> beforeDayRegisterUserList_4 = this.userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -5), DateUtil.offsetDayEndTime(date, -5));
        // 6天前注册用户列表，为了计算5日留存（次留）
        List<Map<String, Object>> beforeDayRegisterUserList_5 = this.userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -6), DateUtil.offsetDayEndTime(date, -6));
        // 7天前注册用户列表，为了计算6日留存（次留）
        List<Map<String, Object>> beforeDayRegisterUserList_6 = this.userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -7), DateUtil.offsetDayEndTime(date, -7));
        // 8天前注册用户列表，为了计算7日留存（次留）
        List<Map<String, Object>> beforeDayRegisterUserList_7 = this.userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -8), DateUtil.offsetDayEndTime(date, -8));
        // 15天前注册用户列表，为了计算14日留存（次留）
        List<Map<String, Object>> beforeDayRegisterUserList_14 = this.userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -15), DateUtil.offsetDayEndTime(date, -15));
        // 31天前注册用户列表，为了计算30日留存（次留）
        List<Map<String, Object>> beforeDayRegisterUserList_30 = this.userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -31), DateUtil.offsetDayEndTime(date, -31));

        DailyKeepRecordReport dailyKeepRecordReport = new DailyKeepRecordReport();
        dailyKeepRecordReport.setExecuteTime(date);
        dailyKeepRecordReport.setStatisticsDay(DateUtil.getYesterdayStr(date));

        // 分端数据
        Set<String> yesterdayLoginUserSet = Sets.newHashSet();
        for(KeepRecordEnum keepRecordEnum : KeepRecordEnum.values()){
            Integer one;
            Integer two;
            Integer three;
            Integer four;
            Integer five;
            Integer six;
            Integer seven;
            Integer fourteen;
            Integer thirty;
            Integer count = 0;
            yesterdayLoginUserSet.clear();
            switch(keepRecordEnum.getCode().intValue()){
                case 0:
                    // 全部
                    for(Map<String, Object> map : yesterdayLoginUserList){
                        yesterdayLoginUserSet.add((String) map.get("userId"));
                    }
                    dailyKeepRecordReport.setRegisterCount(beforeDayRegisterUserList_0.size());
                    break;
                case 1:
                    // pc
                    for(Map<String, Object> map : yesterdayLoginUserList){
                        if("1".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            yesterdayLoginUserSet.add((String) map.get("userId"));
                        }
                    }
                    for(Map<String, Object> map : beforeDayRegisterUserList_0){
                        if("1".equals(map.get("osType") == null ? "" : map.get("osType").toString())){
                            count++;
                        }
                    }
                    dailyKeepRecordReport.setRegisterCount(count);
                    break;
                case 2:
                    // android
                    for(Map<String, Object> map : yesterdayLoginUserList){
                        if("2".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            yesterdayLoginUserSet.add((String) map.get("userId"));
                        }
                    }

                    for(Map<String, Object> map : beforeDayRegisterUserList_0){
                        if("2".equals(map.get("osType") == null ? "" : map.get("osType").toString())){
                            count++;
                        }
                    }
                    dailyKeepRecordReport.setRegisterCount(count);
                    break;
                case 3:
                    // ios;
                    for(Map<String, Object> map : yesterdayLoginUserList){
                        if("3".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            yesterdayLoginUserSet.add((String) map.get("userId"));
                        }
                    }
                    for(Map<String, Object> map : beforeDayRegisterUserList_0){
                        if("3".equals(map.get("osType") == null ? "" : map.get("osType").toString())){
                            count++;
                        }
                    }
                    dailyKeepRecordReport.setRegisterCount(count);
                    break;
                case 4:
                    // h5
                    for(Map<String, Object> map : yesterdayLoginUserList){
                        if("4".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            yesterdayLoginUserSet.add((String) map.get("userId"));
                        }
                    }
                    for(Map<String, Object> map : beforeDayRegisterUserList_0){
                        if("4".equals(map.get("osType") == null ? "" : map.get("osType").toString())){
                            count++;
                        }
                    }
                    dailyKeepRecordReport.setRegisterCount(count);
                    break;
            }

            // 统计留存人数
            one = getRemain(beforeDayRegisterUserList_1, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            two = getRemain(beforeDayRegisterUserList_2, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            three = getRemain(beforeDayRegisterUserList_3, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            four = getRemain(beforeDayRegisterUserList_4, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            five = getRemain(beforeDayRegisterUserList_5, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            six = getRemain(beforeDayRegisterUserList_6, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            seven = getRemain(beforeDayRegisterUserList_7, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            fourteen = getRemain(beforeDayRegisterUserList_14, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            thirty = getRemain(beforeDayRegisterUserList_30, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());

            dailyKeepRecordReport.setSourceType(keepRecordEnum.getCode());
            dailyKeepRecordReport.setOneRemain(one);
            dailyKeepRecordReport.setTwoRemain(two);
            dailyKeepRecordReport.setThreeRemain(three);
            dailyKeepRecordReport.setFourRemain(four);
            dailyKeepRecordReport.setFiveRemain(five);
            dailyKeepRecordReport.setSixRemain(six);
            dailyKeepRecordReport.setSevenRemain(seven);
            dailyKeepRecordReport.setFourteenRemain(fourteen);
            dailyKeepRecordReport.setThirtyRemain(thirty);
            //先删除，再插入
            this.dailyKeepRecordReportMapper.deleteByTime(dailyKeepRecordReport.getStatisticsDay());
            this.dailyKeepRecordReportMapper.insert(dailyKeepRecordReport);
        }
        return Result.success(dailyKeepRecordReport);
    }

    private Integer getRemain(List<Map<String, Object>> list, Set<String> yesterdayLoginUserSet, Integer code){
        Integer count = 0;
        for(Map<String, Object> map : list){
            if(yesterdayLoginUserSet.contains(map.get("UserId"))){
                if(code == 0) count++;
                else if(map.get("osType").equals(code)) count++;
            }
        }
        return count;
    }
}
