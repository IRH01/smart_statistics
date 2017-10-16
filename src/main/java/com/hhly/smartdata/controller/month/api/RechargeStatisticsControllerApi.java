package com.hhly.smartdata.controller.month.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.mouth.TimeFilter;
import com.hhly.smartdata.service.month.RechargeStatisticsService;
import com.hhly.smartdata.util.Result;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@RestController
@RequestMapping("/month/recharge/statistics")
public class RechargeStatisticsControllerApi extends BaseController{

    @Autowired
    private RechargeStatisticsService rechargeStatisticsService;

    @RequestMapping("list")
    public Result search(TimeFilter filter){
        Pagination pagination = null;
        try{
            pagination = this.rechargeStatisticsService.search(filter);
        }catch(Exception e){
            LOGGER.error("查询月充值报表报错：" + e.getMessage());
        }
        return Result.success(pagination);
    }

    @RequestMapping("last/recharge/total")
    public Result getLastTotalRecharge(){
        BigDecimal total = new BigDecimal(0.00);
        try{
            total = this.rechargeStatisticsService.getLastTotalRecharge();
        }catch(Exception e){
            LOGGER.error("获取最近一个月充值统计报错：" + e.getMessage());
        }
        return Result.success(total);
    }
}
