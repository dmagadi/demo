package com.cms.main;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cms.data.Contact;
import com.cms.handler.AddContactHandler;
import com.cms.handler.DeleteContactHandler;
import com.cms.handler.DisplayContactsHandler;
import com.cms.handler.SearchContactHandler;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Aamir
 */
public class ContactMain {

    static Scanner input = new Scanner(System.in);

    private final static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        short option = 0;
        do {
            displayHomeScreen();
            try {
                option = new Scanner(System.in).nextShort();
            } catch (InputMismatchException e) {
                System.err.println("Please enter a valid option.");
                continue;

            }

            handleOption(option);

        } while (option != 6);
    }

    private static void displayHomeScreen() {

        out.println("Enter number to select option.\n\n1 - Display All Contacts."
                + "\n2 - Add Contact\n3 - Delete Contact\n4 - Edit Contact\n5 - "
                + "Search Contact\n6 - Exit");

    }

    private static void handleOption(short option) {

        IHandle handler = null;

        switch (option) {

            case 1:
                handler = new DisplayContactsHandler();
                break;
            case 2:
                // creating the class and calling the method in one line
                handler = new AddContactHandler();
                break;
            case 3:
                handler = new DeleteContactHandler();
                break;
            case 4:
                break;
            case 5:
                handler = new SearchContactHandler();
                break;
            case 6:
                break;
            default:
                System.out.println("Please enter a valid option.");
        }

        if (handler != null) {
            handler.handle(contacts);
        }
    }

}
