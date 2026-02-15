package com.litenotes.service;

import com.litenotes.model.Note;
import com.litenotes.repository.NoteRepository;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica relacionada con las notas dentro
 * de la aplicación LiteNotes. Actúa como intermediario entre los controladores
 * y el repositorio {@link NoteRepository}, proporcionando validación y
 * operaciones de negocio antes de acceder a la base de datos.
 *
 * <p>Este servicio permite crear, obtener, actualizar y eliminar notas,
 * garantizando que los datos cumplan con las reglas básicas de integridad
 * antes de ser enviados al repositorio.</p>
 *
 * <p>Forma parte de la capa de negocio, manteniendo separada la lógica de
 * presentación y la lógica de persistencia.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class NoteService {

    private final NoteRepository repository;

    /**
     * Crea una nueva instancia del servicio e inicializa el repositorio
     * utilizado para acceder a los datos de las notas.
     */
    public NoteService() {
        this.repository = new NoteRepository();
    }

    /**
     * Crea una nueva nota después de validar sus datos.
     *
     * <p>Antes de insertar la nota, se comprueba que:</p>
     * <ul>
     *   <li>El título no sea nulo ni esté vacío.</li>
     *   <li>La categoría no sea nula.</li>
     * </ul>
     *
     * <p>Si los datos son válidos, la nota se envía al repositorio para ser
     * almacenada en la base de datos.</p>
     *
     * @param note La nota que se desea crear.
     * @throws IllegalArgumentException Si el título está vacío o la categoría es nula.
     */
    public void createNote(Note note) {
        if (note.getTitle() == null || note.getTitle().isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }

        if (note.getCategory() == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }

        repository.insert(note);
    }

    /**
     * Obtiene todas las notas almacenadas en la base de datos.
     *
     * @return Una lista con todas las notas existentes.
     */
    public List<Note> getAllNotes() {
        return repository.getAll();
    }

    /**
     * Obtiene todas las notas pertenecientes a una categoría específica.
     *
     * @param categoryId Identificador de la categoría por la que se desea filtrar.
     * @return Una lista de notas pertenecientes a la categoría indicada.
     */
    public List<Note> getNotesByCategory(int categoryId) {
        return repository.getByCategory(categoryId);
    }

    /**
     * Elimina una nota de la base de datos según su identificador.
     *
     * @param id Identificador de la nota que se desea eliminar.
     */
    public void deleteNote(int id) {
        repository.delete(id);
    }

    /**
     * Actualiza una nota existente después de validar sus datos.
     *
     * <p>Antes de actualizar la nota, se comprueba que:</p>
     * <ul>
     *   <li>El título no sea nulo ni esté vacío.</li>
     *   <li>La categoría no sea nula.</li>
     * </ul>
     *
     * @param note La nota con los nuevos valores que se desean guardar.
     * @throws IllegalArgumentException Si el título está vacío o la categoría es nula.
     */
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
