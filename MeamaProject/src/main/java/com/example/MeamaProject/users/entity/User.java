package com.example.MeamaProject.users.entity;

import com.example.MeamaProject.roles.entity.Role;
import com.example.MeamaProject.tasks.entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
    @SequenceGenerator(name = "user", sequenceName = "seq_user", allocationSize = 1, initialValue = 1000)
    private Long id;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "username", length = 255, nullable = false)
    private String username;

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "first_name")
    private String firstname;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "last_name")
    private String lastname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Collection<Task> assignedTasks;

    public User(Long id, String username, String password,
                String firstname, String lastname, Role role,
                Collection<Task> assignedTasks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.assignedTasks = assignedTasks;
    }

    public User() {

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(Collection<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstname, lastname);
    }
}
