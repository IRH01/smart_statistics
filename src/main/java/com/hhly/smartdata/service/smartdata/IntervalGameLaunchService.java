package com.hhly.smartdata.service.smartdata;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.dto.enume.PlatformIdEnum;
import com.hhly.smartdata.mapper.smartdata.IntervalGameLaunchReportMapper;
import com.hhly.smartdata.model.smartdata.IntervalGameLaunchListReport;
import com.hhly.smartdata.model.smartdata.IntervalGameLaunchReport;
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
public class IntervalGameLaunchService{

    @Autowired
    private IntervalGameLaunchReportMapper intervalGameLaunchReportMapper;


    public JSONObject selectIntervalGameLaunchListData(String startDate, String endDate,int pageNumber,int pageSize) throws Exception{
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("startDate", startDate);
        condition.put("endDate", endDate);
        PageHelper.startPage(pageNumber, pageSize);
        List<IntervalGameLaunchListReport> values = intervalGameLaunchReportMapper.selectIntervalGameLaunchListData(condition);
        PageInfo<IntervalGameLaunchListReport> pageInfo = new PageInfo<IntervalGameLaunchListReport>(values);
        return JSONObject.fromObject(pageInfo);
    }

    public JSONObject selectIntervalGameLaunchChartData(String startDate, String endDate,String platformTypes,TreeSet<String> scales) throws Exception{
        // 曲线图数据
        Map<String, Object> result = new HashMap<String, Object>();
        List<String> scaleList = new LinkedList<String>();
        // 一比分
        List<Long> ybfList = new LinkedList<Long>();
        // 乐盈电竞
        List<Long> yydjList = new LinkedList<Long>();
        // 撩妹德州
        List<Long> lmdzList = new LinkedList<Long>();

        Iterator<String> iterator = scales.iterator();

        //查询获取数据
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("startDate", startDate);
        conditions.put("endDate", endDate);
        List<IntervalGameLaunchReport> values = intervalGameLaunchReportMapper.selectIntervalGameLaunchChatData(conditions);

        while (iterator.hasNext()) {

            long ybfNum = 0;
            long yydjNum = 0;
            long lmdzNum = 0;

            String currentScale = iterator.next();
            for (IntervalGameLaunchReport value : values) {
                // 匹配时间
                if (currentScale.substring(0, 5).equals(value.getStatisticsTime())) {
                    if ("撩妹德州".equals(value.getPlatformName())) {
                        lmdzNum = value.getLaunchCount();
                    } else if ("乐盈电竞".equals(value.getPlatformName())) {
                        yydjNum = value.getLaunchCount();
                    } else if ("一比分".equals(value.getPlatformName())) {
                        ybfNum = value.getLaunchCount();
                    }

                }
            }
            ybfList.add(ybfNum);
            yydjList.add(yydjNum);
            lmdzList.add(lmdzNum);
            scaleList.add(currentScale);
        }

        result.put("scales", scaleList);
        result.put("ybfList", ybfList);
        result.put("yydjList", yydjList);
        result.put("lmdzList", lmdzList);

        return JSONObject.fromObject(result);
    }

}
