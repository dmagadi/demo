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
import java.util.Scanner;

/**
 *
 * @author Aamir
 */
public class SearchContactHandler implements IHandle {

    @Override
    public void handle(ArrayList<Contact> contacts) {

    }

    protected static short getFirstOrLastNameSearch() {
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
        } while (input != 1 || input != 2 || input != 3);

        return input;

    }
    
    protected static Contact firstNameSearch(ArrayList<Contact> contacts) {
        
    }
    
    protected static Contact lastNameSearch(ArrayList<Contact> contacts) {
        
    }
}
