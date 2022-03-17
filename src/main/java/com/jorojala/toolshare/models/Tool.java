package com.jorojala.toolshare.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String image;
    String description;
    Boolean isAvailable = true;
    Boolean openReturnRequest = false;
    double distanceFromUser;


    @ManyToOne
    AppUser toolListedByUser;

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

    public AppUser getToolBorrowedByUser() {
        return toolBorrowedByUser;
    }

    public void setToolBorrowedByUser(AppUser toolBorrowedByUser) {
        this.toolBorrowedByUser = toolBorrowedByUser;
    }

    public AppUser getToolListedByUser() {
        return toolListedByUser;
    }

    public void setToolListedByUser(AppUser toolListedByUser) {
        this.toolListedByUser = toolListedByUser;
    }

    public double getDistanceFromUser() {
        return distanceFromUser;
    }

    public void setDistanceFromUser(double distanceFromUser) {
        this.distanceFromUser = distanceFromUser;
    }

    public Boolean getOpenReturnRequest() {
        return openReturnRequest;
    }

    public void setOpenReturnRequest(Boolean openReturnRequest) {
        this.openReturnRequest = openReturnRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tool tool = (Tool) o;
        return Double.compare(tool.distanceFromUser, distanceFromUser) == 0 && id.equals(tool.id) && Objects.equals(name, tool.name) && Objects.equals(image, tool.image) && Objects.equals(description, tool.description) && isAvailable.equals(tool.isAvailable) && openReturnRequest.equals(tool.openReturnRequest) && Objects.equals(toolListedByUser, tool.toolListedByUser) && Objects.equals(toolBorrowedByUser, tool.toolBorrowedByUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, description, isAvailable, openReturnRequest, distanceFromUser, toolListedByUser, toolBorrowedByUser);
    }
}
