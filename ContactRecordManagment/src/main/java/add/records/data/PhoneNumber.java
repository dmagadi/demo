/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add.records.data;

/**
 *
 * @author Aamir
 */
public class PhoneNumber {

    public String number;
    public String type;
    
    /**
     * 
     * @param number
     * @param type 
     */
    public PhoneNumber(String number, String type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }
    
    /**
     * 
     * @param number 
     */
    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }
    
    /**
     * 
     * @param type 
     */
    public void setType(String type) {
        this.type = type;
    }

}
