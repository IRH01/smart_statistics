package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.common.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * AdminService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>九月 25, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
@Transactional
public class AdminServiceTest extends BaseTest{
    @Autowired
    private AdminService AdminService;

    /**
     * Method: searchAdmins(Admin condition, Page page)
     */
    @Test
    public void testSearchAdmins() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: save(Admin admin)
     */
    @Test
    public void testSave() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: update(Admin admin)
     */
    @Test
    public void testUpdate() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: get(Integer id)
     */
    @Test
    public void testGet() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: deleteByUserId(int userId)
     */
    @Test
    public void testDeleteByUserId() throws Exception{
        //TODO: Test goes here...
    }


} 
