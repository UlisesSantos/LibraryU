package com.school.library.Java.LibraryU.repository;

import com.school.library.Java.LibraryU.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Long> {
}
