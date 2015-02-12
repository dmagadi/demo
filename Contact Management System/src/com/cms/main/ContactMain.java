package com.cms.main;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cms.handler.AddContactHandler;
import static java.lang.System.out;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Aamir
 */
public class ContactMain {

    static Scanner input = new Scanner(System.in);

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
        switch (option) {
            
            case 1:
            case 2:
                // creating the class and calling the method in one line
                new AddContactHandler().handle();
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                break;
            default:
                System.out.println("Please enter a valid option.");
        }
    }

}
