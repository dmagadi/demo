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
            
            String password = req.queryParams("password");
            
            //LoggerFactory.getLogger("main").info("Password " + password);
            
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ContactsServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            byte[] thedigest = md.digest(password.getBytes());
            String passwordHash = createHexString(thedigest);
            LoggerFactory.getLogger("main").info("Password hash: " + passwordHash);
            Result result = doLogin(passwordHash);
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
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionHandler.getConnectionToDatabase();

            pstmt = conn.prepareStatement("Select * from users");

            rs = pstmt.executeQuery();
            ArrayList<ContactInfo> contactList = new ArrayList<>();
            while (rs.next()) {
                ContactInfo user = new ContactInfo();
                user.name = rs.getString("name");
                user.cell = rs.getString("cell");
                user.home = rs.getString("home");
                user.email = rs.getString("email");
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
    private static Result doLogin(String passwordHash) {
        Result result = new Result();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DBConnectionHandler.getConnectionToDatabase();
            pstmt = conn.prepareStatement("Select * from users where password = ?");
            pstmt.setString(1, passwordHash);
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

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static String createHexString(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for ( int j = 0; j < bytes.length; j++ ) {
        int v = bytes[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 4];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
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
