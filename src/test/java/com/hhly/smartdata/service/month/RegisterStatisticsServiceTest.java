package com.hhly.smartdata.service.month;

import com.hhly.smartdata.common.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * RegisterStatisticsService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>十月 12, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
public class RegisterStatisticsServiceTest extends BaseTest{
    @Autowired
    private RegisterStatisticsService registerStatisticsService;

    /**
     * Method: search(TimeFilter filter)
     */
    @Test
    public void testSearch() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getLastTotalRegister()
     */
    @Test
    public void testGetLastTotalRegister() throws Exception{
        long lastTotalRegister = registerStatisticsService.getLastTotalRegister();
        System.err.println("结果：" + lastTotalRegister);
    }


} 
