/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercises;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author dmagadi
 */
public class WriteToFile {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("/Users/dmagadi/testfile.txt");
        
        FileOutputStream  fileStream = new FileOutputStream(file);
        
        fileStream.write("aamir".getBytes());
        
        fileStream.flush();
        
        fileStream.close();
        
    }
    
}
