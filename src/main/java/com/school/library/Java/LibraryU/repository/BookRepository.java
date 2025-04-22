package com.school.library.Java.LibraryU.repository;

import com.school.library.Java.LibraryU.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Long> {
}
