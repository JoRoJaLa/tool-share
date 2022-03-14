package com.jorojala.toolshare.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;
    String zipcode;
    ArrayList toolsListed;
    Boolean admin = false;
    String location;

    @OneToMany(mappedBy = "toolBorrowedByUser", cascade = CascadeType.ALL)
    List<Tool> toolsBorrowed;

    public AppUser()
    {
        // default constructor
    }

    public AppUser(String username, String password, String zipcode) {
        this.username = username;
        this.password = password;
        this.zipcode = zipcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipCode) {
        this.zipcode = zipCode;
    }

    public ArrayList getToolsListed() {
        return toolsListed;
    }

    public void setToolsListed(ArrayList toolsListed) {
        this.toolsListed = toolsListed;
    }

    public List getToolsBorrowed() {
        return toolsBorrowed;
    }

    public void setToolsBorrowed(List toolsBorrowed) {
        this.toolsBorrowed = toolsBorrowed;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
