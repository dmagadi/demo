
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dmagadi
 */
public class Config {
    
    public  enum PropKey {
        DB_USER("db.user"),DB_PWD("db.pwd");
        
        
         String key;
        
         PropKey(String key){
            this.key = key;
        }
        
        
    }
    
    
    static Properties myProperties = new Properties();
    
    static{
     // do your load stuff here    
        load();
    }
    
    
    private static void load(){
        // do the file load here
    }
    
    public static String  getConfig( PropKey prop, String defaults){
        
        
        myProperties.getProperty(prop.key, defaults);
        
        
        return null;
    }
    
    public static void  setConfig( PropKey key, String value){
        
         myProperties.setProperty(key.key,value);
    }
    
    public static void save(){
       // myProperties.store(null, null);
        load();
    }
    
    
}
