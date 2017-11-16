package com.hhly.smartdata.controller.month.view;

import com.hhly.smartdata.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/month/register/statistics")
public class MonthRegisterStatisticsController extends BaseController{

    @RequestMapping("/show")
    public String show(){
        return "month/register_statistics";
    }
}
