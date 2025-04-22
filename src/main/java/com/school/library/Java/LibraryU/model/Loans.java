package com.school.library.Java.LibraryU.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loans")
public class Loans {

    @Id
    @GeneratedValue
    private Long loanId;

    @OneToMany(mappedBy = "loans")
    private List<Books> books;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Students students;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

    @Column(name = "loanDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date loanDate;

    @Column(name = "dueDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

}
