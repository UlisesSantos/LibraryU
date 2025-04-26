package com.school.library.Java.LibraryU.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "passwordUser", nullable = false)
    private String password;

    @Column(name = "nameUser", nullable = false)
    private String name;

    @Column(name = "lastnameUser", nullable = false)
    private String lastname;

    @Column(name = "emailUser", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "users")
    private List<Loans> loans;

    public Users(Long userId, String username, String password, String name, String lastname, String email, List<Loans> loans) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.loans = loans;
    }

    public Users() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public List<Loans> getLoans() {
        return loans;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoans(List<Loans> loans) {
        this.loans = loans;
    }
}
