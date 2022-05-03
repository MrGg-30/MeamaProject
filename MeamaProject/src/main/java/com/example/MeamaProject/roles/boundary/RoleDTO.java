package com.example.MeamaProject.roles.boundary;

import java.util.Collection;

public class RoleDTO {

    private Collection<String> privileges;

    private String name;

    public RoleDTO() {
    }

    public RoleDTO(Collection<String> privileges, String name) {
        this.privileges = privileges;
        this.name = name;
    }

    public Collection<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<String> privileges) {
        this.privileges = privileges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
