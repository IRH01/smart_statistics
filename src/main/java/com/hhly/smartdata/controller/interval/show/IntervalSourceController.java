package com.hhly.smartdata.controller.interval.show;

import com.hhly.smartdata.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 各端实时数据
 */
@Controller
@RequestMapping(value = "/interval/source")
public class IntervalSourceController extends BaseController{

    @RequestMapping(value = "/show")
    public String show(){
        return "interval/source_statistics";
    }

}
