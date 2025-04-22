package com.school.library.Java.LibraryU.repository;

import com.school.library.Java.LibraryU.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
