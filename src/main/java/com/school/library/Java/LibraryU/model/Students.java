package com.school.library.Java.LibraryU.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue
    private Long studentId;

    @Column(name = "controlId", nullable = false, unique = true)
    private String controlId;

    @Column(name = "nameStudent", nullable = false)
    private String name;

    @Column(name = "lastnameStudent", nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "students")
    private List<Loans> loans;

}
