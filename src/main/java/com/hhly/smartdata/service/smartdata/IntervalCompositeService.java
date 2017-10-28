package com.hhly.smartdata.service.smartdata;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.smartdata.IntervalSourceReportMapper;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
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
public class IntervalCompositeService{


    @Autowired
    private IntervalSourceReportMapper intervalSourceReportMapper;

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
        return intervalSourceReportMapper.selectIntervalSourceTotalData(startDate, endDate);
    }

    public PageInfo<IntervalSourceReport> selectIntervalSourceListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        // 列表数据
        PageHelper.startPage(pageNumber, pageSize);
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalSourceListData(startDate, endDate);
        PageInfo<IntervalSourceReport> pageInfo = new PageInfo<>(values);
        return pageInfo;
    }

    public  Map<String, Object> selectIntervalSourceChartData(String startDate, String endDate, String deviceTypesStr, TreeSet<String> scales) throws Exception{
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
        String[] deviceTypes = new String[]{""};
        if(!StringUtils.isEmpty(deviceTypesStr)){
            deviceTypes = deviceTypesStr.split(",");
        }
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalSourceChartData(startDate, endDate, deviceTypes);

        while(iterator.hasNext()){

            long registerPopulation = 0;
            long loginPopulation = 0;
            long rechargePopulation = 0;
            long rechargeCount = 0;
            BigDecimal rechargeAmount = new BigDecimal(0);

            String currentScale = iterator.next();
            for(IntervalSourceReport value : values){

                // 匹配时间
                if(currentScale.substring(0, 5).equals(value.getStatisticsTime())){

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
