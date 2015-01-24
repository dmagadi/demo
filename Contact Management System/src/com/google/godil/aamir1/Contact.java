/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.godil.aamir1;

/**
 *
 * @author Aamir
 */
public class Contact {
    String first_name;
    String last_name;
    String cell;
    String home;
    String work;
    String house_number;
    String street;
    String city;
    String state;
    int zipcode;
    
  
    
    void printAddress() {
        
        System.out.print(house_number + " " + street + "/n" + city + "," + state + "," + zipcode);
        
    }

    
}