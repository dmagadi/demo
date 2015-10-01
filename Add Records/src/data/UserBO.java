/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.helper.DBConnectionHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
/**
 *
 * @author dmagadi
 */
public class UserBO {

    

    public Boolean login(String userName, String password) {
        

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = DBConnectionHandler.getConnectionToDatabase();

            pstmt = conn.prepareStatement("Select * from users where username = ? and password = ?");

            pstmt.setString(1, userName);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Logger.getLogger(getClass().getName()).info(String.format("User First Name is %s ", rs.getString("FirstName")));
                return rs.getBoolean("isAdmin");
            }

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {// always close connection/preparedstatment/statement/resultset here 
            DBConnectionHandler.closeRS(rs);
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);
        }

        return false;

    }

    public Boolean addUser(String userName, String password, String firstName, String lastName, int adminValue) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        Boolean userSuccessfullyAdded = true;
        try {

            conn = DBConnectionHandler.getConnectionToDatabase();
            pstmt = conn.prepareStatement("INSERT INTO `cms`.`users` (`username`, `password`, `FirstName`, `LastName`, `createdts`) VALUES (?, ?, ?, ?, now());");
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.execute();

        } catch (Exception e) {
            userSuccessfullyAdded = false;
            throw new RuntimeException(e);

        } finally {
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);
        }
        return userSuccessfullyAdded;
    }

}
