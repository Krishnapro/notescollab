package com.notescollab.notescollab.controller;

import com.notescollab.notescollab.entity.User;
import com.notescollab.notescollab.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User getUserDetails(Long userId){
        return userRepository.getUserById (userId);
    }

    @PostMapping("/singnup")
    public User addUser(@RequestBody User user){
        System.out.println("*********start adding new user"+user.getUsername ());
        return userRepository.saveUser (user);
    }
}
