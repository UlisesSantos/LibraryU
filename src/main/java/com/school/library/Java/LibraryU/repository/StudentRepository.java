package com.school.library.Java.LibraryU.repository;

import com.school.library.Java.LibraryU.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {
}
