package com.hhly.smartdata.service.interval;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import com.hhly.smartdata.mapper.member.LoginTrackMapper;
import com.hhly.smartdata.mapper.member.RechargeRecordMapper;
import com.hhly.smartdata.mapper.smartdata.IntervalSourceReportMapper;
import com.hhly.smartdata.mapper.source.DataGameStartMapper;
import com.hhly.smartdata.mapper.source.DataViewMapper;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import com.hhly.smartdata.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class IntervalSourceService{


    @Autowired
    private IntervalSourceReportMapper intervalSourceReportMapper;

    @Autowired
    private LoginTrackMapper loginTrackMapper;

    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;

    @Autowired
    private DataGameStartMapper dataGameStartMapper;

    @Autowired
    private DataViewMapper dataViewMapper;

    public List<IntervalSourceReport> selectTotalDaySourceListData(String startDate, String endDate) throws Exception{
        // 注册人数 登录人数 充值人数 充值次数 充值金额
        Date now = new Date();
        String startTime = (StringUtils.isEmpty(startDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 00:00" : startDate) + ":00";
        String endTime = (StringUtils.isEmpty(endDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 24:00" : endDate) + ":00";

        // 登录人数
        List<Map<String, Object>> loginUser = loginTrackMapper.selectLoginUserGroupByUserAndOsType(DateUtil.string2Date(startTime),DateUtil.string2Date(endTime));

        //启动表
        List<Map<String, Object>> firstThirtyMinGameStartCount = dataGameStartMapper.selectTodayGameStartCountGroupByUserAndOsType(DateUtil.string2Date(startTime),DateUtil.string2Date(endTime));

        // uv
        List<Map<String, Object>> firstThirtyMinUserViewAndPageView = dataViewMapper.selectTodayGameStartCountGroupByUserAndOsType(DateUtil.string2Date(startTime),DateUtil.string2Date(endTime));

        // 充值人数
        List<Map<String, Object>> rechargeRecord = rechargeRecordMapper.selectRechargeUserGroupByUserAndOsType(DateUtil.string2Date(startTime),DateUtil.string2Date(endTime));

        Set<String> intervalSourceTotalDataSet = Sets.newHashSet();
        List<IntervalSourceReport> intervalTerminalsSourceListData = new ArrayList<IntervalSourceReport>();
        List<IntervalSourceReport> selectIntervalTerminalsSourceListData  = intervalSourceReportMapper.selectIntervalTerminalsSourceListData(startTime, endTime);
        for (IntervalSourceReport intervalSourceReport:selectIntervalTerminalsSourceListData) {
            intervalSourceTotalDataSet.clear();
            Integer count = 0;
            switch(intervalSourceReport.getSourceType().intValue()){
                case 1:
                    // pc
                    for(Map<String, Object> map : loginUser){
                        if("1".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            intervalSourceTotalDataSet.add(new String((byte[]) map.get("userId")));
                        }
                    }


                    for(Map<String, Object> map : firstThirtyMinUserViewAndPageView){
                        if("1".equals(map.get("osType") == null ? "" : map.get("osType").toString())){
                            intervalSourceTotalDataSet.add(new String((byte[]) map.get("userId")));
                        }
                    }

                    for(Map<String, Object> map : rechargeRecord){
                        if("1".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            count++;
                        }
                    }
                    intervalSourceReport.setLoginPopulation(intervalSourceTotalDataSet.size());
                    intervalSourceReport.setRechargePopulation(count);
                    break;
                case 2:
                    // android
                    for(Map<String, Object> map : loginUser){
                        if("2".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            intervalSourceTotalDataSet.add(new String((byte[]) map.get("userId")));
                        }
                    }

                    for(Map<String, Object> map : firstThirtyMinGameStartCount){
                        if("2".equals(map.get("osType") == null ? "" : map.get("osType").toString())){
                            intervalSourceTotalDataSet.add(new String((byte[]) map.get("userId")));
                        }
                    }

                    for(Map<String, Object> map : rechargeRecord){
                        if("2".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            count++;
                        }
                    }
                    intervalSourceReport.setLoginPopulation(intervalSourceTotalDataSet.size());
                    intervalSourceReport.setRechargePopulation(count);
                    break;
                case 3:
                    // ios;
                    for(Map<String, Object> map : loginUser){
                        if("3".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            intervalSourceTotalDataSet.add(new String((byte[]) map.get("userId")));
                        }
                    }

                    for(Map<String, Object> map : firstThirtyMinGameStartCount){
                        if("3".equals(map.get("osType") == null ? "" : map.get("osType").toString())){
                            intervalSourceTotalDataSet.add(new String((byte[]) map.get("userId")));
                        }
                    }

                    for(Map<String, Object> map : rechargeRecord){
                        if("3".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            count++;
                        }
                    }
                    intervalSourceReport.setLoginPopulation(intervalSourceTotalDataSet.size());
                    intervalSourceReport.setRechargePopulation(count);
                    break;
                case 4:
                    // h5
                    for(Map<String, Object> map : loginUser){
                        if("4".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            intervalSourceTotalDataSet.add(new String((byte[]) map.get("userId")));
                        }
                    }

                    for(Map<String, Object> map : firstThirtyMinUserViewAndPageView){
                        if("4".equals(map.get("osType") == null ? "" : map.get("osType").toString())){
                            intervalSourceTotalDataSet.add(new String((byte[]) map.get("userId")));
                        }
                    }
                    for(Map<String, Object> map : rechargeRecord){
                        if("4".equals(map.get("sourceType") == null ? "" : map.get("sourceType").toString())){
                            count++;
                        }
                    }
                    intervalSourceReport.setLoginPopulation(intervalSourceTotalDataSet.size());
                    intervalSourceReport.setRechargePopulation(count);
                    break;
            }
            intervalTerminalsSourceListData.add(intervalSourceReport);

        }

        return intervalTerminalsSourceListData;
    }

    public PageInfo<IntervalSourceReport> selectIntervalTimeTerminalsSourceListData(String startDate, String endDate, int pageNumber, int pageSize, String sourceType) throws Exception{
        Date now = new Date();
        // 列表数据
        PageHelper.startPage(pageNumber, pageSize);
        String startTime = (StringUtils.isEmpty(startDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 00:00" : startDate) + ":00";
        String endTime = (StringUtils.isEmpty(endDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 24:00" : endDate) + ":00";
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalTimeTerminalsSourceListData(startTime, endTime, sourceType);
        return new PageInfo<>(values);
    }

}
