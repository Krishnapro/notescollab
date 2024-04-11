package com.notescollab.notescollab.repository.Impl;

import com.notescollab.notescollab.entity.NotesDetails;
import com.notescollab.notescollab.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class NotesRepositoryImpl implements NotesRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String ADD_NOTES_QUERY = "INSERT INTO public.notes (title,description, notescontent,userid) VALUES(?,?,?,?)";


    @Override
    public String createNotes(NotesDetails notes, Long userId) {

        KeyHolder keyHolder = new GeneratedKeyHolder ();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(ADD_NOTES_QUERY, new String[]{"id"});
            ps.setString(1, notes.getTitle ());
            ps.setString ( 2, notes.getDescription ());
            ps.setString ( 3, notes.getNotescontent ());
            ps.setLong ( 4, userId);

            return ps;
        }, keyHolder);


        int generatedId = keyHolder.getKey().intValue ();

        return "Notes successfully created with id- " + generatedId;
    }
}
