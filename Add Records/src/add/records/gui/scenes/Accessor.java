/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add.records.gui.scenes;

import java.net.URL;

/**
 *
 * @author Aamir
 */
public class Accessor {
    /**
     * 
     * @param path
     * @return 
     */
    public URL getURL(String path) {
        return getClass().getResource(path);
    }
    
}
