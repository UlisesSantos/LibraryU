package com.school.library.Java.LibraryU.controller;

import com.school.library.Java.LibraryU.common.LogConfig;
import com.school.library.Java.LibraryU.model.*;
import com.school.library.Java.LibraryU.service.BookService;
import com.school.library.Java.LibraryU.service.LoanService;
import com.school.library.Java.LibraryU.service.StudentService;
import com.school.library.Java.LibraryU.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("libraryu/loans")
public class LoanController {

    private static final Logger LOGGER = LogManager.getLogger(LoanController.class);

    @Autowired
    private LoanService loanService;

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllLoans(){
        LogConfig.initLog4j2();
        LOGGER.info("Getting all loans from the database");
        ResponseEntity<?> respEnt = null;
        try {
            List<Loans> loans = loanService.findAllLoans();

            respEnt = new ResponseEntity<List<Loans>>(loans,HttpStatus.OK);
        }catch (Exception e){
            respEnt = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respEnt;
    }

    @PostMapping
    public ResponseEntity<?> saveLoan(@RequestBody LoanRequest loanRequest, @AuthenticationPrincipal UserPrincipal userPrincipal){
        LogConfig.initLog4j2();
        LOGGER.info("Registering loan in the database");
        ResponseEntity<?> respEnt = null;
        try {
            if(loanRequest == null || loanRequest.getBookIds() == null || loanRequest.getStudentId() == null){
                return  respEnt = new ResponseEntity<>("Parameters Error", HttpStatus.BAD_REQUEST);
            }

            Loans loan = new Loans();

            loan.setStatusLoan(true);

            Optional<Users> newUser = userService.getUserById(userPrincipal.getId());
            if (newUser.isPresent()){
                loan.setUsers(newUser.get());
            }

            List<Books> books = bookService.findAllBooksById(loanRequest.getBookIds());
            for(Books book : books){
                book.setLoans(loan);
            }
            loan.setBooks(books);

            Optional<Students> newStudent = studentService.getStudentById(loanRequest.getStudentId());
            if(newStudent.isPresent()){
                loan.setStudents(newStudent.get());
            }

            loan.setLoanDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            loan.setDueDate(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()));

            loanService.saveLoan(loan);
            respEnt = new ResponseEntity<Loans>(loan, HttpStatus.OK);

        }catch (Exception e){
            respEnt = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return respEnt;
    }
}
