package com.notescollab.notescollab.controller;

import com.notescollab.notescollab.entity.User;
import com.notescollab.notescollab.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize ("hasAuthority('ROLE_USER')")
    public User getUserDetails(@PathVariable("userid") Long userid) throws Exception {
        try {
            logger.info ( "Getting user details..." + userid );
            return userRepository.getUserById ( userid );
        }catch (Exception e){
            throw new Exception ( "Could not get user details"+e.getMessage (),e);
        }
    }

    @PostMapping("/auth/signup")
    public String addUser(@RequestBody User user){
        System.out.println("*********start adding new user"+user.getUsername ());
        return userRepository.saveUser (user);
    }

}
