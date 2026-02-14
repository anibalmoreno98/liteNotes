package com.litenotes.controller;

import com.litenotes.service.NoteService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NoteFormController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    private final NoteService service = new NoteService();

    @FXML
    private void onSave() {
        service.createNote(titleField.getText(), contentArea.getText());
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
