package com.notescollab.notescollab.controller;

import com.notescollab.notescollab.entity.User;
import com.notescollab.notescollab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/greeting")
    public String greeting(){
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
