package com.notescollab.notescollab.repository.Impl;

import com.notescollab.notescollab.entity.User;
import com.notescollab.notescollab.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Base64;
import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger ("com.notescollab.notescollab.repository.Impl.UserRepositoryImpl");
    private static final String GET_USER_BYID_QUERY = "SELECT * FROM public.users WHERE userid = ?";
    private static final String GET_USER_BYUSERNAME_QUERY = "SELECT * FROM public.users WHERE username = ?";
    private static final String ADD_USER_QUERY = "INSERT INTO public.users (username,password, fullname, emailid) VALUES(?,?,?,?)";

    @Override
    public User getUserById(Long userId) {
        logger.info("getUserById:: start getting user Details by userid-"+userId);
        try {
            return jdbcTemplate.queryForObject ( GET_USER_BYID_QUERY, (resultSet, rowNum) -> {
                return new User (
                        resultSet.getLong ( "userid" ),
                        resultSet.getString ( "username" ),
                        resultSet.getString ( "password" ),
                        resultSet.getString ( "fullname" ),
                        resultSet.getString ( "emailid" ),
                        resultSet.getString ( "roles" )
                );

            }, userId );
        }catch(UsernameNotFoundException e){
            throw new UsernameNotFoundException ("Got exception while getting user information"+e.getMessage (),e);
        }
    }
    @Override
    public String saveUser(User user){

        KeyHolder keyHolder = new GeneratedKeyHolder ();
        String encryptPwd = passwordEncoder.encode(user.getPassword ());
        jdbcTemplate.update(conn -> {
                    PreparedStatement ps = conn.prepareStatement(ADD_USER_QUERY, new String[]{"userid"});
                    ps.setString(1, user.getUsername ());
                    ps.setString ( 2, encryptPwd);
                    ps.setString ( 3, user.getFullname ());
                    ps.setString ( 4, user.getEmailid ());

                    return ps;
        }, keyHolder);


        int generatedId = keyHolder.getKey().intValue ();

        return "User successfully created " + generatedId;

    }

    @Override
    public String loginUser(User user) {

        return "";
    }

    @Override
    public Optional<User> findByUsername(String username) {
        logger.info("findByUsername:: username-"+username);
        return Optional.ofNullable ( jdbcTemplate.queryForObject ( GET_USER_BYUSERNAME_QUERY, (resultSet, rowNum) -> {
            return new User (
                    resultSet.getLong("userid"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("fullname"),
                    resultSet.getString("emailid"),
                    resultSet.getString ( "roles")
            );
        }, username ) );

    }

}
