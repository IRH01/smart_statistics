package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.common.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * RoleService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>九月 25, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
@Transactional
public class RoleServiceTest extends BaseTest{
    @Autowired
    private RoleService RoleService;

    /**
     * Method: getRolesByUserId(int userId)
     */
    @Test
    public void testGetRolesByUserId() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getPerms(List<Integer> roleIds)
     */
    @Test
    public void testGetPerms() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: search(Role role, Page page)
     */
    @Test
    public void testSearch() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: delete(Integer roleId)
     */
    @Test
    public void testDelete() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: insert(Role role)
     */
    @Test
    public void testInsert() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: update(Role role)
     */
    @Test
    public void testUpdate() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: delPermsByRole(List<Integer> roleIds)
     */
    @Test
    public void testDelPermsByRole() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: insertRolePermission(List<RolePermission> rolePermissionList)
     */
    @Test
    public void testInsertRolePermission() throws Exception{
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
     * Method: allocRole(Integer userId, Integer[] roles)
     */
    @Test
    public void testAllocRole() throws Exception{
        //TODO: Test goes here...
    }


} 
