package com.sngastro.sngcontacts.contact;

import java.io.Serializable;

/**
 * Created by Aamir on 9/6/2015.
 */
public class PhoneNumber implements Serializable {

    private String number;
    private String type;

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

}
