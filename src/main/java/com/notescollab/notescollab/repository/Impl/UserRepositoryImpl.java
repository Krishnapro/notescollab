package com.notescollab.notescollab.repository.Impl;

import com.notescollab.notescollab.entity.User;
import com.notescollab.notescollab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_USER_BYID_QUERY = "SELECT * FROM public.user WHERE userid = ?";
    private static final String ADD_USER_QUERY = "INSERT INTO public.user (userid, username,password, fullname, emailid) VALUES(?,?,?,?,?)";

    @Override
    public User getUserById(Long userId) {
        return jdbcTemplate.queryForObject(GET_USER_BYID_QUERY, (resultSet, rowNum) -> {
            return new User(
                    resultSet.getLong("userid"),
                    resultSet.getString("username"),
                    resultSet.getString("fullname"),
                    resultSet.getString("emailid"),
                    resultSet.getString("password"));

        }, userId);
    }
    @Override
    public User saveUser(User user){
        jdbcTemplate.update(ADD_USER_QUERY, user.getUserid (), user.getUsername (), user.getFullname (), user.getEmailid (), user.getPassword ());

        return user;

    }

}
