package com.school.library.Java.LibraryU.service;

import com.school.library.Java.LibraryU.common.LogConfig;
import com.school.library.Java.LibraryU.model.UserPrincipal;
import com.school.library.Java.LibraryU.model.Users;
import com.school.library.Java.LibraryU.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Logger object
    private static final Logger LOGGER = LogManager.getLogger(MyUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        LogConfig.initLog4j2();

        Optional<Users> usersOptional = userRepository.findUserByUsername(username);
        if(usersOptional.isPresent()){
            LOGGER.info("Login user from database");
            Users user = usersOptional.get();
            return new UserPrincipal(user);
        }
        else {
            LOGGER.error("Could not found the user in the database");
            throw new UsernameNotFoundException("User not found");
        }
    }
}
