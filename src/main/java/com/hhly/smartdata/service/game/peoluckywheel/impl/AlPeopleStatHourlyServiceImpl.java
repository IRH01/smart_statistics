package com.hhly.smartdata.service.game.peoluckywheel.impl;

import com.hhly.smartdata.mapper.game.peoluckywheel.AlPeopleStatHReposity;
import com.hhly.smartdata.model.game.peoluckywheel.AlPeopleStatH;
import com.hhly.smartdata.service.game.peoluckywheel.AlPeopleStatHourlyService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlPeopleStatHourlyServiceImpl implements AlPeopleStatHourlyService{
    @Autowired
    private AlPeopleStatHReposity AlPeopleStatHReposity;


    public JSONObject getChart(String domainId, String channelId, String gameId, String date, Set<String> scales){

        Map<String, Object> result = new HashMap<String, Object>();
        List<String> scaleList = new LinkedList<String>();
        //PV(浏览量)
        List<Long> pvCntList = new LinkedList<Long>();
        //UV(访客数量)
        List<Long> uvCntList = new LinkedList<Long>();
        //平均停留时长
        List<Double> avgStayCntList = new LinkedList<Double>();
        Iterator<String> iterator = scales.iterator();

        //查询获取数据
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("domainId", domainId);
        conditions.put("channelId", channelId);
        conditions.put("date", date);
        conditions.put("gameId", gameId);

        List<AlPeopleStatH> values = AlPeopleStatHReposity.find(conditions);

        while(iterator.hasNext()){
            long pvCntScale = 0;
            long uvCntScale = 0;
            double avgStayCntScale = 0;

            String currentScale = iterator.next();
            for(AlPeopleStatH value : values){
                if(currentScale.substring(0, 2).equals(value.getEtlDate())){
                    pvCntScale = value.getPvCnt();
                    uvCntScale = value.getUvCnt();
                    avgStayCntScale = value.getAvgStayCnt();
                }
            }
            pvCntList.add(pvCntScale);
            uvCntList.add(uvCntScale);
            avgStayCntList.add(avgStayCntScale);
            scaleList.add(currentScale);
        }

        result.put("scales", scaleList);
        result.put("pvCntList", pvCntList);
        result.put("uvCntList", uvCntList);
        result.put("avgStayCntList", avgStayCntList);

        return JSONObject.fromObject(result);

    }
}
