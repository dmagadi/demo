/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import add.records.data.Email;
import add.records.data.PhoneNumber;
import utils.DBConnectionHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;
/**
 * 
 * @author Aamir
 */
public class UserBO {

    int userID;
    int contactID;

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
        ResultSet rs = null;
        Boolean userSuccessfullyAdded = true;
        try {

            conn = DBConnectionHandler.getConnectionToDatabase();
            pstmt = conn.prepareStatement("INSERT INTO `sng_db`.`users` (`username`, `password`, `FirstName`, `LastName`, `createdts`, `isAdmin`) VALUES (?, ?, ?, ?, now(), ?);");
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setInt(5, adminValue);
            pstmt.execute();
            pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();
            rs.next();
            userID = rs.getInt("id");
            
        } catch (Exception e) {
            userSuccessfullyAdded = false;
            throw new RuntimeException(e);

        } finally {
            DBConnectionHandler.closeRS(rs);
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);
        }
        
        return userSuccessfullyAdded;
        
    }

    public Boolean addContact(String firstName, String lastName, ArrayList<PhoneNumber> phoneNumbers, ArrayList<Email> emails) {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Boolean contactSuccessfullyAdded = true;
        
        try {
            
            conn = DBConnectionHandler.getConnectionToDatabase();
            pstmt = conn.prepareStatement("INSERT INTO `sng_db`.`contacts` (`FirstName`, `createts`, `userid`, `LastName`) VALUES (?, now(), ?, ?);");
            pstmt.setString(1, firstName);
            pstmt.setInt(2, userID);
            pstmt.setString(3, lastName);
            pstmt.execute();
            pstmt = conn.prepareStatement("SELECT * FROM contacts WHERE userid = ?");
            pstmt.setInt(1, userID);
            rs = pstmt.executeQuery();
            rs.next();
            contactID = rs.getInt("id");
            
            for (PhoneNumber phoneNumber : phoneNumbers) {
                
                pstmt = conn.prepareStatement("INSERT INTO `sng_db`.`phone_numbers` (`phone_number`, `phone_type`, `contact_id`) VALUES (?, ?, ?);");
                pstmt.setString(1, phoneNumber.getNumber());
                pstmt.setString(2, phoneNumber.getType());
                pstmt.setInt(3, contactID);
                pstmt.execute();
                
            }
            
            for (Email email : emails) {
                
                pstmt = conn.prepareStatement("INSERT INTO `sng_db`.`email` (`email`, `email_type`, `contact_id`) VALUES (?, ?, ?);");
                pstmt.setString(1, email.getEmail());
                pstmt.setString(2, email.getType());
                pstmt.setInt(3, contactID);
                pstmt.execute();
                
            }
            
        } catch (Exception e) {
            contactSuccessfullyAdded = false;
        } finally {
            DBConnectionHandler.closeRS(rs);
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);
        }
        
        return contactSuccessfullyAdded;
        
    }

}
