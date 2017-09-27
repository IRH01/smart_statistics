package com.hhly.smartdata.service.schedule;

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
import com.hhly.smartdata.model.source.DataInterfaceInvoke;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class IntervalExecutorService{

    private static final Integer INTERVALTIME =30;

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
        //注册人数
        List<Map<String,Object>> firstThirtyMinRegister = userInfoMapper.selectFirstThirtyMinRegister();

        //登陆人数
        List<Map<String,Object>>  firstThirtyMinLoginUser = loginTrackMapper.selectFirstThirtyMinLoginUser();

        // 充值人数、充值金额、充值次数
        List<Map<String,Object>>  firstThirtyMinRechargeUser = rechargeRecordMapper.selectFirstThirtyMinRechargeUser();

        //启动表
        List<Map<String,Object>>  firstThirtyMinGameStartCount = dataGameStartMapper.selectFirstThirtyMinGameStartCount();

        // uv
        List<Map<String,Object>>  firstThirtyMinUserViewAndPageView =  dataViewMapper.selectFirstThirtyMinUserViewAndPageView();

        Date now = new Date();
        String startDate = DateUtil.getFirstThirtyMinStr(now);
        List<IntervalSourceReport> list = new ArrayList();
        for (SourceTypeEnum sourceTypeEnum:SourceTypeEnum.values()) {
            IntervalSourceReport report = new IntervalSourceReport();
            report.setSourceType(sourceTypeEnum.getCode());
            report.setStatisticsTime(startDate);
            report.setExecuteTime(now);
            Integer loginPopulation = 0;

            for (Map<String,Object> firstThirtyMinRegisterMap:firstThirtyMinRegister) {
                if (sourceTypeEnum.getCode() == firstThirtyMinRegisterMap.get("osType")) {
                    report.setRegisterPopulation(firstThirtyMinRegisterMap.get("registerPopulation") == null ?0:Integer.valueOf(firstThirtyMinRegisterMap.get("registerPopulation")+""));
                }
            }

            for (Map<String,Object> firstThirtyMinLoginUserMap:firstThirtyMinLoginUser) {
                if (sourceTypeEnum.getCode() == firstThirtyMinLoginUserMap.get("osType")) {
                    loginPopulation = firstThirtyMinLoginUserMap.get("loginPopulation") == null ? 0 :Integer.valueOf(firstThirtyMinLoginUserMap.get("loginPopulation")+"");
                    report.setLoginPopulation(loginPopulation);
                }
            }

            for (Map<String,Object> firstThirtyMinRechargeUserMap:firstThirtyMinRechargeUser) {
                if (sourceTypeEnum.getCode() == firstThirtyMinRechargeUserMap.get("osType")) {
                    report.setRechargeAmount((BigDecimal) (firstThirtyMinRechargeUserMap.get("rechargeAmount")));
                    report.setRechargeCount(firstThirtyMinRechargeUserMap.get("rechargeCount") == null ? 0:Integer.valueOf(firstThirtyMinRechargeUserMap.get("rechargeCount")+""));
                    report.setRechargePopulation(firstThirtyMinRechargeUserMap.get("rechargePopulation") == null ? 0:Integer.valueOf(firstThirtyMinRechargeUserMap.get("rechargePopulation")+""));
                }
            }

            for (Map<String,Object> firstThirtyMinGameStartCountMap:firstThirtyMinGameStartCount) {
                if (sourceTypeEnum.getCode() == 1 || sourceTypeEnum.getCode() == 4) {
                    if (sourceTypeEnum.getCode() == firstThirtyMinGameStartCountMap.get("osType") ) {
                        report.setLoginPopulation(loginPopulation + (firstThirtyMinGameStartCountMap.get("loginPopulation")==null?0:
                                Integer.valueOf(firstThirtyMinGameStartCountMap.get("loginPopulation")+"")));
                    }
                }
            }

            for (Map<String,Object> firstThirtyMinUserViewAndPageViewMap:firstThirtyMinUserViewAndPageView) {
                if (sourceTypeEnum.getCode() == 2 || sourceTypeEnum.getCode() == 3){
                    if(sourceTypeEnum.getCode() == firstThirtyMinUserViewAndPageViewMap.get("osType")){
                        report.setLoginPopulation(loginPopulation + (firstThirtyMinUserViewAndPageViewMap.get("loginPopulation")==null?0:
                                Integer.valueOf(firstThirtyMinUserViewAndPageViewMap.get("loginPopulation")+"")));
                    }
                }
            }

            intervalSourceReportMapper.insert(report);
            list.add(report);
        }
        return Result.success(list);


    }

    public Result intervalInterfaceStatistics() throws Exception{
         // 埋点接口数据
        List<Map<String,Object>> dataInterfaceInvokeList = dataInterfaceInvokeMapper.findDataInterfaceInvokeList();

        // 用户注册数据
        Integer  userCount = userInfoMapper.findUserInfoByTime();

        // 充值数据
        Integer rechargeCount = rechargeRecordMapper.findRechargeRecordByTime();

        List<IntervalInterfaceReport> list = new ArrayList();
        Date now = new Date();
        for (Map<String,Object> invokeMap:dataInterfaceInvokeList) {
            IntervalInterfaceReport intervalInterfaceReport = new IntervalInterfaceReport();
            Integer type = invokeMap.get("interfaceType") == null?0:Integer.valueOf(invokeMap.get("interfaceType")+"");
            for (InterfaceTypeEnum interfaceTypeEnum:InterfaceTypeEnum.values()) {
                if (type == 1 && interfaceTypeEnum.getCode() == 1) {
                    intervalInterfaceReport.setOperateType(interfaceTypeEnum.getCode());
                    intervalInterfaceReport.setInterfaceName(interfaceTypeEnum.getDesc());
                } else if (type == 2 && interfaceTypeEnum.getCode() == 2) {
                    intervalInterfaceReport.setOperateType(interfaceTypeEnum.getCode());
                    intervalInterfaceReport.setInterfaceName(interfaceTypeEnum.getDesc());
                }

            }

            for (OperatorTypeEnum operatorTypeEnum:OperatorTypeEnum.values()) {
                intervalInterfaceReport.setOperateType(operatorTypeEnum.getCode());
                if (type == 1) { // 注册
                    if(operatorTypeEnum.getCode() == 1) {//请求数
                        intervalInterfaceReport.setOperateCount(invokeMap.get("invokeCount") == null ?0:Integer.valueOf(invokeMap.get("invokeCount")+""));
                    } else { // 完成数
                        intervalInterfaceReport.setOperateCount(userCount);
                    }
                } else if (type == 2) { //充值
                    if(operatorTypeEnum.getCode() == 1) {//请求数
                        intervalInterfaceReport.setOperateCount(invokeMap.get("invokeCount") == null ?0:Integer.valueOf(invokeMap.get("invokeCount")+""));
                    } else { // 完成数
                        intervalInterfaceReport.setOperateCount(rechargeCount);
                    }
                }
                intervalInterfaceReport.setStatisticsTime(DateUtil.getFirstThirtyMinStr(now));
                intervalInterfaceReport.setIntervalTime(INTERVALTIME);
                intervalInterfaceReport.setExecuteTime(now);
                intervalInterfaceReportMapper.insert(intervalInterfaceReport);
                list.add(intervalInterfaceReport);
            }
        }
        return Result.success(list);
 }



    public Result intervalGameLaunch() throws Exception{
        // 游戏启动数据
        List<Map<String,Object>>  platformAllGameStartCount = dataGameStartMapper.selectPlatformAllGameStartCount();
        Date now = new Date();
        String startDate = DateUtil.getFirstThirtyMinStr(now);
        List<IntervalGameLaunchReport> list = new ArrayList();
        for (Map<String,Object> gameStartMap:platformAllGameStartCount) {
            IntervalGameLaunchReport intervalGameLaunchReport = new IntervalGameLaunchReport();
            intervalGameLaunchReport.setStatisticsTime(startDate);
            intervalGameLaunchReport.setIntervalTime(INTERVALTIME);
            intervalGameLaunchReport.setPlatformId(gameStartMap.get("platformId")== null ?0:Integer.valueOf(gameStartMap.get("platformId")+""));
            intervalGameLaunchReport.setPlatformName(gameStartMap.get("platformName") == null ?"" :gameStartMap.get("platformName")+"");
            intervalGameLaunchReport.setLaunchCount(gameStartMap.get("gameStartCount")==null?0:Integer.valueOf(gameStartMap.get("gameStartCount")+""));
            intervalGameLaunchReport.setExecuteTime(now);
            // 入库接口统计结果表
            intervalGameLaunchReportMapper.insert(intervalGameLaunchReport);
            list.add(intervalGameLaunchReport);
        }

        return Result.success(list);
    }
}
