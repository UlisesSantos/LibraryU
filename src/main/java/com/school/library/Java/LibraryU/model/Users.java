package com.school.library.Java.LibraryU.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
}
