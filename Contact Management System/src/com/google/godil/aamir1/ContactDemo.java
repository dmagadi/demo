/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.godil.aamir1;

import static java.lang.System.out;
import java.util.Scanner;

/**
 *
 * @author Aamir
 */
public class ContactDemo {

    private static String first_name;
    private static String last_name;
    private static String cell;
    private static String home;
    private static String work;
    private static String address;
    private static String city;
    private static String state;
    private static String zipcode;
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        
    }

    private static void displayHomeScreen() {

        out.println("Enter number to select option.\n\n1 - Display All Contacts."
                + "\n2 - Add Contact\n3 - Delete Contact\n4 - Edit Contact\n5 - "
                + "Search Contact");

    }

    private static void displayAllContacts() {

    }

    private static void addContact() {

        out.println("Enter first name.");
        first_name = input.nextLine();
        out.println("Enter last name.");
        last_name = input.nextLine();
        out.println("Enter address in form of (house_number street).");
        address = input.nextLine();
        out.println("Enter city.");
        city = input.nextLine();
        out.println("Enter state.");
        state = input.nextLine();
        out.println("Enter zipcode");
        zipcode = input.nextLine();
        out.println("Enter home phone number.");
        home = input.nextLine();
        out.println("Enter work phone number.");
        work = input.nextLine();
        out.println("Enter cell number.");
        cell = input.nextLine();
        

    }

}
