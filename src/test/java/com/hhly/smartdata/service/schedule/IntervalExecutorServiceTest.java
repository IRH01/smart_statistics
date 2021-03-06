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
        String[] yue = {"10-30", "10-31", "11-01", "11-02", "11-03", "11-04", "11-05", "11-06", "11-07", "11-08", "11-09", "11-10", "11-11"};
        for(String item : yue){
            for(int i = 0; i < 10; i++){
                this.intervalExecutorService.intervalSourceStatistics("2017-" + item + " 0" + i + ":00:00", INTERVAL_TIME);
                this.intervalExecutorService.intervalSourceStatistics("2017-" + item + " 0" + i + ":30:00", INTERVAL_TIME);
            }
            for(int i = 0; i < 10; i++){
                this.intervalExecutorService.intervalSourceStatistics("2017-" + item + " 1" + i + ":00:00", INTERVAL_TIME);
                this.intervalExecutorService.intervalSourceStatistics("2017-" + item + " 1" + i + ":30:00", INTERVAL_TIME);
            }
            for(int i = 0; i < 5; i++){
                this.intervalExecutorService.intervalSourceStatistics("2017-" + item + " 2" + i + ":00:00", INTERVAL_TIME);
                this.intervalExecutorService.intervalSourceStatistics("2017-" + item + " 2" + i + ":30:00", INTERVAL_TIME);
            }
        }
    }

    /**
     * Method: intervalInterfaceStatistics()
     */
    @Test
    public void testIntervalInterfaceStatistics() throws Exception{
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getBeforeThirtyMinutePoint(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);
//        this.intervalExecutorService.intervalInterfaceStatistics(startDate, INTERVAL_TIME);
        String[] yue = {"10-30", "10-31", "11-01", "11-02", "11-03", "11-04", "11-05", "11-06", "11-07", "11-08", "11-09", "11-10", "11-11"};
        for(String item : yue){
            for(int i = 0; i < 10; i++){
                this.intervalExecutorService.intervalInterfaceStatistics("2017-" + item + " 0" + i + ":00:00", INTERVAL_TIME);
                this.intervalExecutorService.intervalInterfaceStatistics("2017-" + item + " 0" + i + ":30:00", INTERVAL_TIME);
            }
            for(int i = 0; i < 10; i++){
                this.intervalExecutorService.intervalInterfaceStatistics("2017-" + item + " 1" + i + ":00:00", INTERVAL_TIME);
                this.intervalExecutorService.intervalInterfaceStatistics("2017-" + item + " 1" + i + ":30:00", INTERVAL_TIME);
            }
            for(int i = 0; i < 5; i++){
                this.intervalExecutorService.intervalInterfaceStatistics("2017-" + item + " 2" + i + ":00:00", INTERVAL_TIME);
                this.intervalExecutorService.intervalInterfaceStatistics("2017-" + item + " 2" + i + ":30:00", INTERVAL_TIME);
            }
        }
    }

    /**
     * Method: intervalGameLaunch()
     */
    @Test
    public void testIntervalGameLaunch() throws Exception{
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getBeforeThirtyMinutePoint(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);
//        this.intervalExecutorService.intervalGameLaunch(startDate, INTERVAL_TIME);
        String[] yue = {"10-30", "10-31", "11-01", "11-02", "11-03", "11-04", "11-05", "11-06", "11-07", "11-08", "11-09", "11-10", "11-11"};
        for(String item : yue){
            for(int i = 0; i < 10; i++){
                this.intervalExecutorService.intervalGameLaunch("2017-" + item + " 0" + i + ":00:00", INTERVAL_TIME);
                this.intervalExecutorService.intervalGameLaunch("2017-" + item + " 0" + i + ":30:00", INTERVAL_TIME);
            }
            for(int i = 0; i < 10; i++){
                this.intervalExecutorService.intervalGameLaunch("2017-" + item + " 1" + i + ":00:00", INTERVAL_TIME);
                this.intervalExecutorService.intervalGameLaunch("2017-" + item + " 1" + i + ":30:00", INTERVAL_TIME);
            }
            for(int i = 0; i < 5; i++){
                this.intervalExecutorService.intervalGameLaunch("2017-" + item + " 2" + i + ":00:00", INTERVAL_TIME);
                this.intervalExecutorService.intervalGameLaunch("2017-" + item + " 2" + i + ":30:00", INTERVAL_TIME);
            }
        }
    }


}
