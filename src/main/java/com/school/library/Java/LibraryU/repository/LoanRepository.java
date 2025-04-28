package com.school.library.Java.LibraryU.repository;

import com.school.library.Java.LibraryU.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {


}
