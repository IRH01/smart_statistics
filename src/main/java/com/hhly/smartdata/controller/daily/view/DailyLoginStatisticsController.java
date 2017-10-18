package com.hhly.smartdata.controller.daily.view;

import com.hhly.smartdata.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@Controller
@RequestMapping("/daily/login/statistics")
public class DailyLoginStatisticsController extends BaseController{

    @RequestMapping("/show")
    public String show(){
        return "daily/login_statistics";
    }
}
