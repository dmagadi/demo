/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.data;

import com.cms.exceptions.ValidationException;

/**
 *
 * @author Aamir
 */
public class Contact {

    public Contact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

        if (!isFormatted(zipCode)) {
            throw new ValidationException("Zip code not formatted correctly");
        }

        this.zipcode = zipCode;
    }

    private String first_name;
    private String last_name;
    private String cell;
    private String home;
    private String work;
    private String address;
    private String city;
    private String state;
    private String zipcode;

    public Contact(String first_name, String last_name, String cell, String home,
            String work, String address, String city,
            String state, String zipcode) throws ValidationException {
        setFirst_name(first_name);
        setLast_name(last_name);
        setCell(cell);
        setHome(home);
        setWork(work);
        setAddress(address);
        setCity(city);
        setState(state);
        setZipcode(zipcode);
    }

    public void printContact() {

        System.out.println(first_name + " " + last_name + "\n Cell: " + cell
                + "\n Home: " + home + "\n Work: " + work);
        System.out.println(address + "\n" + city + "," + state + "," + zipcode);

    }

    private boolean isFormatted(String zipCode) {

        if (zipCode == null || zipCode.isEmpty() || zipCode.length() > 10) {
            return false;
        }
        return true;
    }

}
