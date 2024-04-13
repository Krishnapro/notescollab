package com.notescollab.notescollab.repository;

import com.notescollab.notescollab.entity.NotesDetails;

public interface NotesRepository {

 String createNotes(NotesDetails notes, Long userId);
 NotesDetails getNotesList(Long userId);
}
