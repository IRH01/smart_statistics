package com.hhly.smartdata.service.schedule;

import com.alibaba.fastjson.JSONObject;
import com.hhly.smartdata.common.BaseTest;
import com.hhly.smartdata.util.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * DailyExecutorService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>九月 26, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
public class DailyExecutorServiceTest extends BaseTest{
    @Autowired
    private DailyExecutorService dailyExecutorService;

    /**
     * Method: compositeReport()
     */
    @Test
    public void testCompositeReport() throws Exception{
        Result result = this.dailyExecutorService.compositeReport(new Date());
        System.err.println(JSONObject.toJSONString(result.getData()));
    }

    /**
     * Method: rechargeStatistic()
     */
    @Test
    public void testRechargeStatistic() throws Exception{
        this.dailyExecutorService.rechargeStatistic(new Date());
    }

    /**
     * Method: loginStatistic()
     */
    @Test
    public void testLoginStatistic() throws Exception{
        this.dailyExecutorService.loginStatistic(new Date());
    }

    /**
     * Method: registerStatistic()
     */
    @Test
    public void testRegisterStatistic() throws Exception{
        this.dailyExecutorService.registerStatistic(new Date());
    }

    /**
     * Method: keepRecordAnalyzeReport()
     */
    @Test
    public void testKeepRecordAnalyzeReport() throws Exception{
        this.dailyExecutorService.keepRecordAnalyzeReport(new Date());
    }
}
