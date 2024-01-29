package com.notescollab.notescollab.repository;

import com.notescollab.notescollab.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplateAutoConfiguration jdbcTemplate;

    private static final String GET_USER_BYID_QUERY = "";

    public User getUserById(int userId){
        return "";
    }

}
