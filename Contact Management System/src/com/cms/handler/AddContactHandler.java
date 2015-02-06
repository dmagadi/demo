/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.handler;

import com.cms.data.Contact;
import static java.lang.System.out;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
 *
 * @author dmagadi
 */
public class AddContactHandler {
    private  String first_name;
    private  String last_name;
    private  String cell;
    private  String home;
    private  String work;
    private  String address;
    private  String city;
    private  String state;
    private  String zipcode;
    public void handle() {
        
        
        
    }
    
    private  void addContact() {
        
        Contact contact = new Contact();

     
        contact.setFirst_name(getNonBlankString("Enter first name."));
        
        contact.setLast_name(getNonBlankString("Enter last name."));
        
        contact.setAddress(getNonBlankString("Enter Address Line 1"));
        
        
        
        
        
        
        out.println("Enter address in form of (house_number street).");
        address = new Scanner(System.in).nextLine();
        out.println("Enter city.");
        city = new Scanner(System.in).nextLine();
        out.println("Enter state.");
        state = new Scanner(System.in).nextLine();
        out.println("Enter zipcode");
        zipcode = new Scanner(System.in).nextLine();
        out.println("Enter home phone number.");
        home = new Scanner(System.in).nextLine();
        out.println("Enter work phone number.");
        work = new Scanner(System.in).nextLine();
        out.println("Enter cell number.");
        cell = new Scanner(System.in).nextLine();
        

    }

    private String getNonBlankString(String message) {
        String input = "";
        do {     
            System.out.println(message);
            input = new Scanner(System.in).nextLine();
            
        } while (input.trim().length() > 0);
        
        return input.trim();
    }


}
