package com.hhly.smartdata.service.schedule;

import com.hhly.smartdata.mapper.member.LoginTrackMapper;
import com.hhly.smartdata.mapper.member.RechargeRecordMapper;
import com.hhly.smartdata.mapper.member.UserInfoMapper;
import com.hhly.smartdata.mapper.smartdata.IntervalInterfaceReportMapper;
import com.hhly.smartdata.mapper.smartdata.IntervalSourceReportMapper;
import com.hhly.smartdata.mapper.source.DataInterfaceInvokeMapper;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntervalExecutorService{

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

    public Result intervalSourceStatistics() throws Exception{
        IntervalSourceReport report = new IntervalSourceReport();
        String startDate = DateUtil.getYesterdayStr(new Date());

        /**
         *  各端 注册人数、充值人数、充值金额、充值次数
         *
         */
        List<IntervalSourceReport> sourceReport = loginTrackMapper.findIntervalSourceReportByTime(report);

        //  启动表
        List<IntervalSourceReport> dataPlatformReport = intervalSourceReportMapper.findDataPlatformStartBySourceTypeAndTime(report);

        // uv
        List<IntervalSourceReport> dataViewReport = intervalSourceReportMapper.findDataViewBySourceTypeAndTime(report);


        for(IntervalSourceReport reports : sourceReport){
            // 入库各端数据
            Byte sourceType = report.getSourceType();
            int loginPopulation = reports.getLoginPopulation();
            if(sourceType == 1 || sourceType == 4){
                for(IntervalSourceReport Report : dataPlatformReport){
                    if(Report.getSourceType() == 1 || Report.getSourceType() == 4){
                        reports.setLoginPopulation(loginPopulation + Report.getLoginPopulation());
                    }
                }
            }else if(sourceType == 2 || sourceType == 3){
                for(IntervalSourceReport intervalSourceReport : dataViewReport){
                    if(intervalSourceReport.getSourceType() == 1 || intervalSourceReport.getSourceType() == 4){
                        reports.setLoginPopulation(loginPopulation + intervalSourceReport.getLoginPopulation());
                    }
                }
            }
            intervalSourceReportMapper.insert(reports);

        }
        return null;
    }


    public Result intervalInterfaceStatistics() throws Exception{
        //        // 埋点接口数据
//        List<DataInterfaceInvoke> datalist = dataInterfaceInvokeMapper.findDataInfaceInvokeList();
//
//        // 用户注册数据
//        Integer  userCount = null;
//        try{
//            userCount = userInfoMapper.findUserInfoByTime();
//        }catch(Exception e){
//
//        }
//
//        // 充值数据
//        Integer rechargeCount = null;
//        try{
//            rechargeCount = rechargeRecordMapper.findRechargeRecordByTime();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        IntervalInterfaceReport intervalInterfaceReport = new IntervalInterfaceReport();
//        intervalInterfaceReport.setStatisticsTime(DateUtil.date2String(new Date(),"yyyy-MM-dd HH:mm"));
//        intervalInterfaceReport.setIntervalTime(30);
//        Integer[] interfaceStatus = new Integer[]{1,2};
//        for (DataInterfaceInvoke dataInterfaceInvoke:datalist) {
//            Integer type = dataInterfaceInvoke.getInterfaceType();
//            if (type == 1) {
//                intervalInterfaceReport.setOperateType(1);
//                intervalInterfaceReport.setInterfaceName("注册接口");
//
//            } else if (type == 2){
//                intervalInterfaceReport.setOperateType(2);
//                intervalInterfaceReport.setInterfaceName("充值接口");
//            }
//            for (Integer interStatus:interfaceStatus) {
//                intervalInterfaceReport.setOperateType(interStatus);
//                if (type == 1) { // 注册
//                    if(interStatus == 1) {//请求数
//                        intervalInterfaceReport.setOperateCount(dataInterfaceInvoke.getCountry());
//                    } else { // 完成数
//                        intervalInterfaceReport.setOperateCount(userCount);
//                    }
//                } else if (type == 2) { //充值
//                    if(interStatus == 1) {//请求数
//                        intervalInterfaceReport.setOperateCount(dataInterfaceInvoke.getCountry());
//                    } else { // 完成数
//                        intervalInterfaceReport.setOperateCount(rechargeCount);
//                    }
//                }
//                // 入库接口统计结果表
//                intervalInterfaceReportMapper.insert(intervalInterfaceReport);
//            }
//        }
        //  }

        return null;

    }


    public Result intervalGameLaunch() throws Exception{
        return null;
    }
}
