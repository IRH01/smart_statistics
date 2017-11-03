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

    private static final Integer INTERVAL_TIME = 30;

    /**
     * Method: intervalSourceStatistics()
     */
    @Test
    public void testIntervalSourceStatistics() throws Exception{
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getBeforeThirtyMinutePoint(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);
        this.intervalExecutorService.intervalSourceStatistics(startDate, INTERVAL_TIME);
    }

    /**
     * Method: intervalInterfaceStatistics()
     */
    @Test
    public void testIntervalInterfaceStatistics() throws Exception{
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getBeforeThirtyMinutePoint(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);
        this.intervalExecutorService.intervalInterfaceStatistics(startDate, INTERVAL_TIME);
    }

    /**
     * Method: intervalGameLaunch()
     */
    @Test
    public void testIntervalGameLaunch() throws Exception{
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getBeforeThirtyMinutePoint(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);
        this.intervalExecutorService.intervalGameLaunch(startDate, INTERVAL_TIME);
    }


} 
