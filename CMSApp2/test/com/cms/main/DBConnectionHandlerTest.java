/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.main;

import java.sql.Connection;
import java.sql.SQLException;
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
public class DBConnectionHandlerTest {
    
    public DBConnectionHandlerTest() {
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
     * Test of getConnectionToDatabase method, of class DBConnectionHandler.
     */
    @Test
    public void testGetConnectionToDatabase() throws SQLException {
        System.out.println("getConnectionToDatabase");
        DBConnectionHandler instance = new DBConnectionHandler();
        //Connection expResult = null;
        Connection result = instance.getConnectionToDatabase();
        
        assertNotNull(result);
        
        result.close();
   
    }
    
}
