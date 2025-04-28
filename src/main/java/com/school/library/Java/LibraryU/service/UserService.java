package com.school.library.Java.LibraryU.service;

import com.school.library.Java.LibraryU.model.Users;
import com.school.library.Java.LibraryU.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Retrieves all users
     * @return a list of all users
     */
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Retrieves a single user by its ID
     * @param id The ID of the user
     * @return an Optional containing the user if found
     */
    public Optional<Users> getUserById(Long id){
        return userRepository.findById(id);
    }

    /**
     * Retrieves a single user by its email
     * @param email The email of the user
     * @return an Optional containing the user if found
     */
    public Optional<Users> getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    /**
     *  Saves a user in to the database
     * @param user the user to save
     */
    public void saveUser(Users user){
        userRepository.save(user);
    }

    /**
     * Updates the name of a single user
     * @param id the ID of the user to update
     * @param name the new name to update the user
     */
    public void updateUserName(Long id, String name){
        Optional<Users> newUser = userRepository.findById(id);

        if(newUser.isPresent()){
            Users user = newUser.get();
            user.setName(name);
            userRepository.save(user);
        }
    }

    /**
     * Updates the lastname of a single user
     * @param id the ID of the user to update
     * @param lastname the new lastname to update the user
     */
    public void updateUserLastname(Long id, String lastname){
        Optional<Users> newUser = userRepository.findById(id);

        if(newUser.isPresent()){
            Users user = newUser.get();

            user.setLastname(lastname);
            userRepository.save(user);
        }
    }

    /**
     * Updates the username of a single user
     * @param id the ID of the user to update
     * @param username the new username to update the user
     */
    public void updateUserUsername(Long id, String username){
        Optional<Users> newUser = userRepository.findById(id);

        if(newUser.isPresent()){
            Users user = newUser.get();
            user.setUsername(username);
            userRepository.save(user);
        }
    }

    /**
     * Updates the email of a single user
     * @param id the ID of the user to update
     * @param email the new email to update the user
     */
    public void updateUserEmail(Long id, String email){
        Optional<Users> newUser = userRepository.findById(id);

        if(newUser.isPresent()){
            Users user = newUser.get();
            user.setEmail(email);
            userRepository.save(user);
        }
    }

    /**
     * Deletes a user by its ID
     * @param id the ID of a user to delete
     */
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    /**
     * Deletes a user by its username
     * @param username the username of a user to delete
     */
    public void deleteUserByUsername(String username){
        Optional<Users> newUser = userRepository.findUserByUsername(username);

        if (newUser.isPresent()){
            Users user = newUser.get();
            userRepository.deleteById(user.getUserId());
        }
    }

    /**
     * Deletes a user by its email
     * @param email the email of a user to delete
     */
    public void deleteUserByEmail(String email){
        Optional<Users> newUser = userRepository.findUserByEmail(email);

        if (newUser.isPresent()){
            Users user = newUser.get();
            userRepository.deleteById(user.getUserId());
        }
    }
}
