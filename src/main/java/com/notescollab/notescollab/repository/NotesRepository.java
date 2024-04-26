package com.notescollab.notescollab.repository;

import com.notescollab.notescollab.entity.NotesDetails;

import java.util.List;

public interface NotesRepository {

 String createNotes(NotesDetails notes, Long userId);
 List<NotesDetails> getNotesList(Long userId) throws Exception;
 NotesDetails getNotesById(Integer id, Long userId) throws Exception;

 String updateNotesById(NotesDetails notesDetails, Integer id, Long userId) throws Exception;
 String deleteNotesById(Integer id, Long userId) throws Exception;

 List<NotesDetails> searchNotes(String searchKey, Long userId) throws Exception;



}
