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
 * Controlador encargado de gestionar la ventana de creación de nuevas notas
 * dentro de la aplicación LiteNotes. Permite introducir un título, contenido
 * y categoría, validando los datos antes de crear la nota.
 *
 * <p>Este controlador interactúa con {@link NoteService} para almacenar la nueva
 * nota en la base de datos y con {@link CategoryService} para cargar las
 * categorías disponibles.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class NoteFormController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    @FXML
    private ComboBox<Category> categoryBox;

    private final NoteService noteService = new NoteService();
    private final CategoryService categoryService = new CategoryService();

    /**
     * Inicializa la ventana cargando todas las categorías disponibles
     * en el ComboBox de selección.
     */
    @FXML
    public void initialize() {
        categoryBox.getItems().addAll(categoryService.getAllCategories());
    }

    /**
     * Guarda una nueva nota utilizando los datos introducidos por el usuario.
     * Antes de crear la nota, se validan los campos obligatorios:
     * <ul>
     *   <li>El título no puede estar vacío.</li>
     *   <li>Debe seleccionarse una categoría.</li>
     * </ul>
     *
     * <p>Si los datos son válidos, se crea una instancia de {@link Note},
     * se asignan los valores introducidos y se almacena mediante
     * {@link NoteService#createNote(Note)}. Finalmente, se cierra la ventana.</p>
     */
    @FXML
    private void onSave() {
        String title = titleField.getText();
        String content = contentArea.getText();
        Category category = categoryBox.getValue();

        if (title == null || title.isBlank()) {
            new Alert(Alert.AlertType.WARNING, "El título no puede estar vacío.").show();
            return;
        }

        if (category == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona una categoría.").show();
            return;
        }

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setCategory(category);

        noteService.createNote(note);
        closeWindow();
    }

    /**
     * Cancela la creación de la nota y cierra la ventana sin guardar cambios.
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
