package com.notescollab.notescollab.repository.Impl;

import com.notescollab.notescollab.entity.MyUser;
import com.notescollab.notescollab.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
    private static final String GET_USER_BYUSERNAME_AND_EMAIL_QUERY = "SELECT * FROM public.users WHERE username = ? AND emailid = ?";
    private static final String ADD_USER_QUERY = "INSERT INTO public.users (username,password, fullname, emailid, roles) VALUES(?,?,?,?,?)";

    @Override
    public MyUser getUserById(Long userId) throws Exception {
        logger.info("getUserById:: start getting user Details by userid-"+userId);
        try {
            MyUser user = jdbcTemplate.queryForObject ( GET_USER_BYID_QUERY, (resultSet, rowNum) -> {
                return new MyUser (
                        resultSet.getLong ( "userid" ),
                        resultSet.getString ( "username" ),
                        resultSet.getString ( "password" ),
                        resultSet.getString ( "fullname" ),
                        resultSet.getString ( "emailid" ),
                        resultSet.getString ( "roles" )
                );

            }, userId );

            return user;
        }catch (EmptyResultDataAccessException ac){
            logger.error("getUserById:: Got exception while getting user details"+ac.getMessage(),ac);
            throw new Exception ("User not found with id " + userId);

        } catch(Exception e){
            logger.info("getUserById:: Got exception while getting user details"+e.getMessage (),e);
            throw new Exception (e.getMessage ());
        }
    }
    @Override
    public String saveUser(MyUser user){

        KeyHolder keyHolder = new GeneratedKeyHolder ();
        String encryptPwd = passwordEncoder.encode(user.getPassword ());
        jdbcTemplate.update(conn -> {
                    PreparedStatement ps = conn.prepareStatement(ADD_USER_QUERY, new String[]{"userid"});
                    ps.setString(1, user.getUsername ());
                    ps.setString ( 2, encryptPwd);
                    ps.setString ( 3, user.getFullname ());
                    ps.setString ( 4, user.getEmailid ());
                    ps.setString ( 5, user.getRoles () );

                    return ps;
        }, keyHolder);


        int generatedId = keyHolder.getKey().intValue ();

        return "User \""+ user.getFullname () +"\" successfully created ";

    }

    @Override
    public String loginUser(MyUser user) {

        return "";
    }

    @Override
    public Optional<MyUser> findByUsername(String username) {
        logger.info("findByUsername:: username-"+username);
        return Optional.ofNullable ( jdbcTemplate.queryForObject ( GET_USER_BYUSERNAME_QUERY, (resultSet, rowNum) -> {
            return new MyUser (
                    resultSet.getLong("userid"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("fullname"),
                    resultSet.getString("emailid"),
                    resultSet.getString ( "roles")
            );
        }, username ) );

    }

    @Override
    public Optional<MyUser> findByUsernameAndEmail(String username, String emailid) {
        logger.info("findByUsernameAndEmailId:: username-" + username + " emailid-" + emailid);
        try {
            var user = jdbcTemplate.queryForObject(
                    GET_USER_BYUSERNAME_AND_EMAIL_QUERY,
                    (resultSet, rowNum) -> new MyUser(
                            resultSet.getLong("userid"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("fullname"),
                            resultSet.getString("emailid"),
                            resultSet.getString("roles")
                    ),
                    username,
                    emailid
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
