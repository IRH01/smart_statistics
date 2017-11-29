package com.hhly.smartdata.service.schedule;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.mapper.member.LoginTrackMapper;
import com.hhly.smartdata.mapper.member.PlatformGoldConsumeMapper;
import com.hhly.smartdata.mapper.member.RechargeRecordMapper;
import com.hhly.smartdata.mapper.member.SystemConfigMapper;
import com.hhly.smartdata.mapper.member.UserInfoMapper;
import com.hhly.smartdata.mapper.smartdata.DailyCompositeReportMapper;
import com.hhly.smartdata.mapper.smartdata.MonthCompositeReportMapper;
import com.hhly.smartdata.mapper.smartdata.MonthLoginReportMapper;
import com.hhly.smartdata.mapper.smartdata.MonthRechargeReportMapper;
import com.hhly.smartdata.mapper.smartdata.MonthRegisterReportMapper;
import com.hhly.smartdata.mapper.source.DataGameStartMapper;
import com.hhly.smartdata.mapper.source.DataInstallsMapper;
import com.hhly.smartdata.mapper.source.DataViewMapper;
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
import java.util.Set;

/**
 * Created by Iritchie.ren on 2017/9/26.
 */
@Service
public class MonthExecutorService{

    @Autowired
    private DailyCompositeReportMapper dailyCompositeReportMapper;

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

    @Autowired
    private LoginTrackMapper loginTrackMapper;

    @Autowired
    private DataGameStartMapper dataGameStartMapper;

    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;

    @Autowired
    private DataViewMapper dataViewMapper;

    @Autowired
    private PlatformGoldConsumeMapper platformGoldConsumeMapper;

    @Autowired
    private DataInstallsMapper dataInstallsMapper;

    public Result compositeReport(Date date) throws Exception{
        //总的注册用户数量
        Long userCount = this.userInfoMapper.selectUserCount(DateUtil.offsetMonthStartTime(date, 0));

        //上月注册用户列表
        List<String> newRegisterUserIdList = userInfoMapper.selectUserIdByStartTimeAndEndTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        Set<String> newRegisterUserIdSet = Sets.newHashSet(newRegisterUserIdList);

        //老用户注册列表
        List<String> oldRegisterUserIdList = userInfoMapper.selectOldRegisterUserByTime(DateUtil.offsetMonthStartTime(date, -1));
        Set<String> oldRegisterUserIdSet = Sets.newHashSet(oldRegisterUserIdList);

        //进入游戏的用户列表
        List<String> startGameUserIdList = dataGameStartMapper.selectGameStartByTime(DateUtil.date2String(DateUtil.offsetMonthStartTime(date, -1)), DateUtil.date2String(DateUtil.offsetMonthEndTime(date, -1)));
        Set<String> startGameUserIdSet = Sets.newHashSet(startGameUserIdList);
        //充值的用户记录，平台 + 子公司，userId, orderNo, applyAmount,
        List<Map<String, Object>> rechargeUserPlatformList = this.rechargeRecordMapper.selectRechargeRecordByTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        //充值的用户列表
        Set<String> rechargeUserIdSet = Sets.newHashSet();
        //充值次数
        //充值金额
        for(Map<String, Object> item : rechargeUserPlatformList){
            rechargeUserIdSet.add((String) item.get("userId"));
        }

        // 登录用户列表
        List<String> loginUserIdList = this.loginTrackMapper.selectLoginUserPopulationByTime(DateUtil.date2String(DateUtil.offsetMonthStartTime(date, -1)), DateUtil.date2String(DateUtil.offsetMonthEndTime(date, -1)));
        //APP启动表（手机第二次打开，算第二次登录，没有走登录接口）
        List<String> appStartUserIdList = dataGameStartMapper.selectGameStartByTime(DateUtil.date2String(DateUtil.offsetMonthStartTime(date, -1)), DateUtil.date2String(DateUtil.offsetMonthEndTime(date, -1)));
        //H5的uv（手机第二次打开，算第二次登录，没有走登录接口）
        List<String> userViewUserIdList = dataViewMapper.selectUserViewAndPageViewByTime(DateUtil.date2String(DateUtil.offsetMonthStartTime(date, -1)), DateUtil.date2String(DateUtil.offsetMonthEndTime(date, -1)));
        List<String> loginUserIdTotalList = Lists.newArrayList();
        loginUserIdTotalList.addAll(loginUserIdList);
        loginUserIdTotalList.addAll(appStartUserIdList);
        loginUserIdTotalList.addAll(userViewUserIdList);
        Set<String> loginUserIdTotalSet = Sets.newHashSet(loginUserIdTotalList);

        //消费的用户列表,并转换成Set集合。
        //用户消费记录
        List<String> consumeUserList = this.platformGoldConsumeMapper.selectConsumeUserIdByStartTimeAndEndTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        Set<String> consumeUserSet = Sets.newHashSet(consumeUserList);

        //#注册体验量
        Integer registerExperience = 0;
        //##真体验量
        Integer realExpCount = 0;
        //##假体验量
        Integer virtualExpCount = 0;
        //新用户充值人数;
        Integer newUserRechargePopulation = 0;
        //新用户充值金额
        BigDecimal newUserRechargeAmount = new BigDecimal(0.00);
        //新用户充值次数
        Integer newUserRechargeCount = 0;
        //新用户玩游戏人数
        Integer newUserPlayCount = 0;
        //新用户登录人数
        Integer newUserLoginCount = 0;
        for(String userId : newRegisterUserIdSet){
            //体验量
            if(startGameUserIdSet.contains(userId)){
                //注册体验量
                registerExperience++;
                if(consumeUserSet.contains(userId)){
                    //真体验量
                    realExpCount++;
                }else{
                    //假体验量
                    virtualExpCount++;
                }
            }

            //新用户充值人数;
            if(rechargeUserIdSet.contains(userId)){
                newUserRechargePopulation++;
            }

            //新用户登录人数
            //新用户玩游戏人数
            if(loginUserIdTotalSet.contains(userId)){
                newUserLoginCount++;
                if(startGameUserIdSet.contains(userId)){
                    newUserPlayCount++;
                }
            }

            for(Map<String, Object> item : rechargeUserPlatformList){
                //userId, orderNo, applyAmount
                String userIdTemp = (String) item.get("userId");
                if(userIdTemp.equals(userId)){
                    newUserRechargeAmount = newUserRechargeAmount.add(item.get("applyAmount") == null ? new BigDecimal(0.00) : (BigDecimal) item.get("applyAmount"));
                    newUserRechargeCount++;
                }
            }
        }

        //老用户充值人数;
        Integer oldUserRechargePopulation = 0;
        //老用户玩游戏人数
        Integer oldUserPlayCount = 0;
        //老用户充值金额
        BigDecimal oldUserRechargeAmount = new BigDecimal(0.00);
        //老用户充值次数
        Integer oldUserRechargeCount = 0;
        //老用户登录人数
        Integer oldUserLoginCount = 0;
        for(String userId : oldRegisterUserIdSet){
            //老用户充值人数;
            if(rechargeUserIdSet.contains(userId)){
                oldUserRechargePopulation++;
            }

            //老用户玩游戏人数
            if(loginUserIdTotalSet.contains(userId)){
                oldUserLoginCount++;
                if(startGameUserIdSet.contains(userId)){
                    oldUserPlayCount++;
                }
            }

            for(Map<String, Object> item : rechargeUserPlatformList){
                //userId, orderNo, applyAmount
                String userIdTemp = (String) item.get("userId");
                if(userIdTemp.equals(userId)){
                    oldUserRechargeCount++;
                    oldUserRechargeAmount = oldUserRechargeAmount.add(item.get("applyAmount") == null ? new BigDecimal(0.00) : (BigDecimal) item.get("applyAmount"));
                }
            }
        }

        MonthCompositeReport monthCompositeReport = new MonthCompositeReport();
        monthCompositeReport.setStatisticsMonth(DateUtil.getLastMonthStr(date));
        monthCompositeReport.setExecuteTime(date);
        monthCompositeReport.setTotalRegisterPopulation(userCount == null ? 0 : userCount.intValue());
        monthCompositeReport.setRegisterPopulation(newRegisterUserIdSet.size());
        monthCompositeReport.setRegisterExpCount(registerExperience);
        monthCompositeReport.setRealExpCount(realExpCount);
        monthCompositeReport.setVirtualExpCount(virtualExpCount);
        monthCompositeReport.setNewUserLoginCount(newUserLoginCount);
        monthCompositeReport.setNewUserRechargePopulation(newUserRechargePopulation);
        monthCompositeReport.setNewUserRechargeCount(newUserRechargeCount);
        //玩游戏人数
        monthCompositeReport.setNewUserPlayCount(newUserPlayCount);
        monthCompositeReport.setNewUserRechargeAmount(newUserRechargeAmount);
        monthCompositeReport.setOldUserLoginCount(oldUserLoginCount);
        monthCompositeReport.setOldUserRechargePopulation(oldUserRechargePopulation);
        monthCompositeReport.setOldUserRechargeAmount(oldUserRechargeAmount);
        monthCompositeReport.setOldUserRechargeCount(oldUserRechargeCount);
        monthCompositeReport.setOldUserPlayCount(oldUserPlayCount);
        Map<String, Object> lastMonthCompositeMap = this.dailyCompositeReportMapper.selectLastMonthComposite(DateUtil.getLastMonthFirstDayStr(date), DateUtil.getLastMonthEndDayStr(date));
        monthCompositeReport.setNextDayStayCount(lastMonthCompositeMap.get("next_day_stay_count_avg") == null ? 0 : ((BigDecimal) lastMonthCompositeMap.get("next_day_stay_count_avg")).intValue());

        //先删除，再插入
        this.monthCompositeReportMapper.deleteByTime(monthCompositeReport.getStatisticsMonth());
        this.monthCompositeReportMapper.insert(monthCompositeReport);
        return Result.success(monthCompositeReport);
    }

    public Result registerReport(Date date) throws Exception{
        String statisticsMonth = DateUtil.getLastMonthStr(date);
        // 前一月各端注册用户数
        List<Map<String, Object>> registerUserIdAndTerminalList = userInfoMapper.selectRegisterUserIdAndTerminalByStartTimeAndEndTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        // 前一月pv和uv
        List<Map<String, Object>> userViewList = dataViewMapper.selectUserViewByStartTimeAndEndTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        List<Map<String, Object>> pageViewList = dataViewMapper.selectPageViewByStartTimeAndEndTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        // 前一月app安装量
        List<Map<String, Object>> installsList = dataInstallsMapper.selectInstallsByStartTimeAndEndTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));

        MonthRegisterReport monthRegisterReport = new MonthRegisterReport();
        monthRegisterReport.setExecuteTime(date);
        monthRegisterReport.setStatisticsMonth(statisticsMonth);


        for(Map<String, Object> registerMap : registerUserIdAndTerminalList){
            Integer sourceType = (Integer) registerMap.get("osType");
            switch(sourceType){
                case 1:
                    monthRegisterReport.setPcPopulation(registerMap.get("userCount") == null ? 0 : ((Long) registerMap.get("userCount")).intValue());
                    break;
                case 2:
                    monthRegisterReport.setAndroidPopulation(registerMap.get("userCount") == null ? 0 : ((Long) registerMap.get("userCount")).intValue());
                    break;
                case 3:
                    monthRegisterReport.setIosPopulation(registerMap.get("userCount") == null ? 0 : ((Long) registerMap.get("userCount")).intValue());
                    break;
                case 4:
                    monthRegisterReport.setH5Population(registerMap.get("userCount") == null ? 0 : ((Long) registerMap.get("userCount")).intValue());
                    break;
            }
        }

        for(Map<String, Object> userViewMap : userViewList){
            switch(Integer.valueOf(userViewMap.get("sourceType") + "")){
                case 1:
                    monthRegisterReport.setPcUserView(userViewMap.get("userCount") == null ? 0 : ((Long) userViewMap.get("userCount")).intValue());
                    break;
                case 4:
                    monthRegisterReport.setH5UserView(userViewMap.get("userCount") == null ? 0 : ((Long) userViewMap.get("userCount")).intValue());
                    break;
            }
        }

        for(Map<String, Object> userViewMap : pageViewList){
            switch(Integer.valueOf(userViewMap.get("sourceType") + "")){
                case 1:
                    monthRegisterReport.setPcPageView(userViewMap.get("pageCount") == null ? 0 : (Long) userViewMap.get("pageCount"));
                    break;
                case 4:
                    monthRegisterReport.setH5PageView((userViewMap.get("pageCount") == null ? 0L : (Long) userViewMap.get("pageCount")));
                    break;
            }
        }

        for(Map<String, Object> installsMap : installsList){
            Integer sourceType = (Integer) installsMap.get("platformTerminal");
            if(sourceType == null){
                continue;
            }
            switch(sourceType){
                case 2:
                    monthRegisterReport.setAndroidInstallCount(((Long) installsMap.get("uniqueCount")).intValue());
                    break;
                case 3:
                    monthRegisterReport.setIosInstallCount(((Long) installsMap.get("uniqueCount")).intValue());
                    break;
            }
        }

        //先删除，再插入
        this.monthRegisterReportMapper.deleteByTime(monthRegisterReport.getStatisticsMonth());
        this.monthRegisterReportMapper.insert(monthRegisterReport);
        return Result.success(monthRegisterReport);
    }

    public Result rechargeReport(Date date) throws Exception{
        String monthStr = DateUtil.getLastMonthStr(date);

        //查询昨天的充值记录
        List<Map<String, Object>> rechargeRecordList = this.rechargeRecordMapper.selectByStartTimeAndEndTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        //查询昨天的新用户
        List<Map<String, Object>> registerNewUserList = this.rechargeRecordMapper.selectNewUserAndRechargeByStartTimeAndEndTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        //查询昨天以前的老用户
        List<Map<String, Object>> registerOldUserList = this.rechargeRecordMapper.selectOldUserAndRechargeByStartTimeAndEndTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        List<MonthRechargeReport> monthRechargeReportList = Lists.newArrayList();
        //登陆用户列表
        List<Map<String, Object>> loginUserList = loginTrackMapper.selectLoginUserPopulationByTimeGroupBySource(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        //APP启动表（手机第二次打开，算第二次登录，没有走登录接口）
        List<Map<String, Object>> loginUserGameStart = dataGameStartMapper.selectGameStartByTimeGroupBySource(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        //H5的uv（手机第二次打开，算第二次登录，没有走登录接口）
        List<Map<String, Object>> LoginUserUserView = dataViewMapper.selectUserViewAndPageViewByTimeGroupBySource(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        List<Map<String, Object>> loginUserTotalList = Lists.newArrayList();
        loginUserTotalList.addAll(loginUserList);
        loginUserTotalList.addAll(loginUserGameStart);
        loginUserTotalList.addAll(LoginUserUserView);

        for(SourceTypeEnum item : SourceTypeEnum.values()){
            MonthRechargeReport monthRechargeReport = new MonthRechargeReport();
            monthRechargeReport.setSourceType(item.getCode());
            monthRechargeReport.setStatisticsMonth(monthStr);
            monthRechargeReport.setExecuteTime(date);
            for(Map<String, Object> map : rechargeRecordList){
                if(((Long) map.get("sourceType")).byteValue() == item.getCode()){
                    monthRechargeReport.setRechargeAmount(map.get("amountSum") == null ? new BigDecimal(0) : (BigDecimal) map.get("amountSum"));
                    monthRechargeReport.setRechargePopulation((map.get("userCount") == null ? 0 : ((Long) map.get("userCount")).intValue()));
                    monthRechargeReport.setRechargeCount((map.get("orderCount") == null ? 0 : ((Long) map.get("orderCount")).intValue()));
                    break;
                }
            }

            for(Map<String, Object> map : registerNewUserList){
                if(((Long) map.get("sourceType")).byteValue() == item.getCode()){
                    monthRechargeReport.setNewRechargePopulation((map.get("userCount") == null ? 0 : ((Long) map.get("userCount")).intValue()));
                    break;
                }
            }

            for(Map<String, Object> map : registerOldUserList){
                if(((Long) map.get("sourceType")).byteValue() == item.getCode()){
                    monthRechargeReport.setOldRechargePopulation((map.get("userCount") == null ? 0 : ((Long) map.get("userCount")).intValue()));
                    break;
                }
            }

            Set<String> loginUserIdSet = Sets.newHashSet();
            for(Map<String, Object> map : loginUserTotalList){
                if(((Integer) map.get("sourceType")).byteValue() == item.getCode()){
                    loginUserIdSet.add(new String((byte[]) map.get("userId")));
                }
            }
            monthRechargeReport.setLoginPopulation(loginUserIdSet.size());

            //先删除记录，再执行插入
            this.monthRechargeReportMapper.deleteByTimeAndSourceType(monthRechargeReport.getStatisticsMonth(), monthRechargeReport.getSourceType());
            this.monthRechargeReportMapper.insert(monthRechargeReport);
            monthRechargeReportList.add(monthRechargeReport);
        }

        return Result.success(monthRechargeReportList);
    }

    public Result loginSourceReport(Date date) throws Exception{
        String monthStr = DateUtil.getLastMonthStr(date);
        //登录的账号列表,并转换成Set集合。分端登录
        //登录的账号列表,并转换成Set集合。分端登录，部分平台，只有玩一下平台。
        List<Map<String, Object>> loginUserList = this.loginTrackMapper.selectLoginUserPopulationByTimeGroupBySource(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        //APP启动表（手机第二次打开，算第二次登录，没有走登录接口）
        List<Map<String, Object>> loginUserGameStart = dataGameStartMapper.selectGameStartByTimeGroupBySource(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        //H5的uv（手机第二次打开，算第二次登录，没有走登录接口）
        List<Map<String, Object>> LoginUserUserView = dataViewMapper.selectUserViewAndPageViewByTimeGroupBySource(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        List<Map<String, Object>> loginUserTotalList = Lists.newArrayList();
        loginUserTotalList.addAll(loginUserList);
        loginUserTotalList.addAll(loginUserGameStart);
        loginUserTotalList.addAll(LoginUserUserView);

        // 启动游戏的账号列表, 并转换成Set集合。
        List<Map<String, Object>> launchGameUserList = this.dataGameStartMapper.selectLaunchGameUserByStartTimeAndEndTime(DateUtil.offsetMonthStartTime(date, -1), DateUtil.offsetMonthEndTime(date, -1));
        // 用户登录的临时set不重复容器
        Set<String> tempUserIdSet = Sets.newHashSet();

        List<MonthLoginReport> monthRechargeReportList = Lists.newArrayList();
        String configValue = this.systemConfigMapper.getConfigValueByKey("hhly:playOne:systemConfig:common:platformCode");
        Map<String, String> platformIdMap = JsonUtil.jsonStr2Map(configValue);

        //所有终端的综合数据
        MonthLoginReport monthLoginReportAll = new MonthLoginReport();
        monthLoginReportAll.setExecuteTime(date);
        monthLoginReportAll.setPlatformCode("all");
        monthLoginReportAll.setPlatformName("");
        monthLoginReportAll.setSourceType(Byte.parseByte("0"));
        monthLoginReportAll.setStatisticsMonth(monthStr);
        //初始化临时容器
        tempUserIdSet.clear();
        for(Map<String, Object> item : loginUserTotalList){
            tempUserIdSet.add(new String((byte[]) item.get("userId")));
        }
        monthLoginReportAll.setLoginPopulation(tempUserIdSet.size());

        //初始化临时容器
        tempUserIdSet.clear();
        for(Map<String, Object> item : launchGameUserList){
            tempUserIdSet.add(new String((byte[]) item.get("userId")));
        }
        monthLoginReportAll.setPlayPopulation(tempUserIdSet.size());

        //先删除，再插入
        this.monthLoginReportMapper.deleteByTimeAndPlatformCodeAndSourceType(monthLoginReportAll.getStatisticsMonth(),
                monthLoginReportAll.getPlatformCode(), monthLoginReportAll.getSourceType());
        this.monthLoginReportMapper.insert(monthLoginReportAll);

        for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){
            //每个终端的综合数据
            MonthLoginReport monthLoginReportComp = new MonthLoginReport();
            monthLoginReportComp.setExecuteTime(date);
            monthLoginReportComp.setPlatformCode("zong_he");
            monthLoginReportComp.setPlatformName("");
            monthLoginReportComp.setSourceType(sourceTypeEnum.getCode());
            monthLoginReportComp.setStatisticsMonth(monthStr);
            //初始化临时容器
            tempUserIdSet.clear();
            for(Map<String, Object> item : loginUserTotalList){
                Integer sourceType = (Integer) item.get("sourceType");
                if(sourceType == sourceTypeEnum.getCode().intValue()){
                    tempUserIdSet.add(new String((byte[]) item.get("userId")));
                }
            }
            monthLoginReportComp.setLoginPopulation(tempUserIdSet.size());

            //初始化临时容器
            tempUserIdSet.clear();
            for(Map<String, Object> item : launchGameUserList){
                Integer sourceType = (Integer) item.get("sourceType");
                if(sourceType == sourceTypeEnum.getCode().intValue()){
                    tempUserIdSet.add(new String((byte[]) item.get("userId")));
                }
            }
            monthLoginReportComp.setPlayPopulation(tempUserIdSet.size());

            //先删除，再插入
            this.monthLoginReportMapper.deleteByTimeAndPlatformCodeAndSourceType(monthLoginReportComp.getStatisticsMonth(),
                    monthLoginReportComp.getPlatformCode(), monthLoginReportComp.getSourceType());
            this.monthLoginReportMapper.insert(monthLoginReportComp);

            for(String platformCode : platformIdMap.keySet()){
                MonthLoginReport monthLoginReport = new MonthLoginReport();
                monthLoginReport.setExecuteTime(date);
                monthLoginReport.setStatisticsMonth(monthStr);
                monthLoginReport.setSourceType(sourceTypeEnum.getCode());
                monthLoginReport.setPlatformCode(platformCode);
                monthLoginReport.setPlatformName(platformIdMap.get(platformCode));
                //注册&当日登录人数；当日注册&当日玩游戏人数
                monthLoginReport.setLoginPopulation(0);

                //初始化临时容器
                tempUserIdSet.clear();
                for(Map<String, Object> item : launchGameUserList){
                    String platformCodeStr = (String) item.get("platformCode");
                    Integer sourceType = (Integer) item.get("sourceType");
                    if(sourceType == sourceTypeEnum.getCode().intValue() && platformCodeStr != null && platformCodeStr.equals(platformCode)){
                        tempUserIdSet.add(new String((byte[]) item.get("userId")));
                    }
                }
                monthLoginReport.setPlayPopulation(tempUserIdSet.size());

                //先删除，再插入
                this.monthLoginReportMapper.deleteByTimeAndPlatformCodeAndSourceType(monthLoginReport.getStatisticsMonth(),
                        monthLoginReport.getPlatformCode(), monthLoginReport.getSourceType());
                this.monthLoginReportMapper.insert(monthLoginReport);
                monthRechargeReportList.add(monthLoginReport);
            }
        }
        return Result.success(monthRechargeReportList);
    }
}
