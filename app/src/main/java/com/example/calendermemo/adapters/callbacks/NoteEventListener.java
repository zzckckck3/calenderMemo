package com.example.calendermemo.adapters.callbacks;

import com.example.calendermemo.model.Note;

public interface NoteEventListener {

    void onNoteClick(Note note);

    void onNoteLongClick(Note note);
}
