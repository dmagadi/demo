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
import com.cms.handler.EditContactHandler;
import com.cms.handler.SearchContactHandler;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            readContacts();

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
                handler = new AddContactHandler();
                break;
            case 3:
                handler = new DeleteContactHandler();
                break;
            case 4:
                handler = new EditContactHandler();
                break;
            case 5:
                handler = new SearchContactHandler();
                break;
            case 6:
                saveAllContacts();
                break;
            default:
                System.out.println("Please enter a valid option.");
        }

        if (handler != null) {
            handler.handle(contacts);
        }
    }

    private static void saveAllContacts() {

        try {
            // check if file exists

            BufferedWriter writer = new BufferedWriter(new FileWriter("cms.data"));

            for (Contact contact : contacts) {
                writer.write(contact.getText());
                writer.newLine();
            }
            writer.flush();
            writer.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContactMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContactMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void readContacts() {
        try {

            File file = new File("cms.data");

            if (!file.exists()) {
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader("cms.data"));

            String line = reader.readLine();
            do {
                if (line != null) {
                    Contact contact = new Contact();
                    contact.fromText(line);
                    contacts.add(contact);
                }
                
                line = reader.readLine();

            } while (line != null);

        } catch (Exception ex) {
            Logger.getLogger(ContactMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
