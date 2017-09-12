package com.hhly.smartdata.service.game.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.game.GameSummaryDReposity;
import com.hhly.smartdata.model.game.GameSummaryDaily;
import com.hhly.smartdata.service.game.GameSummaryDailyService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameSummaryDailyServiceImpl implements GameSummaryDailyService{
    @Autowired
    GameSummaryDReposity gameSummaryDReposity;

    public JSONObject find(String platformId, String startDate, String endDate, String deviceTypes, int pageNumber, int pageSize){
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("platformId", platformId);
        conditions.put("startDate", startDate);
        conditions.put("endDate", endDate);
        String[] deviceTypeIds = new String[]{""};
        if(!StringUtils.isEmpty(deviceTypes)){
            deviceTypeIds = deviceTypes.split(",");
        }
        conditions.put("deviceTypes", deviceTypeIds);
        PageHelper.startPage(pageNumber, pageSize);
        List<GameSummaryDaily> values = gameSummaryDReposity.find(conditions);
        PageInfo<GameSummaryDaily> pageInfo = new PageInfo<GameSummaryDaily>(values);
        return JSONObject.fromObject(pageInfo);
    }

    public JSONObject getChart(String platformId, String startDate, String endDate, String deviceTypes, Set<String> scales){

        Map<String, Object> result = new HashMap<String, Object>();
        List<String> scaleList = new LinkedList<String>();
        //注册玩家数
        List<Long> registerCountList = new LinkedList<Long>();
        // 活跃玩家数
        List<Long> activeCountList = new LinkedList<Long>();
        //付费次数
        List<Long> payTimesList = new LinkedList<Long>();
        //付费人数
        List<Long> payCountList = new LinkedList<Long>();
        //付费金额
        List<Long> payAmountList = new LinkedList<Long>();

        Iterator<String> iterator = scales.iterator();

        //查询获取数据
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("platformId", platformId);
        conditions.put("startDate", startDate);
        conditions.put("endDate", endDate);
        String[] deviceTypeIds = new String[]{""};
        if(!StringUtils.isEmpty(deviceTypes)){
            deviceTypeIds = deviceTypes.split(",");
        }
        conditions.put("deviceTypes", deviceTypeIds);
        List<GameSummaryDaily> values = gameSummaryDReposity.find(conditions);

        while(iterator.hasNext()){

            long registerCountScale = 0;
            long activeCountScale = 0;
            long payTimesScale = 0;
            long payCountScale = 0;
            long payAmountScale = 0;

            String currentScale = iterator.next();
            for(GameSummaryDaily value : values){
                if(currentScale.equals(value.getStatDate())){
                    registerCountScale = value.getRegisterCount();
                    activeCountScale = value.getActiveCount();
                    payTimesScale = value.getPayTimes();
                    payCountScale = value.getPayCount();
                    payAmountScale = value.getPayAmount();
                }
            }
            registerCountList.add(registerCountScale);
            activeCountList.add(activeCountScale);
            payTimesList.add(payTimesScale);
            payCountList.add(payCountScale);
            payAmountList.add(payAmountScale);
            scaleList.add(currentScale.substring(5));
        }

        result.put("scales", scaleList);
        result.put("registerCountList", registerCountList);
        result.put("activeCountList", activeCountList);
        result.put("payTimesList", payTimesList);
        result.put("payCountList", payCountList);
        result.put("payAmountList", payAmountList);

        return JSONObject.fromObject(result);

    }
}
