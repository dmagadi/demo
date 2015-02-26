/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.handler;

import com.cms.data.Contact;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dmagadi
 */
public class SearchUtils {
    
        protected List<Contact> firstNameSearch(ArrayList<Contact> contacts) {
            Scanner input = new Scanner(System.in);
            System.out.println("Search for a contact.");
            String search = input.nextLine();
            for(Contact contact: contacts) {
                short i = 0;
                if(contact.getFirst_name().contains(search.trim())) {
                    System.out.println(++i + " - " + contact.getFirst_name() + " " + contact.getLast_name());
                }
            }
            return contacts;
    }
    
    protected List<Contact> lastNameSearch(ArrayList<Contact> contacts) {
        Scanner input = new Scanner(System.in);
            System.out.println("Search for a contact.");
            String search = input.nextLine();
            for(Contact contact: contacts) {
                short i = 0;
                if(contact.getFirst_name().contains(search.trim())) {
                    System.out.println(++i + " - " + contact.getFirst_name() + " " + contact.getLast_name());
                }
            }
        return contacts;
    }
    
}
