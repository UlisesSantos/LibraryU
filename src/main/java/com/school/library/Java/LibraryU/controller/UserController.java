package com.school.library.Java.LibraryU.controller;

import com.school.library.Java.LibraryU.common.LogConfig;
import com.school.library.Java.LibraryU.model.Books;
import com.school.library.Java.LibraryU.model.Users;
import com.school.library.Java.LibraryU.security.MyPasswordEncoder;
import com.school.library.Java.LibraryU.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("libraryu/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MyPasswordEncoder passwordEncoder;

    public static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestHeader HttpHeaders headers){
        LogConfig.initLog4j2();
        List<Users> users = null;
        ResponseEntity<?> respEnt = null;

        LOGGER.info("Getting all users from the DataBase. Headers {}", headers);
        try{
            users = userService.getAllUsers();

            if(users.isEmpty()){
                respEnt = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            respEnt = new ResponseEntity<List<Users>>(users, HttpStatus.OK);

        }catch(CannotCreateTransactionException e){
            respEnt = new ResponseEntity<>("DataBase Service not Responding",HttpStatus.SERVICE_UNAVAILABLE);
            LOGGER.info(e);
        }

        return respEnt;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody Users user){
        LogConfig.initLog4j2();
        LOGGER.info("Registering user in the database");
        ResponseEntity<?> respEnt = null;

        try{
            if(user == null || user.getUsername() == null || user.getPassword() == null){
                return  respEnt = new ResponseEntity<>("Parameters Error", HttpStatus.BAD_REQUEST);
            }

            String rawPassword = user.getPassword();
            user.setPassword(passwordEncoder.encode(rawPassword));
            userService.saveUser(user);
        } catch (CannotCreateTransactionException e){
            respEnt = new ResponseEntity<>("DataBase Service not Responding",HttpStatus.SERVICE_UNAVAILABLE);
            LOGGER.info(e);
        } catch (DataIntegrityViolationException e){
            respEnt = new ResponseEntity<>("Duplicated User", HttpStatus.CONFLICT);
            LOGGER.info(e);
        }

        return respEnt = new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deletesUserByUsername(@PathVariable String username){
        LogConfig.initLog4j2();
        ResponseEntity<?> respEnt = null;
        LOGGER.info("Deleting a user by its username");

        try{
            if(username == null){
                return respEnt = new ResponseEntity<>("Parameters Error", HttpStatus.BAD_REQUEST);
            }

            userService.deleteUserByUsername(username);
            respEnt = new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            respEnt = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return respEnt;
    }

}
