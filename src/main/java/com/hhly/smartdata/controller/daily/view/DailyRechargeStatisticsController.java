package com.hhly.smartdata.controller.daily.view;

import com.hhly.smartdata.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@Controller
@RequestMapping("/daily/recharge/statistics")
public class DailyRechargeStatisticsController extends BaseController{

    @RequestMapping("/show")
    public String show(){
        return "daily/recharge_statistics";
    }
}
