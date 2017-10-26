package com.hhly.smartdata.controller.daily.view;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.enume.PlatformIdEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 注册来源统计
 */
@Controller
@RequestMapping(value = "/daily/register")
public class DailyRegisterController extends BaseController {

    @RequestMapping(value = "/show")
    public ModelAndView show() {
        Map<String, Object> model = Maps.newHashMap();
        model.put("platformTypes", PlatformIdEnum.values());
        return new ModelAndView("daily/register_statistics", model);
    }

}
