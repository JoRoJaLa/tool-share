package com.jorojala.toolshare.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;
    String zipcode;
    ArrayList toolsListed;
    Boolean admin = false;
    Results[] location;

    @OneToMany(mappedBy = "toolBorrowedByUser", cascade = CascadeType.ALL)
    List<Tool> toolsBorrowed;

    public AppUser()
    {
        // default constructor
    }

    public AppUser(String username, String password, String zipcode, Results[] location) {
        this.username = username;
        this.password = password;
        this.zipcode = zipcode;
        this.location = location;
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        this.zipcode = zipcode;
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

    public Results[] getLocation() {
        return location;
    }

    public void setLocation(Results[] location) {
        this.location = location;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
