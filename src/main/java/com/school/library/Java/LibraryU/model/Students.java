package com.school.library.Java.LibraryU.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
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
    //@JsonManagedReference
    private List<Loans> loans;

    public Students(Long studentId, String controlId, String name, String lastname, List<Loans> loans) {
        this.studentId = studentId;
        this.controlId = controlId;
        this.name = name;
        this.lastname = lastname;
        this.loans = loans;
    }

    public Students() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getControlId() {
        return controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Loans> getLoans() {
        return loans;
    }

    public void setLoans(List<Loans> loans) {
        this.loans = loans;
    }
}
