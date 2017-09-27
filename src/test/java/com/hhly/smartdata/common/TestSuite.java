package com.hhly.smartdata.common;

/**
 * Created by Iritchie.ren on 2017/9/25.
 */

import com.hhly.smartdata.service.schedule.DailyExecutorServiceTest;
import com.hhly.smartdata.service.schedule.IntervalExecutorServiceTest;
import com.hhly.smartdata.service.schedule.MonthExecutorServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IntervalExecutorServiceTest.class,
        DailyExecutorServiceTest.class,
        MonthExecutorServiceTest.class
})
public class TestSuite{
}
