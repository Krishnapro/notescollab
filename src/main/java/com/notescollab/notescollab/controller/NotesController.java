package com.notescollab.notescollab.controller;

import com.notescollab.notescollab.entity.MyUser;
import com.notescollab.notescollab.entity.NotesDetails;
import com.notescollab.notescollab.entity.UserInfoAuth;
import com.notescollab.notescollab.repository.NotesRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotesController {

    private Logger logger = LoggerFactory.getLogger ("com.notescollab.notescollab.controller.NotesController");

    @Autowired
    NotesRepository notesRepository;

    @PostMapping("/notes")
    public ResponseEntity<?> createNotes(@RequestBody NotesDetails notes, Authentication auth){
        System.out.println("createNotes:: start creating new notes"+notes.getTitle ());
        Long userId = -1L;
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        Object principal = authentication.getPrincipal ();
        if(principal instanceof UserInfoAuth){
            userId = ((UserInfoAuth) principal).getUserid ();
        }
        logger.info("createNotes:: userId - "+userId);
        String notesDetails = notesRepository.createNotes (notes, userId );
        return ResponseEntity.ok (notesDetails);

    }
    @GetMapping("/notes")
    public ResponseEntity<?> getNotes() throws Exception {
        logger.info("getNotes:: get all the notes detail of current user - ");
        Long userId = -1L;
        try {
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
            Object principal = authentication.getPrincipal ();
            if (principal instanceof UserInfoAuth) {
                userId = ((UserInfoAuth) principal).getUserid ();
            }
            List<NotesDetails> notesDetails = notesRepository.getNotesList ( userId );
            return ResponseEntity.ok ( notesDetails );
        }catch (Exception e){
            logger.error ("getNotes:: Exception while getting notes list"+e.getMessage (),e);
            return new ResponseEntity<> (e.getMessage (),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<?> getNotesById(@PathVariable("id") Integer id) throws Exception{
        logger.info("getNotesById:: start getting notes by id " + id);
        try{

            Long userId = -1L;
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
            Object principal = authentication.getPrincipal ();
            if (principal instanceof UserInfoAuth) {
                userId = ((UserInfoAuth) principal).getUserid ();
            }
            return  ResponseEntity.ok (notesRepository.getNotesById (id , userId));

        }catch (Exception e){
            logger.error("getNotesById:: Got exception while getting notes by id: "+e.getMessage(),e);
            return new ResponseEntity<> ( e.getMessage (), HttpStatus.BAD_REQUEST );
        }
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNotesById(@RequestBody NotesDetails notesDetails, @PathVariable("id") Integer id) throws Exception{
        logger.info("updateNotesById:: start updating notes by id " + id);
        try{

            Long userId = -1L;
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
            Object principal = authentication.getPrincipal ();
            if (principal instanceof UserInfoAuth) {
                userId = ((UserInfoAuth) principal).getUserid ();
            }
            return  ResponseEntity.ok (notesRepository.updateNotesById (notesDetails , id , userId ));

        }catch (Exception e){
            logger.error("updateNotesById:: Got exception while updating notes by id: "+e.getMessage(),e);
            return new ResponseEntity<> ( e.getMessage (), HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNotesById(@PathVariable("id") Integer id) throws Exception {
        logger.info("deleteNotesById:: Deleting notes by id " + id);
        try{

            Long userId = -1L;
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
            Object principal = authentication.getPrincipal ();
            if (principal instanceof UserInfoAuth) {
                userId = ((UserInfoAuth) principal).getUserid ();
            }

            return ResponseEntity.ok(notesRepository.deleteNotesById(id, userId));

        }catch(Exception e){
            logger.error("deleteNotesById: Got exception while deleting notes"+e.getMessage(),e);
            return new ResponseEntity<>(e.getMessage (), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchNotes(@PathParam ("query") String query) throws Exception {
        logger.info("searchNotes:: search notes ");
        try{

            Long userId = -1L;
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
            Object principal = authentication.getPrincipal ();
            if (principal instanceof UserInfoAuth) {
                userId = ((UserInfoAuth) principal).getUserid ();
            }

            return ResponseEntity.ok(notesRepository.searchNotes ( query, userId ));

        }catch(Exception e){
            logger.error("searchNotes: Got exception while searching notes"+e.getMessage(),e);
            return new ResponseEntity<>(e.getMessage (), HttpStatus.BAD_REQUEST);
        }
    }
}
