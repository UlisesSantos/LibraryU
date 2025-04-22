package com.school.library.Java.LibraryU.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Books {
    
    @Id
    @GeneratedValue
    private Long bookId;

    @Column(name="isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name="nameBook")
    private String name;

    @Column(name = "autorBook")
    private String autor;

    @Column(name = "releaseDateBook")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date releaseDate;

    @Column(name = "availability")
    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "loanId")
    private Loans loans;

}
