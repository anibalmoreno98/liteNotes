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

    public void setNote(Note note) {
        this.note = note;

        titleField.setText(note.getTitle());
        contentArea.setText(note.getContent());
        categoryBox.setValue(note.getCategory());
    }

    @FXML
    public void initialize() {
        categoryBox.getItems().addAll(categoryService.getAllCategories());
    }

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

    @FXML
    private void onCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
