package com.hhly.smartdata.controller.gameoperation.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/10/23.
 */
@Controller
@RequestMapping("/game/operation/nation/member/recharge/summary")
public class NationMemberRechargeSummaryController {

    @RequestMapping(value = "/show")
    public String show() {
        return "game_operation/nation_member_recharge_summary";
    }

}
