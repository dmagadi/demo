/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aamir
 */
public class ReadFileHandler {

    String items[] = new String[3];
    private Scanner s;

    public String[] readFile() {
        int index = 0;
        File f = new File("LoginSettings.txt");
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (s.hasNext()) {

            items[index++] = s.next();
            //items[1] = s.next();
            //items[2] = s.next();

        }
        s.close();
        return items;
    }

}
