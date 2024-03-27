package com.notescollab.notescollab.services;

import com.notescollab.notescollab.entity.User;
import com.notescollab.notescollab.entity.UserInfoAuth;
import com.notescollab.notescollab.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoDetailSevice implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger ("com.notescollab.notescollab.services.UserInfoDetailSevice");

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Loading user:: load user by username"+username);

        Optional<User> userInfo = userRepository.findByUsername(username);
        return userInfo.map( UserInfoAuth:: new).orElseThrow (() ->
                new UsernameNotFoundException ( "user not found" + username ));
    }
}
