package com.notescollab.notescollab.repository;

import com.notescollab.notescollab.entity.NotesDetails;

import java.util.List;

public interface NotesRepository {

 String createNotes(NotesDetails notes, Long userId);
 List<NotesDetails> getNotesList(Long userId) throws Exception;
}
