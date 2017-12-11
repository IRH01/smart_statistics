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
//        this.dailyExecutorService.compositeReport(new Date());
//        String[] yue = {"10-21", "10-22", "10-23", "10-24", "10-25", "10-26", "10-27", "10-28", "10-29", "10-30", "10-31",
//                "11-01", "11-02", "11-03", "11-04", "11-05", "11-06", "11-07", "11-08", "11-09", "11-10", "11-11", "11-12", "11-13", "11-14", "11-15",
//                "11-16", "11-17", "11-18", "11-19", "11-20", "11-21", "11-22", "11-23", "11-24", "11-25", "11-26", "11-27", "11-28", "11-29", "11-30",
//                "12-01", "12-02", "12-03", "12-04", "12-05", "12-06", "12-07"};
        String[] yue = {"12-08"};
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
