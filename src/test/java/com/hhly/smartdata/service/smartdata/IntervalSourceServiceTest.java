package com.hhly.smartdata.service.smartdata;

import com.hhly.smartdata.common.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        this.intervalSourceService.selectTotalDaySourceListData();
    }

    /**
     * Method: selectIntervalTimeTerminalsSourceListData(String startDate, String endDate, int pageNumber, int pageSize, String deviceType)
     */
    @Test
    public void testSelectIntervalTimeTerminalsSourceListData() throws Exception{
        this.intervalSourceService.selectIntervalTimeTerminalsSourceListData("00:00", "24:00", 1, 5, "");
    }


} 
