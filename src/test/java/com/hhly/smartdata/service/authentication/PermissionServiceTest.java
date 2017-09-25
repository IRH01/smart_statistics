package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.common.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * PermissionService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>九月 25, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
@Transactional
public class PermissionServiceTest extends BaseTest{
    @Autowired
    private PermissionService PermissionService;

    /**
     * Method: searchPerms(Permission perm)
     */
    @Test
    public void testSearchPerms() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: delete(Permission condition)
     */
    @Test
    public void testDelete() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: batchUpdatePerms(Integer funcId, List<Permission> perms)
     */
    @Test
    public void testBatchUpdatePerms() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: save(Permission permission)
     */
    @Test
    public void testSave() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: queryByRole(List<Integer> roleIds)
     */
    @Test
    public void testQueryByRole() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getAll()
     */
    @Test
    public void testGetAll() throws Exception{
        //TODO: Test goes here...
    }


} 
