/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmagadi
 */
public class Config {

    public static void initProperties(String appName) {

        String userHomeFolder = System.getProperty("user.home");

        new File(userHomeFolder + "/" + appName + "").mkdirs();
        String propertyFile = userHomeFolder + "/" + appName + "/" + appName + ".properties";
        if (!new File(propertyFile).exists()) {
            try {
                //create file
                new File(userHomeFolder + "/" + appName + "/" + appName + ".properties").createNewFile();

                Properties prop = new Properties();
                OutputStream output = null;

                try {

                    output = new FileOutputStream(propertyFile);

                    // set the properties value
                    prop.setProperty("database", "localhost");
                    prop.setProperty("dbuser", "root");
                    prop.setProperty("dbpassword", "");
                    prop.setProperty("dbport", "3306");
                    prop.setProperty("apiport", "8888");

                    // save properties to project root folder
                    prop.store(output, null);

                } catch (IOException io) {
                    throw new RuntimeException(io);
                } finally {
                    if (output != null) {
                        try {
                            output.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }

            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
            readPropFile(appName);
        
    }

    public static Properties prop = null;

    public static void readPropFile(String appName) {
        String userHomeFolder = System.getProperty("user.home");

        new File(userHomeFolder + "/" + appName + "").mkdirs();
        String propertyFile = userHomeFolder + "/" + appName + "/" + appName + ".properties";

        try {
            prop = new Properties();

            prop.load(new FileInputStream(new File(propertyFile)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
    
    public static String getProperty(String propertyName, String defaultValue) {
        
        if (prop == null) {
            throw new RuntimeException("Please call init before calling getProperty()");
        }
        if (propertyName == null || propertyName.isEmpty()) {
            throw new RuntimeException("Property name cannot be null or empty.");
        }
        return prop.getProperty(propertyName, defaultValue);
        
    }

}
