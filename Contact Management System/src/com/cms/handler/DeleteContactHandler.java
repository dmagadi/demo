/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.handler;

import com.cms.data.Contact;
import com.cms.main.IHandle;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
        System.out.println("Please search the contact to delete.");

        SearchContactHandler searchContactHandler = new SearchContactHandler();
        searchContactHandler.handle(contacts);

        if (searchContactHandler.getSearchedContacts() != null && !searchContactHandler.getSearchedContacts().isEmpty()) {
            System.out.println("Please select a number to delete.");
            int selectedIndex = -1;
            
            do {
             selectedIndex = getNumberInput();
            
            }while(selectedIndex >= contacts.size() || selectedIndex < 0 );
            
            if(confirmDelete()){
            
            Contact contactToDelete = searchContactHandler.getSearchedContacts().get(selectedIndex);
            
            deleleFromContacts(contactToDelete,contacts);
            }
        }

    }

    public Boolean confirmDelete() {
        String yesOrNo;
        do {
            System.out.println("Are you sure do you want to delete this contact?\nEnter Y/N.");
             yesOrNo = new Scanner(System.in).nextLine();
            
            }while(!yesOrNo.equalsIgnoreCase("y") && !yesOrNo.equalsIgnoreCase("n"));
        
       
        
        return (yesOrNo.equalsIgnoreCase("y"))?true:false;
    }

    private int getNumberInput() {
        int input = -1;
        
        
        try {
                input = new Scanner(System.in).nextShort();
            } catch (InputMismatchException e) {
                System.err.println("Please enter a valid option.");
                
            }
        
        
        return input - 1;
        
    }

    private void deleleFromContacts(Contact contactToDelete, ArrayList<Contact> contacts) {
        
        contacts.remove(contactToDelete);
    }

}
