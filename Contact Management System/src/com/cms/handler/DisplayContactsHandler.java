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
 * @author dmagadi
 */
public class DisplayContactsHandler implements IHandle {

    public DisplayContactsHandler() {
    }

    @Override
    public void handle(ArrayList<Contact> contacts) {
        for (Contact contact : contacts) {
            int index = contacts.indexOf(contact);
            System.out.print(++index + " - ");
            contact.printContact();
        }
        System.out.println("\nPress enter to return to menu.");
        new Scanner(System.in).nextLine();

    }

}
