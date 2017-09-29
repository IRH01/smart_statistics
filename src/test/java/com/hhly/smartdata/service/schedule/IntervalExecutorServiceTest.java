package com.hhly.smartdata.service.schedule;

import com.hhly.smartdata.common.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * IntervalExecutorService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>九月 27, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
public class IntervalExecutorServiceTest extends BaseTest{
    @Autowired
    private IntervalExecutorService intervalExecutorService;

    /**
     * Method: intervalSourceStatistics()
     */
    @Test
    public void testIntervalSourceStatistics() throws Exception{
        this.intervalExecutorService.intervalSourceStatistics();
    }

    /**
     * Method: intervalInterfaceStatistics()
     */
    @Test
    public void testIntervalInterfaceStatistics() throws Exception{
        this.intervalExecutorService.intervalInterfaceStatistics();
    }

    /**
     * Method: intervalGameLaunch()
     */
    @Test
    public void testIntervalGameLaunch() throws Exception{
        this.intervalExecutorService.intervalGameLaunch();
    }


} 
