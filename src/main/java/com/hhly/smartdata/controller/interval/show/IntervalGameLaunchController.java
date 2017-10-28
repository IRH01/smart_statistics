package com.hhly.smartdata.controller.interval.show;

import com.hhly.smartdata.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 游戏启动统计
 */
@Controller
@RequestMapping(value = "/interval/game/launch")
public class IntervalGameLaunchController extends BaseController{

    @RequestMapping(value = "/show")
    public String show(){
        return "interval/game_launch";
    }

}
