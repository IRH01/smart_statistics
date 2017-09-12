package com.hhly.smartdata.service.ybf.impl;

import com.hhly.smartdata.mapper.ybf.DimInfoRepository;
import com.hhly.smartdata.model.ybf.DimInfoCount;
import com.hhly.smartdata.service.ybf.DimInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DimInfoServiceImpl implements DimInfoService{
    @Autowired
    DimInfoRepository dimInfoRepository;

    @Override
    public Map<String, Object> count(Date startDate, Date endDate, String domainId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        double totalCount = dimInfoRepository.countAllByDate(startDate, endDate, domainId);
        List<DimInfoCount> typeTotalCount = dimInfoRepository.countTypeByDate(startDate, endDate, domainId);
        if(!CollectionUtils.isEmpty(typeTotalCount)){
            DimInfoCount item = null;
            int typeCount = 0;
            double percent = 0;
            for(int i = 0; i < typeTotalCount.size(); i++){
                item = typeTotalCount.get(i);
                typeCount = item.getCount();
                //获取百分比
                if(0 == totalCount){
                    percent = 0;
                }else{
                    percent = (double) typeCount / totalCount;
                }
                item.setPercent(percent);
            }
        }
        //数据
        resultMap.put("data", typeTotalCount);
        return resultMap;
    }

}
