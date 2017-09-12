package com.hhly.smartdata.service.ybf.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.dto.YbfWebStatH;
import com.hhly.smartdata.mapper.ybf.YbfWebStatHRepository;
import com.hhly.smartdata.service.ybf.YbfWebStatHService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class YbfWebStatHServiceImpl implements YbfWebStatHService{

    @Autowired
    private YbfWebStatHRepository ybfWebStatHRepository;

    @Override
    public JSONObject getYbfWebStatHList(String domain, String date, int pageNumber, int pageSize){

        Map<String, String> param = new HashMap<String, String>();
        param.put("domain", domain);
        param.put("date", date);

        PageHelper.startPage(pageNumber, pageSize);
        List<YbfWebStatH> ybfWebStatHs = this.ybfWebStatHRepository.findYbfWebStatH(param);
        PageInfo<YbfWebStatH> pageInfo = new PageInfo<YbfWebStatH>(ybfWebStatHs);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("ybfWebStatDs", pageInfo);
        resultMap.put("pageNumber", pageNumber);

        return JSONObject.fromObject(pageInfo);
    }

    @Override
    public JSONObject getYbfWebStatHChart(String date, String urlId, Set<String> scales){


        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, String> param = new HashMap<String, String>();
        param.put("date", date);
        param.put("urlId", urlId);

        List<YbfWebStatH> ybfWebStatHs = this.ybfWebStatHRepository.findYbfWebStatH(param);


        List<String> scaleList = new LinkedList<String>();
        // 停留时长
        List<Long> stayList = new LinkedList<Long>();
        // 点击次数
        List<Long> clickList = new LinkedList<Long>();
        // ip
        List<Long> ipList = new LinkedList<Long>();

        List<Long> viewList = new LinkedList<Long>();

        List<Long> userList = new LinkedList<Long>();

        Iterator<String> iterator = scales.iterator();

        while(iterator.hasNext()){

            long ipPerScale = 0;
            long stayPerScale = 0;
            long clickPerScale = 0;
            long userPerScale = 0;
            long viewScale = 0;

            String currentScale = iterator.next();
            for(YbfWebStatH ybfWebStatH : ybfWebStatHs){

                if(currentScale.substring(0, 2).equals(ybfWebStatH.getEtlDate())){

                    ipPerScale = ybfWebStatH.getIpCnt();
                    stayPerScale = ybfWebStatH.getStayCnt();
                    clickPerScale = ybfWebStatH.getClickCnt();
                    userPerScale = ybfWebStatH.getUserCnt();
                    viewScale = ybfWebStatH.getViewCnt();
                }
            }

            ipList.add(ipPerScale);
            stayList.add(stayPerScale);
            clickList.add(clickPerScale);
            userList.add(userPerScale);
            viewList.add(viewScale);
            scaleList.add(currentScale);
        }

        result.put("scales", scaleList);
        result.put("ipList", ipList);
        result.put("clickList", clickList);
        result.put("stayList", stayList);
        result.put("userList", userList);
        result.put("viewList", viewList);

        return JSONObject.fromObject(result);

    }

}
