package com.litenotes.controller;

import com.litenotes.model.Category;
import com.litenotes.model.Note;
import com.litenotes.service.CategoryService;
import com.litenotes.service.NoteService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador encargado de gestionar la ventana de edición de notas en la
 * aplicación LiteNotes. Permite modificar el título, contenido y categoría
 * de una nota existente, validando los datos antes de guardarlos.
 *
 * <p>Este controlador interactúa con {@link NoteService} para actualizar
 * la nota en la base de datos y con {@link CategoryService} para cargar
 * las categorías disponibles.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class NoteEditController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    @FXML
    private ComboBox<Category> categoryBox;

    private Note note;

    private final NoteService noteService = new NoteService();
    private final CategoryService categoryService = new CategoryService();

    /**
     * Establece la nota que se va a editar y carga sus datos en los campos
     * correspondientes de la interfaz.
     *
     * @param note La nota que se desea editar.
     */
    public void setNote(Note note) {
        this.note = note;

        titleField.setText(note.getTitle());
        contentArea.setText(note.getContent());
        categoryBox.setValue(note.getCategory());
    }

    /**
     * Inicializa la ventana cargando todas las categorías disponibles
     * en el ComboBox de selección.
     */
    @FXML
    public void initialize() {
        categoryBox.getItems().addAll(categoryService.getAllCategories());
    }

    /**
     * Guarda los cambios realizados en la nota. Antes de actualizarla,
     * se validan los campos obligatorios:
     * <ul>
     *   <li>El título no puede estar vacío.</li>
     *   <li>Debe seleccionarse una categoría.</li>
     * </ul>
     *
     * <p>Si los datos son válidos, la nota se actualiza mediante
     * {@link NoteService#updateNote(Note)} y se cierra la ventana.</p>
     */
    @FXML
    private void onSave() {
        if (titleField.getText().isBlank()) {
            new Alert(Alert.AlertType.WARNING, "El título no puede estar vacío.").show();
            return;
        }

        if (categoryBox.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona una categoría.").show();
            return;
        }

        note.setTitle(titleField.getText());
        note.setContent(contentArea.getText());
        note.setCategory(categoryBox.getValue());

        noteService.updateNote(note);
        closeWindow();
    }

    /**
     * Cancela la edición de la nota y cierra la ventana sin realizar cambios.
     */
    @FXML
    private void onCancel() {
        closeWindow();
    }

    /**
     * Cierra la ventana actual obteniendo la referencia al {@link Stage}
     * asociado al campo de texto del título.
     */
    private void closeWindow() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
