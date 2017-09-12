package com.hhly.smartdata.service.ybf.impl;

import com.hhly.smartdata.mapper.ybf.InfoTypeStatHRepository;
import com.hhly.smartdata.model.ybf.InfoTypeStatH;
import com.hhly.smartdata.service.ybf.InfoTypeStatHService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InfoTypeStatHServiceImpl implements InfoTypeStatHService{

    @Autowired
    private InfoTypeStatHRepository infoTypeStatHRepository;

    @Override
    public JSONObject getInfoTypeStatHCount(String domain, String date, String infoType, Set<String> scales){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");

        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, String> param = new HashMap<String, String>();
        param.put("domain", domain);
        param.put("date", date);
        param.put("infoType", infoType);

        List<InfoTypeStatH> infoTypeStatHs = this.infoTypeStatHRepository.getInfoTypeStatH(param);

        //ip
        List<Long> ipList = new LinkedList<Long>();

        List<String> scaleList = new LinkedList<String>();
        // 咨询
        // List<Long> consultList = new LinkedList<Long>();
        // 停留时长
        List<Long> stayTimeList = new LinkedList<Long>();
        // 点击次数
        List<Long> clickList = new LinkedList<Long>();
        Iterator<String> iterator = scales.iterator();
        while(iterator.hasNext()){

            long ipPerScale = 0;
            // long consultPerScale = 0;
            long stayTimePerScale = 0;
            long clickPerScale = 0;
            String currentScale = iterator.next();

            for(InfoTypeStatH infoTypeStatH : infoTypeStatHs){

                String hh = simpleDateFormat.format(infoTypeStatH.getEtlDate());
                if(currentScale.substring(0, 2).equals(hh)){
                    ipPerScale = infoTypeStatH.getIpCnt();
                    stayTimePerScale = infoTypeStatH.getStayCnt();
                    clickPerScale = infoTypeStatH.getClickCnt();
                }
            }

            ipList.add(ipPerScale);
            stayTimeList.add(stayTimePerScale);
            clickList.add(clickPerScale);
            scaleList.add(currentScale);
        }

        result.put("scales", scaleList);
        result.put("ipList", ipList);
        result.put("clickList", clickList);
        result.put("stayTimeList", stayTimeList);

        return JSONObject.fromObject(result);
    }

}
