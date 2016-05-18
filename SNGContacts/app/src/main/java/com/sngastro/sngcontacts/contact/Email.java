package com.sngastro
        .sngcontacts.contact;

import java.io.Serializable;

/**
 * Created by Aamir on 9/6/2015.
 */
public class Email implements Serializable {

    private String email;
    private String type;

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

}
