/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import com.google.gson.Gson;
import java.security.MessageDigest;
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
            ArrayList<ContactData> contacts = new ArrayList();
            
            //return getAllContacts();
            
            ContactData c = new ContactData();
            c.name = "Test";
            
            contacts.add(c);
            ContactData c2 = new ContactData();
            c2.name = "Test2";
            
            contacts.add(c2);
            return contacts;
        }, gson::toJson);
                
        get("/login", "application/json", (req, res) -> {
            LoggerFactory.getLogger("main").info("Login api...");
            
            String user = req.queryParams("user");
            String password = req.queryParams("password");
            
            LoggerFactory.getLogger("main").info("User " + user + " Password " + password);
            
            doLogin(user,password);
//            MessageDigest md = MessageDigest.getInstance("MD5");
            
//            byte[] thedigest = md.digest(password.getBytes());
            
            return "SUCCESS"; // replace this with Result Object 
        }, gson::toJson);
        
        
        get("/stop", (request, response) -> {
            SparkBase.stop();
            LoggerFactory.getLogger("main").warn("Stopping the server...");
            
            return "stopped";
        });
    }

    private static ArrayList<ContactData> getAllContacts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // mds hash
    private static void doLogin(String user, String passwordhash) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    static class ContactData{
        public String name;
        public String cellNumber;
        public String homeNumber;
        public String email;
                
    }
    
    static class Result{
        public String result;
    }

}
