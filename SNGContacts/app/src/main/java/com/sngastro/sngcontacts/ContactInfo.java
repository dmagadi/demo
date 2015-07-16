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
    private String DOB;

    public ContactInfo(String name, String home, String cell, String email, String DOB) {
        setName(name);
        setHome(home);
        setCell(cell);
        setEmail(email);
        setDOB(DOB);
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

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

}
