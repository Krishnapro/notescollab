package com.notescollab.notescollab.controller;

import com.notescollab.notescollab.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
@RequestMapping("/api")
public class LoginController {

    private Logger logger = Logger.getLogger("com.notescollab.notescollab.controller.LoginController");

    @Autowired
    TokenService tokenService;

    @PostMapping("/token")
    public String token(Authentication authentication){
        logger.info("Token request for user: " +authentication.getName ());
        String token = tokenService.generateToken (authentication);
        logger.info("Token: - "+token);
        return token;
    }
}
