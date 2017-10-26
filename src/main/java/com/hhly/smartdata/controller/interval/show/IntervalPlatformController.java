package com.hhly.smartdata.controller.interval.show;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.service.smartdata.IntervalSourceService;
import com.hhly.smartdata.util.HourListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import java.util.TreeSet;

/**
 *  平台数据实时统计
 */
@Controller
@RequestMapping(value="/interval/platform")
public class IntervalPlatformController extends BaseController{

    @RequestMapping(value="/show")
    public ModelAndView show(){
        Map<String, Object> model = Maps.newHashMap();
        model.put("deviceTypes", SourceTypeEnum.values());
        return new ModelAndView("interval/platform_statistics", model);
    }

}
