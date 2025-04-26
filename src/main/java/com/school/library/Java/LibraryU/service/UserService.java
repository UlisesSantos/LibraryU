package com.school.library.Java.LibraryU.service;

import com.school.library.Java.LibraryU.model.Users;
import com.school.library.Java.LibraryU.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(Users user){
        userRepository.save(user);
    }
}
