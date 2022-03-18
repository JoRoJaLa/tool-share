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
    String email;
    Boolean admin = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "results_id", referencedColumnName = "id")
    Results results;

    @OneToMany(mappedBy = "toolListedByUser", cascade = CascadeType.ALL)
    List<Tool> toolsListed;
    @OneToMany(mappedBy = "toolBorrowedByUser", cascade = CascadeType.ALL)
    List<Tool> toolsBorrowed;

    public AppUser()
    {
        // default constructor
    }

    public AppUser(String username, String password, String zipcode, String email) {
        this.username = username;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<Tool> getToolsListed() {
        return toolsListed;
    }

    public void setToolsListed(List<Tool> toolsListed) {
        this.toolsListed = toolsListed;
    }

    public void addTooltoListedTools(Tool tool) {
        if (toolsListed == null) toolsListed = new ArrayList<>();
        this.toolsListed.add(tool);
    }
    public void addTooltoBorrowedTools(Tool tool) {
        if (toolsBorrowed == null) toolsBorrowed = new ArrayList<>();
        this.toolsBorrowed.add(tool);
    }

    public List<Tool> getToolsBorrowed() {
        return toolsBorrowed;
    }

    public void setToolsBorrowed(List<Tool> toolsBorrowed) {
        this.toolsBorrowed = toolsBorrowed;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
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
