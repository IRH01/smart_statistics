package com.hhly.smartdata.service.game.impl;

import com.hhly.smartdata.mapper.game.GameTransHReposity;
import com.hhly.smartdata.model.game.GameTransHourly;
import com.hhly.smartdata.service.game.GameTransHourlyService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameTransHourlyServiceImpl implements GameTransHourlyService{

    @Autowired
    private GameTransHReposity gameTransHReposity;

    @Override
    public JSONObject getChart(String platformId, String date, Set<String> scales){
        Map<String, Object> result = new HashMap<String, Object>();
        List<String> scaleList = new LinkedList<String>();
        //注册玩家充值次数
        List<Long> regAndRechargeTimesList = new LinkedList<Long>();
        //注册玩家充值人数
        List<Long> regAndRechargeCountList = new LinkedList<Long>();
        //注册玩家充值金额
        List<Long> regAndRechargeAmountList = new LinkedList<Long>();

        //活跃玩家充值次数
        List<Long> actAndRechargeTimesList = new LinkedList<Long>();
        //活跃玩家充值人数
        List<Long> actAndRechargeCountList = new LinkedList<Long>();
        //活跃玩家充值金额
        List<Long> actAndRechargeAmountList = new LinkedList<Long>();

        Iterator<String> iterator = scales.iterator();

        //查询获取数据
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("platformId", platformId);
        conditions.put("date", date);
        List<GameTransHourly> values = gameTransHReposity.statistics(conditions);

        while(iterator.hasNext()){

            long regAndRechargeTimesScale = 0;
            long regAndRechargeCountScale = 0;
            long regAndRechargeAmountScale = 0;
            long actAndRechargeTimesScale = 0;
            long actAndRechargeCountScale = 0;
            long actAndRechargeAmountScale = 0;

            String currentScale = iterator.next();
            for(GameTransHourly value : values){
                if(currentScale.substring(0, 2).equals(value.getStatHour())){
                    regAndRechargeTimesScale = value.getRegAndRechargeTimes();
                    regAndRechargeCountScale = value.getRegAndRechargeCount();
                    regAndRechargeAmountScale = value.getRegAndRechargeAmount();

                    actAndRechargeTimesScale = value.getActAndRechargeTimes();
                    actAndRechargeCountScale = value.getActAndRechargeCount();
                    actAndRechargeAmountScale = value.getActAndRechargeAmount();
                }
            }
            regAndRechargeTimesList.add(regAndRechargeTimesScale);
            regAndRechargeCountList.add(regAndRechargeCountScale);
            regAndRechargeAmountList.add(regAndRechargeAmountScale);

            actAndRechargeTimesList.add(actAndRechargeTimesScale);
            actAndRechargeCountList.add(actAndRechargeCountScale);
            actAndRechargeAmountList.add(actAndRechargeAmountScale);

            scaleList.add(currentScale);
        }

        result.put("scales", scaleList);
        result.put("regAndRechargeTimesList", regAndRechargeTimesList);
        result.put("regAndRechargeCountList", regAndRechargeCountList);
        result.put("regAndRechargeAmountList", regAndRechargeAmountList);

        result.put("actAndRechargeTimesList", actAndRechargeTimesList);
        result.put("actAndRechargeCountList", actAndRechargeCountList);
        result.put("actAndRechargeAmountList", actAndRechargeAmountList);

        return JSONObject.fromObject(result);
    }

}
