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
public class Email {
    
    public String email;
    public String type;

    public Email(String email, String type) {
        this.email = email;
        this.type = type;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
