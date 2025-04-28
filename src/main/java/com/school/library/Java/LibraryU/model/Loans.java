package com.school.library.Java.LibraryU.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loans")
public class Loans {

    @Id
    @GeneratedValue
    private Long loanId;

    @Column(name = "statusLoan")
    private Boolean statusLoan;

    @Column(name = "loanDate")
    private Date loanDate;

    @Column(name = "dueDate")
    private Date dueDate;

    @OneToMany(mappedBy = "loans")
    //@JsonManagedReference
    private List<Books> books;

    @ManyToOne
    @JoinColumn(name = "studentId")
    //@JsonBackReference
    private Students students;

    @ManyToOne
    @JoinColumn(name = "userId")
    //@JsonBackReference
    private Users users;

    public Loans() {
    }

    public Loans(Long loanId, List<Books> books, Students students, Users users, Date loanDate, Date dueDate) {
        this.loanId = loanId;
        this.books = books;
        this.students = students;
        this.users = users;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public Long getLoanId() {
        return loanId;
    }

    public Boolean getStatusLoan() {
        return statusLoan;
    }

    public List<Books> getBooks() {
        return books;
    }

    public Students getStudents() {
        return students;
    }

    public Users getUsers() {
        return users;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setStatusLoan(Boolean statusLoan) {
        this.statusLoan = statusLoan;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
