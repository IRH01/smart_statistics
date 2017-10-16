package com.hhly.smartdata.controller.daily;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.enume.PlatformIdEnum;
import com.hhly.smartdata.service.smartdata.DailyCompositeService;
import com.hhly.smartdata.service.smartdata.DailyRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 *  平台日综合报表
 */
@Controller
@RequestMapping(value="/daily/dailyComposite")
public class DailyCompositeController extends BaseController{

    @Autowired
    private DailyCompositeService dailyCompositeService;


    @RequestMapping(value="/show")
    public ModelAndView show(){
        Map<String, Object> model = Maps.newHashMap();
        model.put("platformTypes", PlatformIdEnum.values());
        return new ModelAndView("daily/composite", model);
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public String getList(String startDate,String endDate,int pageNumber, int pageSize){
        String result ="";
        try{
            result  = dailyCompositeService.selectDailyCompositeListData(startDate,endDate,pageNumber,pageSize).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取平台日综合报表数据异常");
        }
        return result;
    }


}
