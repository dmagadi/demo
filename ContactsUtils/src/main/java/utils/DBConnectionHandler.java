/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

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

    
    
    public static Connection getConnectionToDatabase() {
        
        String ip = String.format("%s:%s", Config.getProperty("database", "localhost"), Config.getProperty("dbport", "3306"));
        String user = Config.getProperty("dbuser", "root");
        String password = Config.getProperty("dbpassword", "");
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
