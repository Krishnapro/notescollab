package com.notescollab.services;

import com.notescollab.entity.MyUser;
import com.notescollab.entity.UserInfoAuth;
import com.notescollab.repository.UserRepository;
import com.notescollab.repository.UserSecurityRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoDetailSevice implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger ("com.notescollab.notescollab.services.UserInfoDetailSevice");

    @Autowired
    private UserSecurityRepository userSecurityRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Loading user:: load user by username"+username);
        try {

            Optional<MyUser> userInfo = userSecurityRepository.findByUsername ( username );
//        Optional<MyUser> userInfo = userRepository.findByUsername(username);
            logger.info ( "user detail " + userInfo );
            if (userInfo.isPresent ()) {
                MyUser user = userInfo.get ();
                logger.info ( "user password =====" + user.getPassword () );

                return new UserInfoAuth ( user );
            } else {
                logger.error ( "Got Exception while authenticate user: " + username );
                throw new UsernameNotFoundException ( "User not found" + username );
            }
        }catch (Exception e){
            logger.error ( "Got Exception while authenticate user"+e.getMessage (),e);
            throw new UsernameNotFoundException (e.getMessage ());
        }

    }
}
