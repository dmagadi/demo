/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.model.UserData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dmagadi
 */
public class UserBOTest {
    
    public UserBOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class UserBO.
     */
    @Test
    public void testLoginPass() {
        System.out.println("login");
        String userName = "admin";
        String password = "admin";
        UserBO instance = new UserBO();
        UserData result = instance.login(userName, password);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
         
    @Test
    public void testLoginFail() {
        System.out.println("login");
        String userName = "admin";
        String password = "admin";
        UserBO instance = new UserBO();
        Boolean expResult = null;
        UserData result = instance.login(userName, password);
        assertNull(result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
