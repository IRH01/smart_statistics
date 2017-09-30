package com.hhly.smartdata.service.smartdata;

import com.hhly.smartdata.mapper.smartdata.DailyRegisterReportMapper;
import com.hhly.smartdata.model.smartdata.DailyRegisterReport;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DailyRegisterService{

    @Autowired
    private DailyRegisterReportMapper dailyRegisterReportMapper;



    public Result  selectDailyRegisterData(Map<String,Object> map) throws Exception{
       //查询注册来源统计列表数据
        List<DailyRegisterReport> dailyRegisterReportList =  dailyRegisterReportMapper.selectRegisterDataListByTime(map);

        // 查询前一日新增用户数
        DailyRegisterReport dailyRegisterReport = dailyRegisterReportMapper.selectYesterdayRegisterData();

        Integer count = 0;
        if (null != dailyRegisterReport) {
            count = dailyRegisterReport.getPcPopulation()+dailyRegisterReport.getH5Population()+dailyRegisterReport.getAndroidPopulation()
                    +dailyRegisterReport.getIosPopulation();
        }
        // 返回
        Map<String,Object> resultMap = new HashMap();
        map.put("dailyRegisterReportList",dailyRegisterReportList);
        map.put("count",count);

        return Result.success(resultMap);
    }


}
