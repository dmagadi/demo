/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.handler;

import com.cms.data.Contact;
import com.cms.exceptions.ValidationException;
import java.util.Scanner;

/**
 *
 * @author dmagadi
 */
public class AddContactHandler {

    private String first_name;
    private String last_name;
    private String cell;
    private String home;
    private String work;
    private String address;
    private String city;
    private String state;
    private String zipcode;

    public void handle() {
        
    }

    private void addContact()
            throws ValidationException {

        Contact contact = new Contact();

        contact.setFirst_name(getNonBlankString("Enter first name."));

        contact.setLast_name(getNonBlankString("Enter last name."));

        contact.setAddress(getNonBlankString("Enter Address Line 1"));

        contact.setCity(getNonBlankString("Enter city."));

        contact.setState(getNonBlankString("Enter state."));

        contact.setZipcode(getNonBlankString("Enter zipcode."));

        contact.setHome(getNonBlankString("Enter home phone number."));

        contact.setWork(getNonBlankString("Enter work phone number."));

        contact.setCell(getNonBlankString("Enter cell phone number."));

    }

    private String getNonBlankString(String message) {
        String input = "";
        do {
            System.out.println(message);
            input = new Scanner(System.in).nextLine();

        } while (input.trim().length() > 0);

        return input.trim();
    }

}
