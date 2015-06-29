/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.file;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aamir
 */
public class WriteToFileHandler {
    private Formatter f;
    public void writeToFile(String ip, String user, String password) {
        
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            f = new Formatter("LoginSettings.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WriteToFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.format("%s%s%s", ip + " ", user + " ", password);
        f.close();
    }
    
}
