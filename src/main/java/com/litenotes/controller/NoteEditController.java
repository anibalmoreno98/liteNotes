package com.litenotes.controller;

import com.litenotes.model.Note;
import com.litenotes.service.NoteService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NoteEditController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    private final NoteService service = new NoteService();
    private Note note;

    public void setNote(Note note) {
        this.note = note;
        titleField.setText(note.getTitle());
        contentArea.setText(note.getContent());
    }

    @FXML
    private void onSave() {
        note.setTitle(titleField.getText());
        note.setContent(contentArea.getText());

        service.updateNote(note);
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
