package com.notescollab.repository;

import java.util.List;

import com.notescollab.entity.NotesDetails;

public interface NotesRepository {

 String createNotes(NotesDetails notes, Long userId);
 List<NotesDetails> getNotesList(Long userId) throws Exception;
 NotesDetails getNotesById(Integer id, Long userId) throws Exception;

 String updateNotesById(NotesDetails notesDetails, Integer id, Long userId) throws Exception;
 String deleteNotesById(Integer id, Long userId) throws Exception;

 List<NotesDetails> searchNotes(String searchKey, Long userId) throws Exception;
String shareNote(Integer id, Long userId, Long sharewithId) throws Exception;



}
