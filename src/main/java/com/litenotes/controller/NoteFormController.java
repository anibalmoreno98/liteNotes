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

public class NoteFormController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    @FXML
    private ComboBox<Category> categoryBox;

    private final NoteService noteService = new NoteService();
    private final CategoryService categoryService = new CategoryService();

    @FXML
    public void initialize() {
        categoryBox.getItems().addAll(categoryService.getAllCategories());
    }

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

    @FXML
    private void onCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
