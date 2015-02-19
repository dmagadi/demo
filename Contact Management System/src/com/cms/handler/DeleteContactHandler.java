/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.handler;

import com.cms.data.Contact;
import com.cms.main.IHandle;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aamir
 */
public class DeleteContactHandler implements IHandle {

    @Override
    public void handle(ArrayList<Contact> contacts) {
        deleteContact(contacts);
    }

    public void deleteContact(ArrayList<Contact> contacts) {
        Contact SearchedContact;
        switch (SearchContactHandler.getFirstOrLastNameSearch()) {
            case 1:
                SearchedContact = SearchContactHandler.firstNameSearch(contacts);
            case 2:
                SearchedContact = SearchContactHandler.lastNameSearch(contacts);
            case 3:
                System.out.println("Cancelled.");
            default:
                break;

        }
    }

    public void confirmDelete() {
        System.out.println("Are you sure do you want to delete this contact?\nEnter Y/N.");
        try {
            char confirmation = new Scanner(System.in).next();
            } catch (Exception e) {
        }
    }

}
