package com.jorojala.toolshare.models;

import javax.persistence.*;

@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String image;
    String description;
    Boolean isAvailable = true;

    @ManyToOne
    AppUser toolBorrowedByUser;

    public Tool(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public Tool() {
        // default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }


}
