package com.hhly.smartdata.service.schedule;

import com.hhly.smartdata.common.BaseTest;
import com.hhly.smartdata.util.DateUtil;
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
        this.dailyExecutorService.compositeReport(new Date());
        String[] yue = {"11-22", "11-23", "11-24", "11-25", "11-26", "11-27"};
//        String[] yue = {"11-22"};
        for(String item : yue){
            this.dailyExecutorService.compositeReport(DateUtil.string2Date("2017-" + item + " 01:00:00"));
        }
    }

    /**
     * Method: rechargeStatistic()
     */
    @Test
    public void testRechargeStatistic() throws Exception{
        this.dailyExecutorService.rechargeStatistic(new Date());
        String[] yue = {"11-22", "11-23", "11-24", "11-25", "11-26", "11-27"};
        for(String item : yue){
            this.dailyExecutorService.rechargeStatistic(DateUtil.string2Date("2017-" + item + " 01:00:00"));
        }
    }

    /**
     * Method: loginStatistic()
     */
    @Test
    public void testLoginStatistic() throws Exception{
        this.dailyExecutorService.loginStatistic(new Date());
        String[] yue = {"11-22", "11-23", "11-24", "11-25", "11-26", "11-27"};
        for(String item : yue){
            this.dailyExecutorService.loginStatistic(DateUtil.string2Date("2017-" + item + " 01:00:00"));
        }
    }

    /**
     * Method: registerStatistic()
     */
    @Test
    public void testRegisterStatistic() throws Exception{
        this.dailyExecutorService.registerStatistic(new Date());
        String[] yue = {"11-22", "11-23", "11-24", "11-25", "11-26", "11-27"};
        for(String item : yue){
            this.dailyExecutorService.registerStatistic(DateUtil.string2Date("2017-" + item + " 01:00:00"));
        }
    }

    /**
     * Method: keepRecordAnalyzeReport()
     */
    @Test
    public void testKeepRecordAnalyzeReport() throws Exception{
        this.dailyExecutorService.keepRecordAnalyzeReport(new Date());
        String[] yue = {"11-22", "11-23", "11-24", "11-25", "11-26", "11-27"};
        for(String item : yue){
            this.dailyExecutorService.keepRecordAnalyzeReport(DateUtil.string2Date("2017-" + item + " 01:00:00"));
        }
    }
}
