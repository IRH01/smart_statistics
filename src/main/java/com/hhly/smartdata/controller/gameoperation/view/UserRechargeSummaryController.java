package com.hhly.smartdata.controller.gameoperation.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/10/23.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/game/operation/user/recharge/summary")
public class UserRechargeSummaryController{

    @RequestMapping(value = "/show")
    public String show(){
        return "game_operation/user_recharge_summary";
    }

}
