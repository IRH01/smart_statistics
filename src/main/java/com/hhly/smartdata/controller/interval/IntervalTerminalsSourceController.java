package com.hhly.smartdata.controller.interval;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.service.smartdata.IntervalSourceService;
import com.hhly.smartdata.service.smartdata.IntervalTerminalsSourceService;
import com.hhly.smartdata.util.HourListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.TreeSet;

/**
 *  各端实时数据
 */
@Controller
@RequestMapping(value="/interval/realTimeTerminalsInterval")
public class IntervalTerminalsSourceController extends BaseController{

    @Autowired
    private IntervalTerminalsSourceService intervalTerminalsSourceService;


    @RequestMapping(value="/show")
    public ModelAndView show(){
        Map<String, Object> model = Maps.newHashMap();
        model.put("deviceTypes", SourceTypeEnum.values());
        return new ModelAndView("interval/terminals_source_statistics", model);
    }


    @RequestMapping(value="/terminalsList")
    @ResponseBody
    public String getTerminalsList(String startDate,String endDate,int pageNumber, int pageSize){
        String result ="";
        try{
            result =  intervalTerminalsSourceService.selectIntervalTerminalsSourceListData(startDate,endDate,pageNumber,pageSize).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取各端数据异常");
        }
        return result;
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public String getList(String startDate,String endDate,int pageNumber, int pageSize,String deviceType){
        String result ="";
        try{
            result =  intervalTerminalsSourceService.selectIntervalTimeTerminalsSourceListData(startDate,endDate,pageNumber,pageSize,deviceType).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取分时段列表数据异常");
        }
        return result;
    }



}
