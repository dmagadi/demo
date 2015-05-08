/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.main;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author dmagadi
 */
public class DBConnectionHandler {
    
    public Connection getConnectionToDatabase() {
        
        Connection conn = null;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager
		.getConnection("jdbc:mysql://10.37.129.3:3306/cms","aamir", "password");
        }catch(Throwable e){
            throw new RuntimeException(e);
        }
        
        return null;
        
    }
    
}
