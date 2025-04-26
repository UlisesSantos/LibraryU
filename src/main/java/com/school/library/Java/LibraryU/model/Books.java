package com.school.library.Java.LibraryU.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue
    private Long bookId;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "nameBook")
    private String name;

    @Column(name = "authorBook")
    private String author;

    @Column(name = "releaseDateBook")
    private Date releaseDate;

    @Column(name = "availability")
    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "loanId")
    private Loans loans;

    public Books(Long bookId, String isbn, String name, String author, Date releaseDate, Boolean availability, Loans loans) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
        this.availability = availability;
        this.loans = loans;
    }

    public Books() {
    }

    public Long getBookId() {
        return this.bookId;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public Boolean getAvailability() {
        return this.availability;
    }

    public Loans getLoans() {
        return this.loans;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public void setLoans(Loans loans) {
        this.loans = loans;
    }
}
