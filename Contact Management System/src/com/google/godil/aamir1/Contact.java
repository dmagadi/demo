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
    int house_number;
    String street;
    String city;
    String state;
    int zipcode;

    public Contact(String first_name, String last_name, String cell, String home, String work, int house_number, String street, String city, String state, int zipcode) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.cell = cell;
        this.home = home;
        this.work = work;
        this.house_number = house_number;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    void printContact() {

        System.out.println(first_name + " " + last_name + "\n Cell: " + cell + "\n Home: " + home + "\n Work: " + work);
        System.out.println(house_number + " " + street + "\n" + city + "," + state + "," + zipcode);

    }

}
