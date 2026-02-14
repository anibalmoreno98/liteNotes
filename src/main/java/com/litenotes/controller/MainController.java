package com.litenotes.controller;

import com.litenotes.model.Note;
import com.litenotes.service.NoteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private ListView<Note> notesList;

    private final NoteService service = new NoteService();
    private final ObservableList<Note> notes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadNotes();

        // Doble clic para editar
        notesList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Note selected = notesList.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    openEditWindow(selected);
                }
            }
        });
    }

    private void loadNotes() {
        notes.setAll(service.getAllNotes());
        notesList.setItems(notes);
    }

    @FXML
    private void onCreateNote() {
        System.out.println("Botón pulsado"); // prueba

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/note-form.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Nueva Nota");
            stage.showAndWait();

            loadNotes();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteNote() {
        Note selected = notesList.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecciona una nota para eliminar.");
            alert.show();
            return;
        }

        service.deleteNote(selected.getId());
        loadNotes();
    }

    private void openEditWindow(Note note) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/note-edit.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Editar Nota");
            stage.initModality(Modality.APPLICATION_MODAL);

            // Pasar la nota al controlador
            NoteEditController controller = loader.getController();
            controller.setNote(note);

            stage.showAndWait();

            loadNotes(); // refrescar lista después de editar

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
