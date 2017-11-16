package com.hhly.smartdata.controller.gameoperation.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/10/23.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/game/operation/channel/operate/report")
public class ChannelOperateReportController{

    @RequestMapping(value = "/show")
    public String show(){
        return "game_operation/channel_operate_report";
    }

}
