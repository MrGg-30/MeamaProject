package com.example.MeamaProject.tasks.boundary;

import javax.validation.constraints.NotEmpty;

public class TaskDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String shortDescription;

    @NotEmpty
    private String longDescription;

    @NotEmpty
    private String username;

    public TaskDTO(String name, String shortDescription, String longDescription, String username) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.username = username;
    }

    public TaskDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
