package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.common.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * MenuService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>九月 25, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
@Transactional
public class MenuServiceTest extends BaseTest{
    @Autowired
    private MenuService MenuService;

    /**
     * Method: getMenuByPerms(List<String> perms)
     */
    @Test
    public void testGetMenuByPerms() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getAll()
     */
    @Test
    public void testGetAll() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: searchMenus(Menu menu)
     */
    @Test
    public void testSearchMenus() throws Exception{
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
     * Method: update(Menu menu)
     */
    @Test
    public void testUpdate() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: updateMenuTree(JSONArray menuTree, Integer parentId)
     */
    @Test
    public void testUpdateMenuTree() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: sortAndUpdateMenus(JSONArray menuTree, Integer parentId)
     */
    @Test
    public void testSortAndUpdateMenus() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getMenuListByRole(List<Integer> roleIds)
     */
    @Test
    public void testGetMenuListByRole() throws Exception{
        //TODO: Test goes here...
    }


} 
