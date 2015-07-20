package com.sngastro.sngcontacts;

import java.io.Serializable;

/**
 * Created by Aamir on 7/10/2015.
 */
public class ContactInfo implements Serializable {

    private String name;
    private String home;
    private String cell;
    private String email;

    public ContactInfo(String name, String home, String cell, String email) {
        setName(name);
        setHome(home);
        setCell(cell);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
