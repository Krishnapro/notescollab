package com.notescollab.notescollab.controller;

import com.notescollab.notescollab.entity.MyUser;
import com.notescollab.notescollab.entity.NotesDetails;
import com.notescollab.notescollab.repository.NotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotesController {

    private Logger logger = LoggerFactory.getLogger ("com.notescollab.notescollab.controller.NotesController");

    @Autowired
    NotesRepository notesRepository;

    @PostMapping("/notes")
    public String createNotes(@RequestBody NotesDetails notes){
        System.out.println("createNotes:: start creating new notes"+notes.getTitle ());
        return userRepository.saveUser (user);
    }
}
