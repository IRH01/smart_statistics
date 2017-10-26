package com.hhly.smartdata.controller.remitreport.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/10/23.
 */
@Controller
@RequestMapping("/remit/report/game/remit")
public class GameRemitListController {

    @RequestMapping(value = "/show")
    public String show() {
        return "remit_report/game_remit_list";
    }

}
