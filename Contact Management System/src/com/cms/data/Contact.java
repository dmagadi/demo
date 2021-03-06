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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) throws ValidationException {
        if (!isFormatted("firstName", first_name)) {
            throw new ValidationException("First name not formatted correctly");
        } else {
            this.first_name = first_name;
        }

    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) throws ValidationException {
        if (!isFormatted("lastName", last_name)) {
            throw new ValidationException("Last name not formatted correctly.");
        } else {
            this.last_name = last_name;
        }

    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) throws ValidationException {
        if (!isFormatted("cell", cell)) {
            throw new ValidationException("Cell not formatted correctly.");
        } else {
            this.cell = cell;
        }

    }

    public String getHome() {

        return home;
    }

    public void setHome(String home) throws ValidationException {
        if (!isFormatted("home", home)) {
            throw new ValidationException("Home phone not formatted correctly.");
        } else {
            this.home = home;
        }

    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) throws ValidationException {
        if (!isFormatted("work", work)) {
            throw new ValidationException("Work phone not formatted correctly.");
        } else {
            this.work = work;
        }

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws ValidationException {
        if (!isFormatted("address", address)) {
            throw new ValidationException("Address not formatted correctly");
        } else {
            this.address = address;
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws ValidationException {
        if (!isFormatted("city", city)) {
            throw new ValidationException("City not formatted correctly.");
        } else {
            this.city = city;
        }

    }

    public String getState() {
        return state;
    }

    public void setState(String state) throws ValidationException {
        if (!isFormatted("state", state)) {
            throw new ValidationException("State not formatted correctly.");
        } else {
            this.state = state;
        }

    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipCode) throws ValidationException {

        if (!isFormatted("zipCode", zipCode)) {
            throw new ValidationException("Zipcode not formatted correctly.");
        } else {
            this.zipcode = zipCode;
        }

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

    public void printContact() {

        System.out.println(first_name + " " + last_name + "\n Cell: " + cell
                + "\n Home: " + home + "\n Work: " + work);
        System.out.println(address + "\n" + city + ", " + state + "," + zipcode + "\n");

    }

    public String getText() {

        return first_name + ";" + last_name + ";" + cell
                + ";" + home + ";" + work + ";" + address + ";" + city + ";" + state + ";" + zipcode;

    }

    public void fromText(String line) {
        String[] columns = line.split(";");

        first_name = columns[0];
        last_name = columns[1];
        cell = columns[2];
        home = columns[3];
        work = columns[4];
        address = columns[5];
        city = columns[6];
        state = columns[7];
        zipcode = columns[8];

        // Todo fill the rest
    }

    private boolean isFormatted(String field, String fieldName) {
        switch (field) {
            case "zipCode":
                if (fieldName == null || fieldName.isEmpty() || fieldName.length() > 10) {
                    return false;
                } else {
                    return true;
                }
            case "firstName":
                if (fieldName == null || fieldName.isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            case "lastName":
                if (fieldName == null || fieldName.isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            case "cell":
                if (fieldName == null || fieldName.isEmpty() || fieldName.length() > 10) {
                    return false;
                } else {
                    return true;
                }
            case "home":
                if (fieldName == null || fieldName.isEmpty() || fieldName.length() > 10) {
                    return false;
                } else {
                    return true;
                }
            case "work":
                if (fieldName == null || fieldName.isEmpty() || fieldName.length() > 10) {
                    return false;
                } else {
                    return true;
                }
            case "address":
                if (fieldName == null || fieldName.isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            case "city":
                if (fieldName == null || fieldName.isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            case "state":
                if (fieldName == null || fieldName.isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            default:
                return false;

        }

    }

}
