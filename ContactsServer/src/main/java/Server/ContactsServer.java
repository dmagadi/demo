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
import java.util.Date;
import java.util.List;
import javax.xml.ws.WebServiceException;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.core.profile.UserProfile;
import org.pac4j.jwt.profile.JwtGenerator;
import org.pac4j.sparkjava.CallbackRoute;
import org.pac4j.sparkjava.RequiresAuthenticationFilter;
import org.slf4j.LoggerFactory;
import spark.Route;
import static spark.Spark.before;
import static spark.Spark.get;
import spark.SparkBase;
import utils.Config;
import utils.DBConnectionHandler;

/**
 *
 * @author dmagadi
 */
public class ContactsServer {

    /**
     *
     * @param args
     */
    static boolean login = false;
    
    private final static String JWT_SALT = "12345678901234567890123456789012";

    
    public static void main(String[] args) {
        
        Config.initProperties("ContactsServer");
        
        SparkBase.port(Integer.parseInt(Config.getProperty("apiport", "8888")));
        
        SparkBase.secure(System.getProperty("user.home") + "/ContactsServer/keystore.jks", "password", null, null);
        
        final org.pac4j.core.config.Config config = new SNGConfigFactory(JWT_SALT).build();
        
        final Route callback = new CallbackRoute(config);
        
        
        before("/contacts", new RequiresAuthenticationFilter(config, "ParameterClient", "excludedPath"));
        
        
        Gson gson = new Gson();

        get("/contacts", "application/json", (req, res) -> {
            LoggerFactory.getLogger("main").info("Returning contacts from the server...");
            ArrayList<ContactInfo> contactList = null;
            
                contactList = getAllContacts();
            login = false;
            return contactList;
        }, gson::toJson);

        get("/login", "application/json", (req, res) -> {
            LoggerFactory.getLogger("main").info("Login api...");
            String user = req.queryParams("user");
            String passwordHash = req.queryParams("password");

            //LoggerFactory.getLogger("main").info("Password hash: " + passwordHash);
            Result result = doLogin(user, passwordHash);
            if (result.value.equals("SUCCESS")) {
                login = true;
                
            }
            return result; // replace this with Result Object 
        }, gson::toJson);

//        get("/stop", (request, response) -> {
//            
//            String stop = "null";
//            
//            if (login) {
//                
//                stop = "stopped";
//                SparkBase.stop();
//                LoggerFactory.getLogger("main").warn("Stopping the server...");
//                
//            } else {
//                stop = "not stopped";
//            }
//            
//            return stop;
//            
//        });
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
                while (phoneRS.next()) {
                    PhoneNumber number = new PhoneNumber();
                    number.number = phoneRS.getString("phone_number");
                    number.type = phoneRS.getString("phone_type");
                    contact.phoneNumbers.add(number);
                }

                while (emailRS.next()) {
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

    /**
     *
     * @param user
     * @param passwordHash
     * @return
     */
    private static Result doLogin(String user, String passwordHash) {
        Result result = new Result();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if((user == null || user.isEmpty())  || (passwordHash == null || passwordHash.isEmpty()) ){
            throw new WebServiceException("User and Password cannot be empty");
        }
        try {

            conn = DBConnectionHandler.getConnectionToDatabase();
            pstmt = conn.prepareStatement("Select * from users where username = ? and password = ?");
            pstmt.setString(1, user);
            pstmt.setString(2, passwordHash);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result.value = "SUCCESS";
                result.token = generateToken(user, "Admin");
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
  
    
  
    public static String  generateToken(String username, String permission){
        final JwtGenerator generator = new JwtGenerator(JWT_SALT);
        String token = "";
        UserProfile userProfile = new UserProfile();
        userProfile.setId(username);
        userProfile.addPermission(permission);
        userProfile.addAttribute("Expiry", new Date());
        //userProfile.setRemembered(true);
        
        return generator.generate(userProfile);
        
    }
    

    static class ContactInfo {

        public String firstName;
        public String lastName;
        public ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        public ArrayList<Email> emails = new ArrayList<>();

    }

    static class Result {

        public String value;
        public String token;

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
