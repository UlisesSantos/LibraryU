package com.school.library.Java.LibraryU.service;

import com.school.library.Java.LibraryU.model.Loans;
import com.school.library.Java.LibraryU.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    public List<Loans> findAllLoans(){
        return loanRepository.findAll();
    }

    public void saveLoan(Loans loan){
        loanRepository.save(loan);
    }
}
