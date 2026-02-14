package com.litenotes.service;

import com.litenotes.model.Note;
import com.litenotes.repository.NoteRepository;

import java.util.List;

public class NoteService {

    private final NoteRepository repository;

    public NoteService() {
        this.repository = new NoteRepository();
    }

    // Crear nota
    public void createNote(Note note) {
        if (note.getTitle() == null || note.getTitle().isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }

        if (note.getCategory() == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }

        repository.insert(note);
    }

    // Obtener todas las notas
    public List<Note> getAllNotes() {
        return repository.getAll();
    }

    // Obtener notas filtradas por categoría
    public List<Note> getNotesByCategory(int categoryId) {
        return repository.getByCategory(categoryId);
    }

    // Eliminar nota
    public void deleteNote(int id) {
        repository.delete(id);
    }

    // Actualizar nota
    public void updateNote(Note note) {
        if (note.getTitle() == null || note.getTitle().isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }

        if (note.getCategory() == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }

        repository.update(note);
    }
}
