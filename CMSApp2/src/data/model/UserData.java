/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import java.sql.Timestamp;

/**
 *
 * @author dmagadi
 */
public class UserData {
    
    private String userName;
    private String firstName;

    public Timestamp getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(Timestamp createdTS) {
        this.createdTS = createdTS;
    }

    public Timestamp getModifiedTS() {
        return modifiedTS;
    }

    public void setModifiedTS(Timestamp modifiedTS) {
        this.modifiedTS = modifiedTS;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private String lastName;
    private Boolean isAdmin;
    private Timestamp createdTS;
    private Timestamp modifiedTS;
    private String password;
    private Integer id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
}
