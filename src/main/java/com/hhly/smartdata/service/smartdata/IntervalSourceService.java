package com.hhly.smartdata.service.smartdata;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.smartdata.IntervalSourceReportMapper;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import com.hhly.smartdata.util.Result;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Service
public class IntervalSourceService{


    @Autowired
    private IntervalSourceReportMapper intervalSourceReportMapper;

    /***
     *  实时数据各指标总数
     * @param startDate, endDate
     * @return
     * @throws Exception
     */
    public JSONObject selectIntervalSourceToltalData(String startDate,String endDate) throws Exception{
        // 注册人数 登录人数 充值人数 充值次数 充值金额
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        Map<String,Object> selectIntervalSourceToltalDataMap = intervalSourceReportMapper.selectIntervalSourceToltalData(paramMap);
        return JSONObject.fromObject(selectIntervalSourceToltalDataMap);
    }

    public JSONObject selectIntervalSourceListData(String startDate,String endDate,int pageNumber,int pageSize) throws Exception{
        // 列表数据
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("startDate", startDate);
        condition.put("endDate", endDate);
        PageHelper.startPage(pageNumber, pageSize);
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalSourceListData(condition);
        PageInfo<IntervalSourceReport> pageInfo = new PageInfo<IntervalSourceReport>(values);
        return JSONObject.fromObject(pageInfo);
    }

    public JSONObject selectIntervalSourceChartData(String startDate,String endDate,String deviceTypes,TreeSet<String> scales)throws Exception{
        // 曲线图数据
        Map<String, Object> result = new HashMap<String, Object>();
        List<String> scaleList = new LinkedList<String>();
        // 注册人数
        List<Long> registerPopulationList = new LinkedList<Long>();
        //登录人数
        List<Long> loginCountList = new LinkedList<Long>();
        //充值人数
        List<Long> rechargePopulationList = new LinkedList<Long>();
        //充值次数
        List<Long> rechargeCountList = new LinkedList<Long>();
        //充值金额
        List<BigDecimal> rechargeAmountList = new LinkedList<BigDecimal>();

        Iterator<String> iterator = scales.iterator();

        //查询获取数据
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("startDate", startDate);
        conditions.put("endDate", endDate);
        String[] deviceTypeIds = new String[]{""};
        if (!StringUtils.isEmpty(deviceTypes)) {
            deviceTypeIds = deviceTypes.split(",");
        }
        conditions.put("deviceTypes", deviceTypeIds);
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalSourceChartData(conditions);

        while (iterator.hasNext()) {

            long registerPopulation = 0;
            long loginPopulation = 0;
            long rechargePopulation = 0;
            long rechargeCount = 0;
            BigDecimal rechargeAmount = new BigDecimal(0);

            String currentScale = iterator.next();
            for (IntervalSourceReport value : values) {

                // 匹配时间
                if (currentScale.substring(0, 5).equals(value.getStatisticsTime())) {

                    registerPopulation = value.getRegisterPopulation();
                    loginPopulation = value.getLoginPopulation();
                    rechargePopulation = value.getRechargePopulation();
                    rechargeCount = value.getRechargeCount();
                   rechargeAmount = value.getRechargeAmount();
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

        return JSONObject.fromObject(result);
    }

}
