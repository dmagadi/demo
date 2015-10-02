/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
        ResultSet contactsRS = null;
        ResultSet phoneRS = null;
        ResultSet emailRS = null;

        try {
            conn = DBConnectionHandler.getConnectionToDatabase();

            getContacts = conn.prepareStatement("Select * from contacts order by LastName, FirstName");
            getPhone = conn.prepareStatement("Select * from phone_numbers where contact_id=?");
            getEmail = conn.prepareStatement("Select * from email where contact_id=?");

            contactsRS = getContacts.executeQuery();
            ArrayList<ContactInfo> contactList = new ArrayList<>();
            while (contactsRS.next()) {
                ContactInfo contact = new ContactInfo();
                getPhone.setInt(1, contactsRS.getInt("id"));
                getEmail.setInt(1, contactsRS.getInt("id"));
                phoneRS = getPhone.executeQuery();
                emailRS = getEmail.executeQuery();
                while(phoneRS.next()) {
                    PhoneNumber number = new PhoneNumber();
                    number.number = phoneRS.getString("phone_number");
                    number.type = phoneRS.getString("phone_type");
                    contact.phoneNumbers.add(number);
                }
                
                while(emailRS.next()) {
                    Email email = new Email();
                    email.email = emailRS.getString("email");
                    email.type = emailRS.getString("email_type");
                    contact.emails.add(email);
                }
                contact.firstName = contactsRS.getString("FirstName");
                contact.lastName = contactsRS.getString("LastName");
                contactList.add(contact);
            }

            return contactList;

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {// always close connection/preparedstatment/statement/resultset here 
            DBConnectionHandler.closeRS(emailRS);
            DBConnectionHandler.closeRS(phoneRS);
            DBConnectionHandler.closeRS(contactsRS);
            DBConnectionHandler.closePreparedStatement(getContacts);
            DBConnectionHandler.closePreparedStatement(getEmail);
            DBConnectionHandler.closePreparedStatement(getPhone);
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
        public String firstName;
        public String lastName;
        public ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        public ArrayList<Email> emails = new ArrayList<>();
    }
    
    static class Result{
        public String value;
    }
    
    static class PhoneNumber {
        
        public String number;
        public String type;
        
    }
    
    static class Email {
        
        public String email;
        public String type;
        
    }
    
}
