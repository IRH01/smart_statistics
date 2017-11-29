package com.hhly.smartdata.service.interval;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hhly.smartdata.mapper.member.LoginTrackMapper;
import com.hhly.smartdata.mapper.member.RechargeRecordMapper;
import com.hhly.smartdata.mapper.member.UserInfoMapper;
import com.hhly.smartdata.mapper.smartdata.IntervalSourceReportMapper;
import com.hhly.smartdata.mapper.source.DataGameStartMapper;
import com.hhly.smartdata.mapper.source.DataViewMapper;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import com.hhly.smartdata.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Service
public class IntervalCompositeService{


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

    @Autowired
    private UserInfoMapper userInfoMapper;
    /***
     *  实时数据各指标总数
     * @param startDate, endDate
     *
     * @return Map
     *
     * @throws Exception
     */
    public Map<String, Object> selectIntervalSourceTotalData(String startDate, String endDate) throws Exception{
        // 注册人数 登录人数 充值人数 充值次数 充值金额
        Date now = new Date();
        String startTime = (StringUtils.isEmpty(startDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 00:00" : startDate) + ":00";
        String endTime = (StringUtils.isEmpty(endDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 24:00" : endDate) + ":00";

        // 登录人数
        List<Map<String, Object>> loginUser = loginTrackMapper.selectLoginUserGroupByUser(DateUtil.string2Date(startTime),DateUtil.string2Date(endTime));

        //启动表
        List<Map<String, Object>> firstThirtyMinGameStartCount = dataGameStartMapper.selectTodayGameStartCountGroupByUser(DateUtil.string2Date(startTime),DateUtil.string2Date(endTime));

        // uv
        List<Map<String, Object>> firstThirtyMinUserViewAndPageView = dataViewMapper.selectTodayGameStartCountGroupByUser(DateUtil.string2Date(startTime),DateUtil.string2Date(endTime));

        // 充值人数
        List<Map<String, Object>> rechargeRecord = rechargeRecordMapper.selectRechargeUserGroupByUser(DateUtil.string2Date(startTime),DateUtil.string2Date(endTime));

        // 注册人数
        List<Map<String, Object>> firstThirtyMinRegister = userInfoMapper.selectRegisterUserByStartTimeAndEndTime(DateUtil.string2Date(startTime),DateUtil.string2Date(endTime));

        Set<String> intervalSourceTotalDataSet = Sets.newHashSet();
        for(Map<String, Object> map : loginUser){
            intervalSourceTotalDataSet.add((String) map.get("userId"));
        }
        for(Map<String, Object> map : firstThirtyMinGameStartCount){
            intervalSourceTotalDataSet.add((String) map.get("userId"));
        }
        for(Map<String, Object> map : firstThirtyMinUserViewAndPageView){
            intervalSourceTotalDataSet.add((String) map.get("userId"));
        }
        Integer rechargeCount = 0;
        BigDecimal rechargeAmount = new BigDecimal(0.00);
        for(Map<String, Object> map : rechargeRecord){
            rechargeCount = rechargeCount + (map.get("orderCount") == null ?0:Integer.valueOf(map.get("orderCount").toString()));
            rechargeAmount = rechargeAmount.add((BigDecimal) map.get("applyAmountSum"));

        }

        Map<String,Object>  map = new HashMap<String,Object>();
        map.put("registerPopulation",firstThirtyMinRegister.size());
        map.put("loginPopulation",intervalSourceTotalDataSet.size());
        map.put("rechargePopulation",rechargeRecord.size());
        map.put("rechargeCount",rechargeCount);
        map.put("rechargeAmount",rechargeAmount);
        return map;
    }

    public PageInfo<IntervalSourceReport> selectIntervalSourceListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        // 列表数据
        Date now = new Date();
        String startTime = (StringUtils.isEmpty(startDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 00:00" : startDate) + ":00";
        String endTime = (StringUtils.isEmpty(endDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 24:00" : endDate) + ":00";
        PageHelper.startPage(pageNumber, pageSize);
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalSourceListData(startTime, endTime);
        return new PageInfo<>(values);
    }

    public Map<String, Object> selectIntervalSourceChartData(String startDate, String endDate, String sourceType, TreeSet<String> scales) throws Exception{
        Date now = new Date();
        // 曲线图数据
        Map<String, Object> result = Maps.newHashMap();
        List<String> scaleList = Lists.newArrayList();
        // 注册人数
        List<Long> registerPopulationList = Lists.newArrayList();
        //登录人数
        List<Long> loginCountList = Lists.newArrayList();
        //充值人数
        List<Long> rechargePopulationList = Lists.newArrayList();
        //充值次数
        List<Long> rechargeCountList = Lists.newArrayList();
        //充值金额
        List<BigDecimal> rechargeAmountList = Lists.newArrayList();

        //查询获取数据
        String startTime = (StringUtils.isEmpty(startDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 00:00" : startDate) + ":00";
        String endTime = (StringUtils.isEmpty(endDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 24:00" : endDate) + ":00";
        List<HashMap<String, Object>> values = intervalSourceReportMapper.selectIntervalSourceChartData(startTime, endTime, sourceType);

        Iterator<String> iterator = scales.iterator();
        while(iterator.hasNext()){
            long registerPopulation = 0;
            long loginPopulation = 0;
            long rechargePopulation = 0;
            long rechargeCount = 0;
            BigDecimal rechargeAmount = new BigDecimal(0.00);

            String currentScale = iterator.next();
            for(HashMap<String, Object> item : values){
                // 匹配时间
                if(currentScale.substring(0, 5).trim().equals(((String) item.get("statistics_time")).substring(10, 16).trim())){
                    registerPopulation = ((BigDecimal) item.get("register_population_sum")).longValue();
                    loginPopulation = ((BigDecimal) item.get("login_population_sum")).longValue();
                    rechargePopulation = ((BigDecimal) item.get("recharge_population_sum")).longValue();
                    rechargeCount = ((BigDecimal) item.get("recharge_count_sum")).longValue();
                    rechargeAmount = (BigDecimal) item.get("recharge_amount_sum");
                    break;
                }
            }
            registerPopulationList.add(registerPopulation);
            loginCountList.add(loginPopulation);
            rechargePopulationList.add(rechargePopulation);
            rechargeCountList.add(rechargeCount);
            rechargeAmountList.add(rechargeAmount);
            scaleList.add(currentScale);
        }

        result.put("scales", scaleList);
        result.put("registerPopulationList", registerPopulationList);
        result.put("loginCountList", loginCountList);
        result.put("rechargePopulationList", rechargePopulationList);
        result.put("rechargeCountList", rechargeCountList);
        result.put("rechargeAmountList", rechargeAmountList);

        return result;
    }

}
