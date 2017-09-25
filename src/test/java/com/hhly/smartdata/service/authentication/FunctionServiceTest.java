package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.common.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * FunctionService Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>九月 25, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-context.xml"})
@Transactional
public class FunctionServiceTest extends BaseTest{
    @Autowired
    private FunctionService FunctionService;

    /**
     * Method: getAll()
     */
    @Test
    public void testGetAll() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: save(Function function)
     */
    @Test
    public void testSave() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: updateFuncTree(JSONArray funcTree, Integer parentId)
     */
    @Test
    public void testUpdateFuncTree() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: batchUpdateFuncs(JSONArray funcTree, Integer parentId)
     */
    @Test
    public void testBatchUpdateFuncs() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: update(Function function)
     */
    @Test
    public void testUpdate() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: delete(Integer id)
     */
    @Test
    public void testDelete() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: queryByRole(List<Integer> roleIds)
     */
    @Test
    public void testQueryByRole() throws Exception{
        //TODO: Test goes here...
    }


} 
