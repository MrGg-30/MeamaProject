package com.example.MeamaProject.privileges.entity;

import com.example.MeamaProject.roles.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "privileges")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege")
    @SequenceGenerator(name = "privilege", sequenceName = "seq_privileges", allocationSize = 1, initialValue = 1000)
    private Long id;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege() {
    }

    public Privilege(Long id,  String name, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
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

    public Collection<Role> getPrivileges() {
        return roles;
    }

    public void setPrivileges(Collection<Role> roles) {
        this.roles = roles;
    }
}
