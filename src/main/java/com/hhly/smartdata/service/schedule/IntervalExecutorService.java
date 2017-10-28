package com.hhly.smartdata.service.schedule;

import com.google.common.collect.Lists;
import com.hhly.smartdata.dto.enume.InterfaceTypeEnum;
import com.hhly.smartdata.dto.enume.OperatorTypeEnum;
import com.hhly.smartdata.dto.enume.PlatformIdEnum;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.mapper.member.LoginTrackMapper;
import com.hhly.smartdata.mapper.member.RechargeRecordMapper;
import com.hhly.smartdata.mapper.member.UserInfoMapper;
import com.hhly.smartdata.mapper.smartdata.IntervalGameLaunchReportMapper;
import com.hhly.smartdata.mapper.smartdata.IntervalInterfaceReportMapper;
import com.hhly.smartdata.mapper.smartdata.IntervalSourceReportMapper;
import com.hhly.smartdata.mapper.source.DataGameStartMapper;
import com.hhly.smartdata.mapper.source.DataInterfaceInvokeMapper;
import com.hhly.smartdata.mapper.source.DataViewMapper;
import com.hhly.smartdata.model.smartdata.IntervalGameLaunchReport;
import com.hhly.smartdata.model.smartdata.IntervalInterfaceReport;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class IntervalExecutorService{

    private static final Integer INTERVAL_TIME = 30;

    @Autowired
    private DataInterfaceInvokeMapper dataInterfaceInvokeMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;

    @Autowired
    private IntervalInterfaceReportMapper intervalInterfaceReportMapper;

    @Autowired
    private IntervalSourceReportMapper intervalSourceReportMapper;

    @Autowired
    private LoginTrackMapper loginTrackMapper;

    @Autowired
    private DataGameStartMapper dataGameStartMapper;

    @Autowired
    private IntervalGameLaunchReportMapper intervalGameLaunchReportMapper;

    @Autowired
    private DataViewMapper dataViewMapper;


    public Result intervalSourceStatistics() throws Exception{
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getPointByThirtyMinute(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);

        //注册人数
        List<Map<String, Object>> firstThirtyMinRegister = userInfoMapper.selectFirstThirtyMinRegister(startDate, INTERVAL_TIME);

        //登陆人数
        List<Map<String, Object>> firstThirtyMinLoginUser = loginTrackMapper.selectFirstThirtyMinLoginUser(startDate, INTERVAL_TIME);

        // 充值人数、充值金额、充值次数
        List<Map<String, Object>> firstThirtyMinRechargeUser = rechargeRecordMapper.selectFirstThirtyMinRechargeUser(startDate, INTERVAL_TIME);

        //启动表
        List<Map<String, Object>> firstThirtyMinGameStartCount = dataGameStartMapper.selectFirstThirtyMinGameStartCount(startDate, INTERVAL_TIME);

        // uv
        List<Map<String, Object>> firstThirtyMinUserViewAndPageView = dataViewMapper.selectFirstThirtyMinUserViewAndPageView(startDate, INTERVAL_TIME);

        List<IntervalSourceReport> list = Lists.newArrayList();
        for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){
            IntervalSourceReport report = new IntervalSourceReport();
            report.setIntervalTime(INTERVAL_TIME);
            report.setSourceType(sourceTypeEnum.getCode());
            report.setStatisticsTime(startDate);
            report.setExecuteTime(now);
            Integer loginPopulation = 0;
            for(Map<String, Object> firstThirtyMinRegisterMap : firstThirtyMinRegister){
                if(sourceTypeEnum.getCode() == (firstThirtyMinRegisterMap.get("osType") == null ? 0 : Integer.valueOf(firstThirtyMinRegisterMap.get("osType").toString()))){
                    report.setRegisterPopulation(firstThirtyMinRegisterMap.get("registerPopulation") == null ? 0 : Integer.valueOf(firstThirtyMinRegisterMap.get("registerPopulation") + ""));
                    break;
                }
            }

            for(Map<String, Object> firstThirtyMinLoginUserMap : firstThirtyMinLoginUser){
                if(sourceTypeEnum.getCode() == (firstThirtyMinLoginUserMap.get("osType") == null ? 0 : Integer.valueOf(firstThirtyMinLoginUserMap.get("osType").toString()))){
                    loginPopulation = firstThirtyMinLoginUserMap.get("loginPopulation") == null ? 0 : Integer.valueOf(firstThirtyMinLoginUserMap.get("loginPopulation") + "");
                    report.setLoginPopulation(loginPopulation);
                    break;
                }
            }

            for(Map<String, Object> firstThirtyMinRechargeUserMap : firstThirtyMinRechargeUser){
                if(sourceTypeEnum.getCode() == (firstThirtyMinRechargeUserMap.get("osType") == null ? 0 : Integer.valueOf(firstThirtyMinRechargeUserMap.get("osType").toString()))){
                    report.setRechargeAmount((BigDecimal) (firstThirtyMinRechargeUserMap.get("rechargeAmount")));
                    report.setRechargeCount(firstThirtyMinRechargeUserMap.get("rechargeCount") == null ? 0 : Integer.valueOf(firstThirtyMinRechargeUserMap.get("rechargeCount") + ""));
                    report.setRechargePopulation(firstThirtyMinRechargeUserMap.get("rechargePopulation") == null ? 0 : Integer.valueOf(firstThirtyMinRechargeUserMap.get("rechargePopulation") + ""));
                    break;
                }
            }

            for(Map<String, Object> firstThirtyMinGameStartCountMap : firstThirtyMinGameStartCount){
                if(sourceTypeEnum.getCode() == 1 || sourceTypeEnum.getCode() == 4){
                    if(sourceTypeEnum.getCode() == (firstThirtyMinGameStartCountMap.get("osType") == null ? 0 : Integer.valueOf(firstThirtyMinGameStartCountMap.get("osType").toString()))){
                        report.setLoginPopulation(loginPopulation + (firstThirtyMinGameStartCountMap.get("loginPopulation") == null ? 0 :
                                Integer.valueOf(firstThirtyMinGameStartCountMap.get("loginPopulation") + "")));
                        break;
                    }
                }
            }

            for(Map<String, Object> firstThirtyMinUserViewAndPageViewMap : firstThirtyMinUserViewAndPageView){
                if(sourceTypeEnum.getCode() == 2 || sourceTypeEnum.getCode() == 3){
                    if(sourceTypeEnum.getCode() == (firstThirtyMinUserViewAndPageViewMap.get("osType") == null ? 0 : Integer.valueOf(firstThirtyMinUserViewAndPageViewMap.get("osType").toString()))){
                        report.setLoginPopulation(loginPopulation + (firstThirtyMinUserViewAndPageViewMap.get("loginPopulation") == null ? 0 :
                                Integer.valueOf(firstThirtyMinUserViewAndPageViewMap.get("loginPopulation") + "")));
                        break;
                    }
                }
            }

            //先删除，再插入
            this.intervalSourceReportMapper.deleteByTimeSourceType(report.getStatisticsTime(), report.getSourceType());
            this.intervalSourceReportMapper.insert(report);
            list.add(report);
        }
        return Result.success(list);
    }

    public Result intervalInterfaceStatistics() throws Exception{
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getPointByThirtyMinute(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);

        // 埋点接口数据
        List<Map<String, Object>> dataInterfaceInvokeList = dataInterfaceInvokeMapper.findDataInterfaceInvokeList(startDate, INTERVAL_TIME);

        // 用户注册数据
        Integer userCount = userInfoMapper.findUserInfoByTime(startDate, INTERVAL_TIME);

        // 充值数据
        Integer rechargeCount = rechargeRecordMapper.findRechargeRecordByTime(startDate, INTERVAL_TIME);

        List<IntervalInterfaceReport> list = Lists.newArrayList();

        for(InterfaceTypeEnum interfaceTypeEnum : InterfaceTypeEnum.values()){ // 注册 充值
            for(OperatorTypeEnum operatorTypeEnum : OperatorTypeEnum.values()){ // 请求 完成
                IntervalInterfaceReport intervalInterfaceReport = new IntervalInterfaceReport();
                intervalInterfaceReport.setStatisticsTime(startDate);
                intervalInterfaceReport.setOperateType(operatorTypeEnum.getCode());
                intervalInterfaceReport.setInterfaceName(interfaceTypeEnum.getDesc());
                intervalInterfaceReport.setInterfaceCode(interfaceTypeEnum.getCode().intValue());
                intervalInterfaceReport.setIntervalTime(INTERVAL_TIME);
                intervalInterfaceReport.setExecuteTime(now);
                // 请求
                if(operatorTypeEnum.getCode() == 1){
                    for(Map<String, Object> invokeMap : dataInterfaceInvokeList){
                        int type = invokeMap.get("interfaceType") == null ? 0 : Integer.valueOf(invokeMap.get("interfaceType") + "");
                        if(interfaceTypeEnum.getCode() == type){
                            intervalInterfaceReport.setOperateCount(invokeMap.get("invokeCount") == null ? 0 : Integer.valueOf(invokeMap.get("invokeCount") + ""));
                        }
                    }
                }
                // 完成
                if(operatorTypeEnum.getCode() == 2){
                    if(interfaceTypeEnum.getCode() == 1){ // 注册
                        intervalInterfaceReport.setOperateCount(userCount);
                    }else if(interfaceTypeEnum.getCode() == 2){
                        intervalInterfaceReport.setOperateCount(rechargeCount);
                    }
                }
                //先删除，再插入
                this.intervalInterfaceReportMapper.deleteByTimeAndOperateTypeAndInterfaceCode(intervalInterfaceReport.getStatisticsTime(), intervalInterfaceReport.getOperateType(), intervalInterfaceReport.getInterfaceCode());
                this.intervalInterfaceReportMapper.insert(intervalInterfaceReport);
                list.add(intervalInterfaceReport);
            }
        }
        return Result.success(list);
    }

    public Result intervalGameLaunch() throws Exception{
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getPointByThirtyMinute(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);
        // 游戏启动数据
        List<Map<String, Object>> platformAllGameStartCountMapList = dataGameStartMapper.selectPlatformAllGameStartCount(startDate, INTERVAL_TIME);
        if(CollectionUtils.isEmpty(platformAllGameStartCountMapList)){
            return Result.fail("没有需要统计的数据");
        }

        List<IntervalGameLaunchReport> list = Lists.newArrayList();
        for(PlatformIdEnum platformIdEnum : PlatformIdEnum.values()){
            for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){
                IntervalGameLaunchReport intervalGameLaunchReport = new IntervalGameLaunchReport();
                intervalGameLaunchReport.setExecuteTime(now);
                intervalGameLaunchReport.setStatisticsTime(startDate);
                intervalGameLaunchReport.setIntervalTime(INTERVAL_TIME);
                intervalGameLaunchReport.setSourceType(sourceTypeEnum.getCode());
                intervalGameLaunchReport.setPlatformId(platformIdEnum.getCode());
                intervalGameLaunchReport.setPlatformName(platformIdEnum.getDesc());

                for(Map<String, Object> item : platformAllGameStartCountMapList){
                    if(item.get("sourceType").equals(sourceTypeEnum.getCode()) && item.get("platformId").equals(platformIdEnum.getCode())){
                        intervalGameLaunchReport.setLaunchCount(item.get("gameStartCount") == null ? 0 : Integer.valueOf(item.get("gameStartCount") + ""));
                        break;
                    }
                }
                // 入库接口统计结果表
                this.intervalGameLaunchReportMapper.deleteByTimeAndSourceTypeAndPlatformId(intervalGameLaunchReport.getStatisticsTime(), intervalGameLaunchReport.getSourceType(), intervalGameLaunchReport.getPlatformId());
                this.intervalGameLaunchReportMapper.insert(intervalGameLaunchReport);
                list.add(intervalGameLaunchReport);
            }
        }
        return Result.success(list);
    }
}
