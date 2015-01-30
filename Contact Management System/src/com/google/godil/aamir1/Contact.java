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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public int getHouse_number() {
        return house_number;
    }

    public void setHouse_number(int house_number) {
        this.house_number = house_number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipCode) throws ValidationException {
        
        if(!isFormatted(zipCode)){
            throw new ValidationException("Zip code not formatted correctly");
        }
        
        this.zipcode = zipCode;
    }

    private String first_name;
    private String last_name;
    private String cell;
    private String home;
    private String work;
    private int house_number;
    private String street;
    private String city;
    private String state;
    private String zipcode;

    public Contact(String first_name, String last_name, String cell, String home, 
            String work, int house_number, String street, String city, 
            String state, String zipcode) throws ValidationException {
        this.first_name = first_name;
        this.last_name = last_name;
        this.cell = cell;
        this.home = home;
        this.work = work;
        this.house_number = house_number;
        this.street = street;
        this.city = city;
        this.state = state;
        //this.zipcode = zipcode;
        
        setZipcode(zipcode);
        
    }

    void printContact() {

        System.out.println(first_name + " " + last_name + "\n Cell: " + cell + "\n Home: " + home + "\n Work: " + work);
        System.out.println(house_number + " " + street + "\n" + city + "," + state + "," + zipcode);

    }

    private boolean isFormatted(String zipCode) {
        
        if(zipCode == null || zipCode.isEmpty() || zipCode.length() != 10){
            return false;
        }
        return true;
    }

}
