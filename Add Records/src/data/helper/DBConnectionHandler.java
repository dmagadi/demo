/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilterReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmagadi
 */
public class DBConnectionHandler {

    public static Properties prop = null;
    
    
    static{
              String userHomeFolder = System.getProperty("user.home");

        new File(userHomeFolder + "/addrecords").mkdirs();
        String propertyFile = userHomeFolder + "/addrecords/addrecords.properties";
   
        try {
            prop = new Properties();
            
            prop.load(new FileInputStream(new File(propertyFile)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public static Connection getConnectionToDatabase() {
        
        String ip = "localhost:3306";
        String user = prop.getProperty("dbuser");
        String password = "";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager
                    .getConnection("jdbc:mysql://" + ip + "/sng_db", user, password);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return conn;

    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void closeRS(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  }

}
