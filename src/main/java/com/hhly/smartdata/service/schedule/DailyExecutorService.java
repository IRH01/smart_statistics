package com.hhly.smartdata.service.schedule;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hhly.smartdata.dto.daily.DailyKeepRecordReportResult;
import com.hhly.smartdata.dto.enume.SourceTypeAndAllEnum;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.mapper.member.LoginTrackMapper;
import com.hhly.smartdata.mapper.member.PlatformGoldConsumeMapper;
import com.hhly.smartdata.mapper.member.PlatformInfoMapper;
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

    @Autowired
    private PlatformInfoMapper platformInfoMapper;

    public Result compositeReport(Date date) throws Exception{
        // 昨日注册用户列表
        List<String> yesterdayRegisterUserList = this.userInfoMapper.selectUserIdByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));

        // 昨日游戏启动用户列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayLaunchGameUserList = this.dataGameStartMapper.selectLaunchGameUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        Set<String> yesterdayLaunchGameUserSet = Sets.newHashSet();
        for(Map<String, Object> map : yesterdayLaunchGameUserList){
            yesterdayLaunchGameUserSet.add((String) map.get("userId"));
        }

        // 昨日充值的用户列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayRechargeUserList = this.rechargeRecordMapper.selectRechargeUserByTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        Map<String, Map<String, Object>> yesterdayRechargeUserMap = Maps.newHashMap();
        for(Map<String, Object> map : yesterdayRechargeUserList){
            yesterdayRechargeUserMap.put((String) map.get("userId"), map);
        }

        // 昨日产生消费记录的用户列表,并转换成Set集合。
        List<Map<String, Object>> yesterdayConsumeUserList = this.platformGoldConsumeMapper.selectConsumeUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        Map<String, Map<String, Object>> yesterdayConsumeUserMap = Maps.newHashMap();
        for(Map<String, Object> map : yesterdayConsumeUserList){
            yesterdayConsumeUserMap.put((String) map.get("userId"), map);
        }
        // 昨日登录用户列表
        List<String> yesterdayLoginUserList = this.loginTrackMapper.selectLoginUserPopulationByTime(DateUtil.date2String(DateUtil.offsetDayStartTime(date, -1)), DateUtil.date2String(DateUtil.offsetDayEndTime(date, -1)));
        Set<String> yesterdayLoginUserSet = Sets.newHashSet(yesterdayLoginUserList);


        // 昨天以前的老用户注册列表
        List<String> oldRegisterUserList = this.userInfoMapper.selectOldRegisterUserByTime(DateUtil.offsetDayStartTime(date, -1));
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

            if(yesterdayLaunchGameUserSet.contains(item) && yesterdayConsumeUserMap.containsKey(item)){
                realExpCount++;
            }else if(yesterdayLaunchGameUserSet.contains(item) && !yesterdayConsumeUserMap.containsKey(item)){
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
        List<Map<String, Object>> rechargeRecordList = this.rechargeRecordMapper.selectByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        //查询昨天的新用户
        List<Map<String, Object>> registerNewUserList = this.rechargeRecordMapper.selectNewUserAndRechargeByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        //查询昨天以前的老用户
        List<Map<String, Object>> registerOldUserList = this.rechargeRecordMapper.selectOldUserAndRechargeByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        List<DailyRechargeReport> dailyRechargeReportList = Lists.newArrayList();
        //登陆用户列表
        List<Map<String, Object>> loginUserList = loginTrackMapper.selectLoginUserGroupBySourceType(DateUtil.date2String(DateUtil.offsetDayStartTime(date, -1)), DateUtil.date2String(DateUtil.offsetDayEndTime(date, -1)));
        //APP启动表（手机第二次打开，算第二次登录，没有走登录接口）
        List<Map<String, Object>> loginUserGameStart = dataGameStartMapper.selectGameStartByTimeGroupBySource(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        //H5的uv（手机第二次打开，算第二次登录，没有走登录接口）
        List<Map<String, Object>> LoginUserUserView = dataViewMapper.selectUserViewAndPageViewByTimeGroupBySource(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        List<Map<String, Object>> loginUserTotalList = Lists.newArrayList();
        loginUserTotalList.addAll(loginUserList);
        loginUserTotalList.addAll(loginUserGameStart);
        loginUserTotalList.addAll(LoginUserUserView);

        for(SourceTypeEnum item : SourceTypeEnum.values()){
            DailyRechargeReport dailyRechargeReport = new DailyRechargeReport();
            dailyRechargeReport.setSourceType(item.getCode());
            dailyRechargeReport.setStatisticsDay(yesterdayStr);
            dailyRechargeReport.setExecuteTime(date);
            for(Map<String, Object> map : rechargeRecordList){
                if(((Long) map.get("sourceType")).byteValue() == item.getCode()){
                    dailyRechargeReport.setRechargePopulation((map.get("userCount") == null ? 0 : ((Long) map.get("userCount")).intValue()));
                    dailyRechargeReport.setRechargeAmount(map.get("amountSum") == null ? new BigDecimal(0) : (BigDecimal) map.get("amountSum"));
                    dailyRechargeReport.setRechargeCount((map.get("orderCount") == null ? 0 : ((Long) map.get("orderCount")).intValue()));
                    break;
                }
            }

            for(Map<String, Object> map : registerNewUserList){
                if(((Long) map.get("sourceType")).byteValue() == item.getCode()){
                    dailyRechargeReport.setNewRechargePopulation((map.get("userCount") == null ? 0 : ((Long) map.get("userCount")).intValue()));
                    break;
                }
            }

            for(Map<String, Object> map : registerOldUserList){
                if(((Long) map.get("sourceType")).byteValue() == item.getCode()){
                    dailyRechargeReport.setOldRechargePopulation((map.get("userCount") == null ? 0 : ((Long) map.get("userCount")).intValue()));
                    break;
                }
            }

            Set<String> loginUserIdSet = Sets.newHashSet();
            for(Map<String, Object> map : loginUserTotalList){
                if(((Integer) map.get("sourceType")).byteValue() == item.getCode()){
                    loginUserIdSet.add((String) map.get("userId"));
                }
            }
            dailyRechargeReport.setLoginPopulation(loginUserIdSet.size());

            //先删除记录，再执行插入
            this.dailyRechargeReportMapper.deleteByTimeAndSourceType(dailyRechargeReport.getStatisticsDay(), dailyRechargeReport.getSourceType());
            this.dailyRechargeReportMapper.insert(dailyRechargeReport);
            dailyRechargeReportList.add(dailyRechargeReport);
        }

        return Result.success(dailyRechargeReportList);
    }

    public Result loginStatistic(Date date) throws Exception{
        String statisticsDayStr = DateUtil.getYesterdayStr(date);

        //昨天登录的账号列表,并转换成Set集合。分端登录，部分平台，只有玩一下平台。
        List<Map<String, Object>> loginUserList = this.loginTrackMapper.selectLoginUserPopulationByTimeGroupBySource(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        //APP启动表（手机第二次打开，算第二次登录，没有走登录接口）
        List<Map<String, Object>> loginUserGameStart = dataGameStartMapper.selectGameStartByTimeGroupBySource(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        //H5的uv（手机第二次打开，算第二次登录，没有走登录接口）
        List<Map<String, Object>> LoginUserUserView = dataViewMapper.selectUserViewAndPageViewByTimeGroupBySource(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        List<Map<String, Object>> loginUserTotalList = Lists.newArrayList();
        loginUserTotalList.addAll(loginUserList);
        loginUserTotalList.addAll(loginUserGameStart);
        loginUserTotalList.addAll(LoginUserUserView);

        // 昨天启动游戏玩游戏的账号列表,并转换成Set集合。
        List<Map<String, Object>> launchGameUserList = this.dataGameStartMapper.selectLaunchGameUserByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        //用户登录的临时set不重复容器
        Set<String> tempUserIdSet = Sets.newHashSet();

        //#（终端+平台）维度进行统计
        List<DailyLoginReport> dailyLoginReportList = Lists.newArrayList();
        //综合数据
        String configValue = systemConfigMapper.getConfigValueByKey("hhly:playOne:systemConfig:common:platformCode");
        Map<String, String> PlatformIdMap = JsonUtil.jsonStr2Map(configValue);

        //每个终端的综合数据
        DailyLoginReport dailyLoginReportAll = new DailyLoginReport();
        dailyLoginReportAll.setExecuteTime(date);
        dailyLoginReportAll.setPlatformCode("all");
        dailyLoginReportAll.setPlatformName("");
        dailyLoginReportAll.setSourceType(Byte.parseByte("0"));
        dailyLoginReportAll.setStatisticsDay(statisticsDayStr);
        //初始化临时容器
        tempUserIdSet.clear();
        for(Map<String, Object> item : loginUserTotalList){
            tempUserIdSet.add((String) item.get("userId"));
        }
        dailyLoginReportAll.setLoginPopulation(tempUserIdSet.size());

        //初始化临时容器
        tempUserIdSet.clear();
        for(Map<String, Object> item : launchGameUserList){
            tempUserIdSet.add((String) item.get("userId"));
        }
        dailyLoginReportAll.setPlayPopulation(tempUserIdSet.size());

        //先删除，再插入
        this.dailyLoginReportMapper.deleteByTimeAndPlatformCodeAndSourceType(dailyLoginReportAll.getStatisticsDay(),
                dailyLoginReportAll.getPlatformCode(), dailyLoginReportAll.getSourceType());
        this.dailyLoginReportMapper.insert(dailyLoginReportAll);

        //#1 终端
        for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){
            //每个终端的综合数据
            DailyLoginReport dailyLoginReportComp = new DailyLoginReport();
            dailyLoginReportComp.setExecuteTime(date);
            dailyLoginReportComp.setPlatformCode("zong_he");
            dailyLoginReportComp.setPlatformName("");
            dailyLoginReportComp.setSourceType(sourceTypeEnum.getCode());
            dailyLoginReportComp.setStatisticsDay(statisticsDayStr);
            //初始化临时容器
            tempUserIdSet.clear();
            for(Map<String, Object> item : loginUserTotalList){
                Integer sourceType = (Integer) item.get("sourceType");
                if(sourceType == sourceTypeEnum.getCode().intValue()){
                    tempUserIdSet.add((String) item.get("userId"));
                }
            }
            dailyLoginReportComp.setLoginPopulation(tempUserIdSet.size());

            //初始化临时容器
            tempUserIdSet.clear();
            for(Map<String, Object> item : launchGameUserList){
                Integer sourceType = (Integer) item.get("sourceType");
                if(sourceType == sourceTypeEnum.getCode().intValue()){
                    tempUserIdSet.add((String) item.get("userId"));
                }
            }
            dailyLoginReportComp.setPlayPopulation(tempUserIdSet.size());

            //先删除，再插入
            this.dailyLoginReportMapper.deleteByTimeAndPlatformCodeAndSourceType(dailyLoginReportComp.getStatisticsDay(),
                    dailyLoginReportComp.getPlatformCode(), dailyLoginReportComp.getSourceType());
            this.dailyLoginReportMapper.insert(dailyLoginReportComp);

            //每个终端下面，不同平台的数据。
            for(String platformCode : PlatformIdMap.keySet()){
                //#2 平台
                DailyLoginReport dailyLoginReport = new DailyLoginReport();
                dailyLoginReport.setExecuteTime(date);
                dailyLoginReport.setPlatformCode(platformCode);
                dailyLoginReport.setPlatformName(PlatformIdMap.get(platformCode));
                dailyLoginReport.setSourceType(sourceTypeEnum.getCode());
                dailyLoginReport.setStatisticsDay(statisticsDayStr);
                dailyLoginReport.setLoginPopulation(0);

                //初始化临时容器
                tempUserIdSet.clear();
                for(Map<String, Object> item : launchGameUserList){
                    Integer sourceType = (Integer) item.get("sourceType");
                    String platformCodeStr = (String) item.get("platformCode");
                    if(sourceType == sourceTypeEnum.getCode().intValue() && platformCodeStr != null && platformCodeStr.equals(platformCode)){
                        tempUserIdSet.add((String) item.get("userId"));
                    }
                }
                dailyLoginReport.setPlayPopulation(tempUserIdSet.size());

                //先删除，再插入
                this.dailyLoginReportMapper.deleteByTimeAndPlatformCodeAndSourceType(dailyLoginReport.getStatisticsDay(),
                        dailyLoginReport.getPlatformCode(), dailyLoginReport.getSourceType());
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
        List<Map<String, Object>> userViewList = dataViewMapper.selectUserViewByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        List<Map<String, Object>> pageViewList = dataViewMapper.selectPageViewByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));
        // 前一日app安装量
        List<Map<String, Object>> yesterdayInstallsList = dataInstallsMapper.selectInstallsByStartTimeAndEndTime(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));

        List<DailyRegisterReport> dailyRegisterReportList = Lists.newArrayList();

        DailyRegisterReport dailyRegisterReport = new DailyRegisterReport();


        for(Map<String, Object> registerMap : yesterdayRegisterUserIdAndTerminalList){
            Integer sourceType = (Integer) registerMap.get("osType");
            if(sourceType == null){
                continue;
            }
            switch(sourceType){
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


        for(Map<String, Object> userViewMap : userViewList){
            switch(Integer.valueOf(userViewMap.get("sourceType") + "")){
                case 1:
                    dailyRegisterReport.setPcUserView(userViewMap.get("userCount") == null ? 0 : Integer.valueOf(userViewMap.get("userCount") + ""));
                    break;
                case 4:
                    dailyRegisterReport.setH5UserView(userViewMap.get("userCount") == null ? 0 : Integer.valueOf(userViewMap.get("userCount") + ""));
                    break;
            }
        }

        for(Map<String, Object> userViewMap : pageViewList){
            switch(Integer.valueOf(userViewMap.get("sourceType") + "")){
                case 1:
                    dailyRegisterReport.setPcPageView(userViewMap.get("pageCount") == null ? 0 : Long.valueOf(userViewMap.get("pageCount") + ""));
                    break;
                case 4:
                    dailyRegisterReport.setH5PageView((long) (userViewMap.get("pageCount") == null ? 0 : Integer.valueOf(userViewMap.get("pageCount") + "")));
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
        // 昨日登录用户列表  登陆日志表
        List<Map<String, Object>> yesterdayLoginUserList = this.loginTrackMapper.selectLoginUserGroupByUserAndOsType(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));

        //启动表
        List<Map<String, Object>> yesterdayGameStartList = dataGameStartMapper.selectYesterdayGameStartList(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));

        // uv
        List<Map<String, Object>> yesterdayUserViewAndPageViewList = dataViewMapper.selectYesterdayUserViewAndPageViewList(DateUtil.offsetDayStartTime(date, -1), DateUtil.offsetDayEndTime(date, -1));

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

        // 1天前dailyKeepRecordReport入库
        //List<DailyKeepRecordReportResult>  beforeDayDailyKeepRecordReportList_0 = this.dailyKeepRecordReportMapper.selectDailyKeepRecordListDataByStartDayNumAndEndDayNum(DateUtil.offsetDayStr(date, -1));
        // 2天前dailyKeepRecordReport入库
        List<DailyKeepRecordReportResult> beforeDayDailyKeepRecordReportList_1 = this.dailyKeepRecordReportMapper.selectDailyKeepRecordListDataByStartDayNumAndEndDayNum(DateUtil.offsetDayStr(date, -2));
        // 3天前dailyKeepRecordReport入库
        List<DailyKeepRecordReportResult> beforeDayDailyKeepRecordReportList_2 = this.dailyKeepRecordReportMapper.selectDailyKeepRecordListDataByStartDayNumAndEndDayNum(DateUtil.offsetDayStr(date, -3));
        // 4天前dailyKeepRecordReport入库
        List<DailyKeepRecordReportResult> beforeDayDailyKeepRecordReportList_3 = this.dailyKeepRecordReportMapper.selectDailyKeepRecordListDataByStartDayNumAndEndDayNum(DateUtil.offsetDayStr(date, -4));
        // 5天前dailyKeepRecordReport入库
        List<DailyKeepRecordReportResult> beforeDayDailyKeepRecordReportList_4 = this.dailyKeepRecordReportMapper.selectDailyKeepRecordListDataByStartDayNumAndEndDayNum(DateUtil.offsetDayStr(date, -5));
        // 6天前dailyKeepRecordReport入库
        List<DailyKeepRecordReportResult> beforeDayDailyKeepRecordReportList_5 = this.dailyKeepRecordReportMapper.selectDailyKeepRecordListDataByStartDayNumAndEndDayNum(DateUtil.offsetDayStr(date, -6));
        // 7天前dailyKeepRecordReport入库
        List<DailyKeepRecordReportResult> beforeDayDailyKeepRecordReportList_6 = this.dailyKeepRecordReportMapper.selectDailyKeepRecordListDataByStartDayNumAndEndDayNum(DateUtil.offsetDayStr(date, -7));
        // 8天前dailyKeepRecordReport入库
        List<DailyKeepRecordReportResult> beforeDayDailyKeepRecordReportList_7 = this.dailyKeepRecordReportMapper.selectDailyKeepRecordListDataByStartDayNumAndEndDayNum(DateUtil.offsetDayStr(date, -8));
        // 15天前dailyKeepRecordReport入库
        List<DailyKeepRecordReportResult> beforeDayDailyKeepRecordReportList_14 = this.dailyKeepRecordReportMapper.selectDailyKeepRecordListDataByStartDayNumAndEndDayNum(DateUtil.offsetDayStr(date, -15));
        // 31天前dailyKeepRecordReport入库
        List<DailyKeepRecordReportResult> beforeDayDailyKeepRecordReportList_30 = this.dailyKeepRecordReportMapper.selectDailyKeepRecordListDataByStartDayNumAndEndDayNum(DateUtil.offsetDayStr(date, -31));


        DailyKeepRecordReport dailyKeepRecordReport = new DailyKeepRecordReport();
        dailyKeepRecordReport.setExecuteTime(date);
        dailyKeepRecordReport.setStatisticsDay(DateUtil.getYesterdayStr(date));

        // 分端数据
        Set<String> yesterdayLoginUserSet = Sets.newHashSet();
        //先删除，再插入
        this.dailyKeepRecordReportMapper.deleteByTime(dailyKeepRecordReport.getStatisticsDay());
        for(SourceTypeAndAllEnum keepRecordEnum : SourceTypeAndAllEnum.values()){
            Integer oneRemain;
            Integer twoRemain;
            Integer threeRemain;
            Integer fourRemain;
            Integer fiveRemain;
            Integer sixRemain;
            Integer sevenRemain;
            Integer fourteenRemain;
            Integer thirtyRemain;
            Integer count = 0;
            yesterdayLoginUserSet.clear();
            switch(keepRecordEnum.getCode().intValue()){
                case 0:
                    // 全部
                    for(Map<String, Object> map : yesterdayLoginUserList){
                        yesterdayLoginUserSet.add((String) map.get("userId"));
                    }

                    for(Map<String, Object> map : yesterdayGameStartList){
                        yesterdayLoginUserSet.add((String) map.get("userId"));
                    }

                    for(Map<String, Object> map : yesterdayUserViewAndPageViewList){
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

                    for(Map<String, Object> map : yesterdayUserViewAndPageViewList){
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

                    for(Map<String, Object> map : yesterdayGameStartList){
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

                    for(Map<String, Object> map : yesterdayGameStartList){
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

                    for(Map<String, Object> map : yesterdayUserViewAndPageViewList){
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
            oneRemain = this.calculateRemain(beforeDayRegisterUserList_1, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            twoRemain = this.calculateRemain(beforeDayRegisterUserList_2, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            threeRemain = this.calculateRemain(beforeDayRegisterUserList_3, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            fourRemain = this.calculateRemain(beforeDayRegisterUserList_4, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            fiveRemain = this.calculateRemain(beforeDayRegisterUserList_5, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            sixRemain = this.calculateRemain(beforeDayRegisterUserList_6, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            sevenRemain = this.calculateRemain(beforeDayRegisterUserList_7, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            fourteenRemain = this.calculateRemain(beforeDayRegisterUserList_14, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());
            thirtyRemain = this.calculateRemain(beforeDayRegisterUserList_30, yesterdayLoginUserSet, keepRecordEnum.getCode().intValue());

            dailyKeepRecordReport.setSourceType(keepRecordEnum.getCode());
            dailyKeepRecordReport.setOneRemain(-1);
            dailyKeepRecordReport.setTwoRemain(-1);
            dailyKeepRecordReport.setThreeRemain(-1);
            dailyKeepRecordReport.setFourRemain(-1);
            dailyKeepRecordReport.setFiveRemain(-1);
            dailyKeepRecordReport.setSixRemain(-1);
            dailyKeepRecordReport.setSevenRemain(-1);
            dailyKeepRecordReport.setFourteenRemain(-1);
            dailyKeepRecordReport.setThirtyRemain(-1);

            // 更新留存率
            for(DailyKeepRecordReport oneDayReport :beforeDayDailyKeepRecordReportList_1){
                if(keepRecordEnum.getCode().intValue() == oneDayReport.getSourceType().intValue()) {
                    oneDayReport.setOneRemain(oneRemain);
                    oneDayReport.setTwoRemain(-1);
                    oneDayReport.setThirtyRemain(-1);
                    oneDayReport.setFourteenRemain(-1);
                    oneDayReport.setFiveRemain(-1);
                    oneDayReport.setSixRemain(-1);
                    oneDayReport.setSevenRemain(-1);
                    oneDayReport.setFourteenRemain(-1);
                    oneDayReport.setThirtyRemain(-1);
                    this.dailyKeepRecordReportMapper.updateByPrimaryKey(oneDayReport);
                    break;
                }
            }

            for(DailyKeepRecordReport twoDayReport :beforeDayDailyKeepRecordReportList_2){
                if(keepRecordEnum.getCode().intValue() == twoDayReport.getSourceType().intValue()) {
                    twoDayReport.setTwoRemain(twoRemain);
                    twoDayReport.setThirtyRemain(-1);
                    twoDayReport.setFourteenRemain(-1);
                    twoDayReport.setFiveRemain(-1);
                    twoDayReport.setSixRemain(-1);
                    twoDayReport.setSevenRemain(-1);
                    twoDayReport.setFourteenRemain(-1);
                    twoDayReport.setThirtyRemain(-1);
                    this.dailyKeepRecordReportMapper.updateByPrimaryKey(twoDayReport);
                    break;
                }
            }

            for(DailyKeepRecordReport threeDayReport :beforeDayDailyKeepRecordReportList_3){
                if(keepRecordEnum.getCode().intValue() == threeDayReport.getSourceType().intValue()) {
                    threeDayReport.setThreeRemain(threeRemain);
                    threeDayReport.setFourteenRemain(-1);
                    threeDayReport.setFiveRemain(-1);
                    threeDayReport.setSixRemain(-1);
                    threeDayReport.setSevenRemain(-1);
                    threeDayReport.setFourteenRemain(-1);
                    threeDayReport.setThirtyRemain(-1);
                    this.dailyKeepRecordReportMapper.updateByPrimaryKey(threeDayReport);
                    break;
                }
            }

            for(DailyKeepRecordReport fourDayReport :beforeDayDailyKeepRecordReportList_4){
                if(keepRecordEnum.getCode().intValue() == fourDayReport.getSourceType().intValue()) {
                    fourDayReport.setFourRemain(fourRemain);
                    fourDayReport.setFiveRemain(-1);
                    fourDayReport.setSixRemain(-1);
                    fourDayReport.setSevenRemain(-1);
                    fourDayReport.setFourteenRemain(-1);
                    fourDayReport.setThirtyRemain(-1);
                    this.dailyKeepRecordReportMapper.updateByPrimaryKey(fourDayReport);
                    break;
                }
            }

            for(DailyKeepRecordReport fiveDayReport :beforeDayDailyKeepRecordReportList_5){
                if(keepRecordEnum.getCode().intValue() == fiveDayReport.getSourceType().intValue()) {
                    fiveDayReport.setFiveRemain(fiveRemain);
                    fiveDayReport.setSixRemain(-1);
                    fiveDayReport.setSevenRemain(-1);
                    fiveDayReport.setFourteenRemain(-1);
                    fiveDayReport.setThirtyRemain(-1);
                    this.dailyKeepRecordReportMapper.updateByPrimaryKey(fiveDayReport);
                    break;
                }
            }

            for(DailyKeepRecordReport sixDayReport :beforeDayDailyKeepRecordReportList_6){
                if(keepRecordEnum.getCode().intValue() == sixDayReport.getSourceType().intValue()) {
                    sixDayReport.setSixRemain(sixRemain);
                    sixDayReport.setSevenRemain(-1);
                    sixDayReport.setFourteenRemain(-1);
                    sixDayReport.setThirtyRemain(-1);
                    this.dailyKeepRecordReportMapper.updateByPrimaryKey(sixDayReport);
                    break;
                }
            }

            for(DailyKeepRecordReport sevenDayReport :beforeDayDailyKeepRecordReportList_7){
                if(keepRecordEnum.getCode().intValue() == sevenDayReport.getSourceType().intValue()) {
                    sevenDayReport.setSevenRemain(sevenRemain);
                    sevenDayReport.setFourteenRemain(-1);
                    sevenDayReport.setThirtyRemain(-1);
                    this.dailyKeepRecordReportMapper.updateByPrimaryKey(sevenDayReport);
                    break;
                }
            }

            for(DailyKeepRecordReport fourteenDayReport :beforeDayDailyKeepRecordReportList_14){
                if(keepRecordEnum.getCode().intValue() == fourteenDayReport.getSourceType().intValue()) {
                    fourteenDayReport.setFourteenRemain(fourteenRemain);
                    fourteenDayReport.setThirtyRemain(-1);
                    this.dailyKeepRecordReportMapper.updateByPrimaryKey(fourteenDayReport);
                    break;
                }
            }

            for(DailyKeepRecordReport thirtyDayReport :beforeDayDailyKeepRecordReportList_30){
                if(keepRecordEnum.getCode().intValue() == thirtyDayReport.getSourceType().intValue()) {
                    thirtyDayReport.setThirtyRemain(thirtyRemain);
                    this.dailyKeepRecordReportMapper.updateByPrimaryKey(thirtyDayReport);
                    break;
                }
            }

            this.dailyKeepRecordReportMapper.insert(dailyKeepRecordReport);
        }
        return Result.success(dailyKeepRecordReport);

    }

    private Integer calculateRemain(List<Map<String, Object>> list, Set<String> yesterdayLoginUserSet, Integer code){
        Integer count = 0;
        for(Map<String, Object> map : list){
            String userId = (String) map.get("UserId");
            if(yesterdayLoginUserSet.contains(userId)){
                if(code == 0) count++;
                else if(map.get("osType").equals(code)) count++;
            }
        }
        return count;
    }
}
