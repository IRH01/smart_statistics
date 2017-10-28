package com.hhly.smartdata.controller.interval.show;

import com.hhly.smartdata.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 平台数据实时统计
 */
@Controller
@RequestMapping(value = "/interval/platform")
public class IntervalCompositeController extends BaseController{

    @RequestMapping(value = "/show")
    public String show(){
        return "interval/composite";
    }

}
