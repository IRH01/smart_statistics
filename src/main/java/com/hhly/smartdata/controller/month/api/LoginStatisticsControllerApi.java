package com.hhly.smartdata.controller.month.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.mouth.TimeFilter;
import com.hhly.smartdata.service.month.LoginStatisticsService;
import com.hhly.smartdata.util.Result;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@RestController
@RequestMapping("/month/login/statistics")
public class LoginStatisticsControllerApi extends BaseController{

    @Autowired
    private LoginStatisticsService loginStatisticsService;

    @RequestMapping("list")
    public Result search(TimeFilter filter){
        Pagination pagination = null;
        try{
            pagination = this.loginStatisticsService.search(filter);
        }catch(Exception e){
            LOGGER.error("查询月登录报表报错：" + e.getMessage());
        }
        return Result.success(pagination);
    }
}
