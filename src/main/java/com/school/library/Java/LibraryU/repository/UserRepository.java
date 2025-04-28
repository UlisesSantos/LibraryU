package com.school.library.Java.LibraryU.repository;

import com.school.library.Java.LibraryU.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {


    @Transactional(readOnly = true)
    Optional<Users> findUserByUsername(String username);

    @Transactional(readOnly = true)
    Optional<Users> findUserByEmail(String email);

}
