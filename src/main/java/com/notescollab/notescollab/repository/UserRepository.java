package com.notescollab.notescollab.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplateAutoConfiguration jdbcTemplate;

}
