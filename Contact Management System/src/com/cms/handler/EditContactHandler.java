/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.handler;

import java.util.Scanner;
import com.cms.data.Contact;
import com.cms.exceptions.ValidationException;
import com.cms.main.IHandle;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author Aamir
 */
public class EditContactHandler implements IHandle {

    short FieldToEdit = 0;

    @Override
    public void handle(ArrayList<Contact> contacts) {
        try {
            editContact(contacts);
        } catch (ValidationException e) {
        }

    }

    private void editContact(ArrayList<Contact> contacts) throws ValidationException {
        int selectedContact = -1;
        out.println("Which field do you want to edit?");
        out.println("1 - First name\n2 - Last name\n3 - Cell phone number\n"
                + "4 - Home phone number\n5 - Work phone number\n6 - Address\n"
                + "7 - City\n8 - State\n9 - Zipcode\n10 - Cancel\n");
        do {
            try {
                FieldToEdit = new Scanner(System.in).nextShort();
            } catch (InputMismatchException e) {
                System.err.println("Number is not from 1-10.");
                FieldToEdit = 0;
            }
        } while (FieldToEdit < 1 || FieldToEdit > 10);
        if (FieldToEdit == 10) {
            return;
        }
        for (Contact contact : contacts) {
            
            System.out.print(contacts.indexOf(contact) + " - ");
            contact.printContact();
        }

        System.out.println("\nWhich contact do you want to edit?");
        do {
            try {
                selectedContact = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Not a number for a contact.");
                selectedContact = -1;
            }
        } while (selectedContact < 0 || selectedContact > contacts.size());
        System.out.println("Enter new field.");
        switch (FieldToEdit) {
            case 1:
                contacts.get(selectedContact).setFirst_name(getNonBlankString(""));
                break;
            case 2:
                contacts.get(selectedContact).setLast_name(getNonBlankString(""));
                break;
            case 3:
                contacts.get(selectedContact).setCell(getNonBlankString(""));
                break;
            case 4:
                contacts.get(selectedContact).setHome(getNonBlankString(""));
                break;
            case 5:
                contacts.get(selectedContact).setWork(getNonBlankString(""));
                break;
            case 6:
                contacts.get(selectedContact).setAddress(getNonBlankString(""));
                break;
            case 7:
                contacts.get(selectedContact).setCity(getNonBlankString(""));
                break;
            case 8:
                contacts.get(selectedContact).setState(getNonBlankString(""));
                break;
            case 9:
                contacts.get(selectedContact).setZipcode(getNonBlankString(""));
                break;
            default:
                break;
        }
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
