package com.school.library.Java.LibraryU.repository;

import com.school.library.Java.LibraryU.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Transactional(readOnly = true)
    List<Books> findByAvailability(Boolean availability);

    @Transactional(readOnly = true)
    Optional<Books> findByIsbn(String isbn);

    @Transactional(readOnly = true)
    List<Books> findByName(String name);

    @Transactional(readOnly = true)
    List<Books> findByAuthor(String author);

}
