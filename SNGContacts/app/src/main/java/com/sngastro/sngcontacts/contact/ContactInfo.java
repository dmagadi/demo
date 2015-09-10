package com.sngastro.sngcontacts.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aamir on 7/10/2015.
 */
public class ContactInfo implements Serializable {

    private String firstName;
    private String lastName;
    private ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
    private ArrayList<Email> emails = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }



}
