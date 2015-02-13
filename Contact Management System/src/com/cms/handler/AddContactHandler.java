/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.handler;

import com.cms.data.Contact;
import com.cms.exceptions.ValidationException;
import com.cms.main.IHandle;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmagadi
 */
public class AddContactHandler implements IHandle{

    private String first_name;
    private String last_name;
    private String cell;
    private String home;
    private String work;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    
    private ArrayList<Contact> contacts = null;

    @Override
    public void handle(ArrayList<Contact> contacts) {
        try {
            //Set the contacts to field contacts.
            this.contacts = contacts;
            addContact();
        } catch (ValidationException ex) {

        }

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
        
        contacts.add(contact);

    }

    private String getNonBlankString(String message) {
        String input = "";
        do {
            System.out.println(message);
            input = new Scanner(System.in).nextLine();

        } while (input.trim().length() <= 0);

        return input.trim();
    }

}
