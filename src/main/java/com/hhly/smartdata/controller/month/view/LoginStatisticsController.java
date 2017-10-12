package com.hhly.smartdata.controller.month.view;

import com.hhly.smartdata.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@Controller
@RequestMapping("/month/login/statistics")
public class LoginStatisticsController extends BaseController{

    @RequestMapping("/show")
    public String show(){
        return "month/login_statistics";
    }
}
