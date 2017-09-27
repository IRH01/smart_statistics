package com.hhly.smartdata.service.schedule;

import com.hhly.smartdata.common.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        this.monthExecutorService.compositeReport();
    }

    /**
     * Method: registerReport()
     */
    @Test
    public void testRegisterReport() throws Exception{
        this.monthExecutorService.registerReport();
    }

    /**
     * Method: rechargeReport()
     */
    @Test
    public void testRechargeReport() throws Exception{
        this.monthExecutorService.rechargeReport();
    }

    /**
     * Method: loginSourceReport()
     */
    @Test
    public void testLoginSourceReport() throws Exception{
        this.monthExecutorService.loginSourceReport();
    }
} 
