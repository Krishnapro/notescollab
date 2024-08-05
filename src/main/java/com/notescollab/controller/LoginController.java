package com.notescollab.controller;

import com.notescollab.entity.AuthRequest;
import com.notescollab.entity.MyUser;
import com.notescollab.repository.UserRepository;
import com.notescollab.services.TokenService;
import com.notescollab.services.UserInfoDetailSevice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger ("com.notescollab.notescollab.controller.LoginController");

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    /*@PostMapping("/token")
    public String token(Authentication authentication){
        logger.info("Token request for user: " +authentication.getName ());
        String token = tokenService.generateToken (authentication);
        logger.info("Token: - "+token);
        return token;
    }*/
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        logger.info("Login: - start authentication to login user ");
        try{
            Authentication authentication = authenticationManager.authenticate (
                    new UsernamePasswordAuthenticationToken (authRequest.getUsername (), authRequest.getPassword())
            );

            Optional<MyUser> user = userRepository.findByUsername (authRequest.getUsername ());
            String token = tokenService.generateToken (user.get ());

            return ResponseEntity.ok (token);
        }catch (AuthenticationException e){
            logger.error("Login:: Got Exception while login "+e.getMessage (),e);
            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED).body (e.getMessage ());
        }

    }
}
