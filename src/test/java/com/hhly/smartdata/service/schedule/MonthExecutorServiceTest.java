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
 * MonthExecutorService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>九月 26, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
public class MonthExecutorServiceTest extends BaseTest{
    @Autowired
    private MonthExecutorService monthExecutorService;

    /**
     * Method: compositeReport()
     */
    @Test
    public void testCompositeReport() throws Exception{
        this.monthExecutorService.compositeReport(DateUtil.string2Date("2017-12-03 01:00:00"));
        String[] yue = {"11-30", "12-03"};
        for(String item : yue){
            this.monthExecutorService.compositeReport(DateUtil.string2Date("2017-" + item + " 01:00:00"));
        }
    }

    /**
     * Method: registerReport()
     */
    @Test
    public void testRegisterReport() throws Exception{
        this.monthExecutorService.registerReport(new Date());
        String[] yue = {"11-30", "12-03"};
        for(String item : yue){
            this.monthExecutorService.registerReport(DateUtil.string2Date("2017-" + item + " 01:00:00"));
        }
    }

    /**
     * Method: rechargeReport()
     */
    @Test
    public void testRechargeReport() throws Exception{
        this.monthExecutorService.rechargeReport(new Date());
        String[] yue = {"11-30", "12-03"};
        for(String item : yue){
            this.monthExecutorService.rechargeReport(DateUtil.string2Date("2017-" + item + " 01:00:00"));
        }
    }

    /**
     * Method: loginSourceReport()
     */
    @Test
    public void testLoginSourceReport() throws Exception{
        this.monthExecutorService.loginSourceReport(new Date());
        String[] yue = {"11-20", "12-03"};
        for(String item : yue){
            this.monthExecutorService.loginSourceReport(DateUtil.string2Date("2017-" + item + " 01:00:00"));
        }
    }
} 
