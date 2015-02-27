/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.handler;

import com.cms.data.Contact;
import com.cms.main.IHandle;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Aamir
 */
public class SearchContactHandler implements IHandle {
    private List<Contact> searchedContacts = null;

    public List<Contact> getSearchedContacts() {
        return searchedContacts;
    }
    @Override
    public void handle(ArrayList<Contact> contacts) {

        short selectedInput = getFirstOrLastNameSearch();
        
        
        switch (selectedInput) {
            case 1:
                searchedContacts = new SearchUtils().firstNameSearch(contacts);
                break;
            case 2:
                searchedContacts = new SearchUtils().lastNameSearch(contacts);
                break;
            case 3:
                System.out.println("Cancelled.");
                break;
            default:
                break;

        }
        //if(searchedContacts != null)
          //  printContacts();

    }

    protected short getFirstOrLastNameSearch() {
        short input = 0;
        do {
            out.println("Do you want to search by first or last name?");
            out.println("Enter 1, 2, or 3 to select option.\n");
            out.println("1. Search by first name.\n2. Search by last name.\n3. Cancel.");
            try {
                input = new Scanner(System.in).nextShort();
            } catch (InputMismatchException e) {
                System.err.println("Please enter a valid option.");
                continue;
            }
        } while (input < 1 && input >3);

        return input;

    }

    private void printContacts() {
        
        int index = 1;
        for(Contact contact :  searchedContacts){
            System.out.println(index++ + " - "+  contact.getFirst_name() + " " + contact.getLast_name());
        }
        
    }

}
