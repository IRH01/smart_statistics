package com.hhly.smartdata.controller.gameoperation.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/10/23.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/game/operation/lawyer/user/channel/consume")
public class LawyerUserChannelConsumeController{

    @RequestMapping(value = "/show")
    public String show(){
        return "game_operation/lawyer_user_channel_consume";
    }

}
