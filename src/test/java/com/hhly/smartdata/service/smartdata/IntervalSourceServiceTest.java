package com.hhly.smartdata.service.smartdata;

import com.hhly.smartdata.common.BaseTest;
import com.hhly.smartdata.service.interval.IntervalSourceService;
import com.hhly.smartdata.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * IntervalSourceService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>十一月 1, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
public class IntervalSourceServiceTest extends BaseTest{
    @Autowired
    private IntervalSourceService intervalSourceService;

    /**
     * Method: selectIntervalTerminalsSourceListData()
     */
    @Test
    public void testSelectTotalDaySourceListData() throws Exception{
        Date now = new Date();
        String startTime = DateUtil.date2String(DateUtil.getNowZeroTime(now));
        String endTime = DateUtil.date2String(DateUtil.getNowLongestTime(now));
        this.intervalSourceService.selectTotalDaySourceListData(startTime, endTime);
    }

    /**
     * Method: selectIntervalTimeTerminalsSourceListData(String startDate, String endDate, int pageNumber, int pageSize, String deviceType)
     */
    @Test
    public void testSelectIntervalTimeTerminalsSourceListData() throws Exception{
        this.intervalSourceService.selectIntervalTimeTerminalsSourceListData("00:00", "24:00", 1, 5, "");
    }


} 
