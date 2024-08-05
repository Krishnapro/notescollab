package com.notescollab.controller;

import com.notescollab.entity.MyUser;
import com.notescollab.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @PreAuthorize ("hasRole('ROLE_USER')")
    public ResponseEntity<?> getUserDetails(@PathVariable("userid") Long userid) throws Exception {
        try {

            logger.info ( "Getting user details..." + userid );
            MyUser user = userRepository.getUserById ( userid );
            if(user == null ){
                throw new UsernameNotFoundException ( "user " + userid + " does not found");
            }
            return ResponseEntity.ok (user);

        }catch(Exception e){
            logger.info("getUserDetails:: Exception while getting user details"+e.getMessage(),e);
            return new ResponseEntity<> (e.getMessage (), HttpStatus.BAD_REQUEST );

        }
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> createUser(@RequestBody MyUser user) throws Exception {
        logger.info("createUser:: start creating new user");
        try {
            if (checkIfUserAlreadyExists(user)) {
                logger.error("createUser:: Got exception while creating user");
                return new ResponseEntity<>("User with this username and/or email already exists.", HttpStatus.CONFLICT);
            }

            return ResponseEntity.ok (userRepository.saveUser ( user ));
        }catch (Exception e) {
            logger.error("createUser:: Got exception while creating user "+e.getMessage (),e);
            return new ResponseEntity<> (e.getMessage (),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Helper method to check wether user with given username and/or email already exists in the database
     * @param user the User Entity
     * @return {@code true} if user already exists, {@code false} otherwise
     */
    private boolean checkIfUserAlreadyExists(MyUser user) {
        var username = userRepository.findByUsername(user.getUsername());
        var email = userRepository.findByEmail(user.getEmailid());
        var usernameAndEmail = userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmailid());

        return username.isPresent() || email.isPresent() || usernameAndEmail.isPresent();
    }
}
