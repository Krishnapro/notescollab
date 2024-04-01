package com.notescollab.notescollab.controller;

import com.notescollab.notescollab.entity.User;
import com.notescollab.notescollab.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationObservationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger ("com.notescollab.notescollab.controller.UserController");

    @GetMapping("/greeting")
    public String greeting(){
        logger.info("info:: Greeting");
        logger.warn ("warn:: Greeting");
        logger.error ("error:: Greeting");
        return "Hello world! Spring Boot Rest API";
    }
    @GetMapping("/getuserdetails/{userid}")
    @PreAuthorize ("hasRole('ROLE_USER')")
    public ResponseEntity<?> getUserDetails(@PathVariable("userid") Long userid) throws Throwable {
        try {

            logger.info ( "Getting user details..." + userid );
            User user = userRepository.getUserById ( userid );
            if(user == null){
                throw new UsernameNotFoundException ( "user " + userid + " does not found");
            }
            return ResponseEntity.ok (user);
        }catch (UsernameNotFoundException e) {
            throw e;
        }catch(Exception e){
            logger.info("getUserDetails:: Exception while getting user details"+e.getMessage(),e);
            return ResponseEntity.ok ("User "+userid+" does not found");

        }
    }

    @PostMapping("/auth/signup")
    public String addUser(@RequestBody User user){
        System.out.println("*********start adding new user"+user.getUsername ());
        return userRepository.saveUser (user);
    }

}
