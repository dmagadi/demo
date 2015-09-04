/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import com.google.gson.Gson;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.get;
import spark.SparkBase;

/**
 *
 * @author dmagadi
 */
public class ContactsServer {

    public static void main(String[] args) {
        SparkBase.port(8888);
        
        Gson gson = new Gson();
        
        get("/contacts", "application/json", (req, res) -> {
            LoggerFactory.getLogger("main").info("Returning contacts from the server...");
            ArrayList<ContactInfo> contactList = getAllContacts();
            return contactList;
        }, gson::toJson);
                
        get("/login", "application/json", (req, res) -> {
            LoggerFactory.getLogger("main").info("Login api...");
            String user = req.queryParams("user");
            String passwordHash  = req.queryParams("password");
            
            //LoggerFactory.getLogger("main").info("Password " + password);
            
//            MessageDigest md = null;
//            try {
//                md = MessageDigest.getInstance("MD5");
//            } catch (NoSuchAlgorithmException ex) {
//                Logger.getLogger(ContactsServer.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            byte[] thedigest = md.digest(password.getBytes());
//            String passwordHash = createHexString(thedigest);
            LoggerFactory.getLogger("main").info("Password hash: " + passwordHash);
            Result result = doLogin(user,passwordHash);
            return result; // replace this with Result Object 
        }, gson::toJson);
        
        
        get("/stop", (request, response) -> {
            SparkBase.stop();
            LoggerFactory.getLogger("main").warn("Stopping the server...");
            
            return "stopped";
        });
    }

    private static ArrayList<ContactInfo> getAllContacts() {
        Connection conn = null;
        PreparedStatement getContacts = null;
        PreparedStatement getPhone = null;
        PreparedStatement getEmail = null;
        ResultSet contacts = null;
        ResultSet phone = null;
        ResultSet email = null;

        try {
            conn = DBConnectionHandler.getConnectionToDatabase();

            getContacts = conn.prepareStatement("Select * from contacts");
            getPhone = conn.prepareStatement("Select * from phone_numbers where contact_id=?");
            getEmail = conn.prepareStatement("Select * from phone_numbers where contact_id=?");

            contacts = getContacts.executeQuery();
            ArrayList<ContactInfo> contactList = new ArrayList<>();
            while (contacts.next()) {
                ContactInfo user = new ContactInfo();
                getPhone.setInt(1, contacts.getInt("id"));
                getEmail.setInt(1, contacts.getInt("id"));
                phone = getPhone.executeQuery();
                email = getEmail.executeQuery();
                
                
                
                
                contactList.add(user);
            }

            return contactList;

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {// always close connection/preparedstatment/statement/resultset here 
            DBConnectionHandler.closeRS(rs);
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);
        }
    }
    // mds hash
    private static Result doLogin(String user,String passwordHash) {
        Result result = new Result();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DBConnectionHandler.getConnectionToDatabase();
            pstmt = conn.prepareStatement("Select * from users where username = ? and password = ?");
            pstmt.setString(1, user);
            pstmt.setString(2, passwordHash);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result.value = "SUCCESS";
            } else {
                result.value = "FAILURE";
            }
            
        } catch (Exception e) {
            
            throw new RuntimeException(e);
            
        } finally {
            DBConnectionHandler.closeRS(rs);
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);
        }
        return result;
    }
    
    static class ContactInfo{
        public String name;
        public String home;
        public String cell;
        public String email;       
    }
    
    static class Result{
        public String value;
    }

}
