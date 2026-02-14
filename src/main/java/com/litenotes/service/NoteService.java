package com.litenotes.service;

import com.litenotes.model.Note;
import com.litenotes.repository.NoteRepository;

import java.util.List;

public class NoteService {

    private final NoteRepository repository;

    public NoteService() {
        this.repository = new NoteRepository();
    }

    public void createNote(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }

        Note note = new Note(title, content);
        repository.save(note);
    }

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public void deleteNote(int id) {
        repository.delete(id);
    }

    public void updateNote(Note note) {
        if (note.getTitle() == null || note.getTitle().isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }

        repository.update(note);
    }

}
