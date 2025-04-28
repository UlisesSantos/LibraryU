package com.school.library.Java.LibraryU.repository;

import com.school.library.Java.LibraryU.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {

    @Transactional(readOnly = true)
    List<Students> findStudentsByName(String name);

    @Transactional(readOnly = true)
    List<Students> findStudentsByLastname(String lastname);

    @Transactional(readOnly = true)
    Optional<Students> findStudentByControlId(String controlId);
}
