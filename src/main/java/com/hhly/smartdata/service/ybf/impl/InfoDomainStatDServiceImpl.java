package com.hhly.smartdata.service.ybf.impl;

import com.hhly.smartdata.mapper.ybf.InfoDomainStatDRepository;
import com.hhly.smartdata.model.ybf.InfoDomainStatD;
import com.hhly.smartdata.service.ybf.InfoDomainStatDService;
import com.hhly.smartdata.util.DateListUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class InfoDomainStatDServiceImpl implements InfoDomainStatDService{

    @Resource
    private InfoDomainStatDRepository infoDomainStatDRepository;

    public JSONObject countHistory(TreeSet<String> dates, String domain){

        Map<String, Object> resultMap = new TreeMap<String, Object>();

        // 时间范围
        Map<String, String> params = new HashMap<String, String>();
        params.put("startDate", dates.first());
        params.put("endDate", dates.last());
        params.put("domain", domain);

        // ip
        List<Long> ipList = new LinkedList<Long>();

        List<String> dateList = new LinkedList<String>();
        // 咨询
        List<Long> consultList = new LinkedList<Long>();
        // 停留时长
        List<Long> stayTimeList = new LinkedList<Long>();
        // 点击次数
        List<Long> clickList = new LinkedList<Long>();

        //PageHelper.startPage(1, 1);
        List<InfoDomainStatD> infoActStatDs = infoDomainStatDRepository.getInfoActStatD(params);

        Iterator<String> iterator = dates.iterator();
        while(iterator.hasNext()){

            String date = iterator.next();
            dateList.add(date.substring(5, 10));

            long ipPerDay = 0;
            long consultPerDay = 0;
            long stayTimePerDay = 0;
            long clickPerDay = 0;
            for(InfoDomainStatD infoActStatD : infoActStatDs){

                if(DateListUtil.getStringDate(infoActStatD.getEtlDate()).equals(date)){
                    ipPerDay = infoActStatD.getIpCnt();
                    consultPerDay = infoActStatD.getInfoCnt();
                    stayTimePerDay = infoActStatD.getStayCnt();
                    clickPerDay = infoActStatD.getClickCnt();
                }
            }
            ipList.add(ipPerDay);
            consultList.add(consultPerDay);
            stayTimeList.add(stayTimePerDay);
            clickList.add(clickPerDay);
            // 时间格式处理
        }

        // ip统计

        resultMap.put("dates", dateList);
        resultMap.put("ipList", ipList);
        resultMap.put("consultList", consultList);
        resultMap.put("stayTimeList", stayTimeList);
        resultMap.put("clickNumberList", clickList);
        resultMap.put("startDate", dates.first());
        resultMap.put("endDate", dates.last());

        return JSONObject.fromObject(resultMap);
    }
}
